package com.Lidigu.ecommerce.order;
import com.Lidigu.ecommerce.kafka.OrderConfirmation;
import com.Lidigu.ecommerce.kafka.OrderProducer;
import com.Lidigu.ecommerce.orderLine.OrderLineRequest;
import com.Lidigu.ecommerce.customer.CustomerClient;
import com.Lidigu.ecommerce.exception.BusinessException;
import com.Lidigu.ecommerce.orderLine.OrderLineService;
import com.Lidigu.ecommerce.payment.PaymentClient;
import com.Lidigu.ecommerce.payment.PaymentRequest;
import com.Lidigu.ecommerce.product.ProductClient;
import com.Lidigu.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderReposotory repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public Integer createdOrder(OrderRequest request){
        //check customer -->feignClient
        var customer=this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() ->new BusinessException("Cannot create order::no customer exists with provided ID::"));


        //purchase the product-->RestTemplate
       var purchasedProducts= this.productClient.purchaseProducts(request.products());


        //persist order
        var order=this.repository.save(mapper.toOrder(request));
        //persist order lines
        for(PurchaseRequest purchaseRequest: request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        //start payment process
        var paymentRequest=new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer

        );
        paymentClient.requestOrderPayment(paymentRequest);

        //send order confirmation to kafka
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }


    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());

    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() ->new EntityNotFoundException(String.format("Not order found with Id: %d" ,orderId)));
    }
}

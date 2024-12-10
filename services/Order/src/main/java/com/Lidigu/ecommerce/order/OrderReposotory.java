package com.Lidigu.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderReposotory extends JpaRepository<Order,Integer> {
}

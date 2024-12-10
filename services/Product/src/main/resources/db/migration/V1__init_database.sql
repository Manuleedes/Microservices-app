create table if not exists cartegory
(
     id integer not null primary key,
     description varchar(255),
     name varchar(255)
);

create table if not exists product
(
     id integer not null primary key,
     description varchar(255),
     name varchar(255),
     availabe_quantity double precision not null,
     price numeric(38,2),
     cartegory_id integer
             constrain emmanuel references cartegory
);
create sequence if not exists cartegory_seq increment by 50;
create sequence if not exists product_seq increment by 50;
create database shop;
use shop;

/*CREATING TABLES*/
create table customer(
    customer_id int AUTO_INCREMENT,
    customer_name varchar(100) not null,
    customer_bill double,
    constraint pk_customer primary key (customer_id)
);

create table product(
    product_id int AUTO_INCREMENT,
    product_name varchar(50) not null,
    product_price double not null,
    customer_id int,
    constraint pk_product primary key (product_id)
);

create table shop_staff(
    shop_staff_id int AUTO_INCREMENT,
    shop_staff_name varchar(50) not null,
    shop_joining_date varchar(50) not null,
    constraint pk_shop_staff primary key (shop_staff_id)
);

create table shop_owner(
    shop_owner_id int AUTO_INCREMENT,
    shop_owner_name varchar(50) not null,
    constraint pk_shop_owner primary key (shop_owner_id)
);


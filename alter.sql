/*FOREIGN KEY CREATION*/
alter table product
    add constraint fk_customer foreign key (customer_id) references customer(customer_id); 
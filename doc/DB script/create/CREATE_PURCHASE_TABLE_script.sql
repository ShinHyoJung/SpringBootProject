CREATE TABLE purchase(
    purchase_id int not null auto_increment,
    name varchar(300),
    price varchar(200),
    address varchar(400),
    product_id int,
    sell_id int,
    idx int,
    delivery_status varchar(100),
    purchase_date datetime,
    primary key (purchase_id)
)
;
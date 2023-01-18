CREATE TABLE purchase(
    purchase_id int not null auto_increment,
    name varchar(300),
    price varchar(200),
    address varchar(400),
    thumbnail_image_name varchar(300),
    imp_uid varchar(500),
    product_id int,
    sell_id int,
    idx int,
    order_status varchar(100),
    purchase_date datetime,
    primary key (purchase_id)
)
;
CREATE TABLE purchase(
    purchase_id int not null auto_increment,
    name varchar(300),
    price varchar(200),
    quantity int,
    address varchar(400),
    detail_address varchar(300),
    zip_code varchar(200),
    thumbnail_image_name varchar(300),
    imp_uid varchar(500),
    product_id int,
    sell_id int,
    order_status varchar(100),
    purchase_date datetime,
    available_cancel_yn varchar(10),
    idx int,
    primary key (purchase_id)
)
;
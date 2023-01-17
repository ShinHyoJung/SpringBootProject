CREATE TABLE cart (
    cart_id int not null auto_increment,
    name varchar(300),
    price varchar(300),
    quantity int,
    thumbnail_image_name varchar(300),
    sell_id int,
    primary key (cart_id)
)
;
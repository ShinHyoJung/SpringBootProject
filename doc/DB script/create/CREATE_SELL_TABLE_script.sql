CREATE TABLE sell (
    sell_id int not null auto_increment,
    name varchar(300),
    title varchar(300),
    content varchar(300),
    price varchar(500),
    thumbnail_image varchar(300),
    thumbnail_image_path varchar(300),
    detail_image varchar(400),
    detail_image_path varchar(300),
    create_date datetime,
    update_date datetime,
    product_id int,
    product_code varchar(300),
    primary key (sell_id)
)
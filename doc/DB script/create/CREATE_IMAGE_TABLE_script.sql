CREATE TABLE image (
    image_id int not null auto_increment,
    org_name varchar(300),
    stored_name varchar(300),
    size varchar(200),
    thumbnail_image_name varchar(200),
    thumbnail_image_path varchar(300),
    detail_image_name varchar(200),
    detail_image_path varchar(300),
    product_id int,
    product_code varchar(300),
    create_date datetime,
    delete_yn varchar(100),
    sell_id int,
    path varchar(500),
    primary key (image_id)
)
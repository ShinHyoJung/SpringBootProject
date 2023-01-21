CREATE TABLE product (
     product_id int not null auto_increment,
     code varchar(200) not null,
     name varchar(200) not null,
     full_quantity int,
     sold_quantity int,
     left_quantity int,
     info longtext,
     category varchar(300),
     thumbnail_image_name varchar(300),
     register_date datetime,
     update_date datetime,
     primary key(product_id)
)
;

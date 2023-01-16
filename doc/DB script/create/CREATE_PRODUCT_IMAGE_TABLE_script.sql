CREATE TABLE product_image (
    image_id int not null auto_increment,
    org_name varchar(300),
    stored_name varchar(300),
    size varchar(200),
    product_id int,
    path varchar(500),
    create_date datetime,
    delete_yn varchar(100),
    primary key (image_id)
)
;
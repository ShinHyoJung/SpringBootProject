CREATE TABLE sell_image (
    image_id int not null auto_increment,
    org_name varchar(300),
    stored_name varchar(300),
    size varchar(200),
    thumbnail_image_name varchar(200),
    title_image_name varchar(200),
    detail_image_name varchar(200),
    sell_id int,
    path varchar(500),
    create_date datetime,
    delete_yn varchar(100),
    primary key (image_id)
)
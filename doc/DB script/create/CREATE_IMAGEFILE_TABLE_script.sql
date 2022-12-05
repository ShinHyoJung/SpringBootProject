CREATE TABLE imagefile (
    imagefile_id int not null auto_increment,
    org_name varchar(300),
    stored_name varchar(300),
    size varchar(200),
    product_id int,
    product_code varchar(300),
    create_date datetime,
    sell_id int,
    path varchar(500),
    primary key (imagefile_id)
)
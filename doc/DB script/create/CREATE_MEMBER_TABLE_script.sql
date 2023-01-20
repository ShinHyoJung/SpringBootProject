CREATE TABLE member (
    idx int not null auto_increment,
    login_id varchar(100) not null,
    password varchar(200) not null,
    name varchar(200) not null,
    address varchar(300),
    detail_address varchar(300),
    zip_code varchar(200),
    email varchar(300),
    birth varchar(200),
    phone varchar(300),
    create_date datetime,
    update_date datetime,
    primary key (idx)
)
;
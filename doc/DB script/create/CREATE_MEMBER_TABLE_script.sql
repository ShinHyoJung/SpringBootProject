CREATE TABLE member (
    idx int not null auto_increment,
    member_id varchar(100) not null,
    password varchar(200) not null,
    name varchar(200) not null,
    address varchar(300),
    mail varchar(300),
    birth varchar(200),
    mobile varchar(300),
    create_date datetime,
    update_date datetime,
    primary key (idx)
)
waCREATE TABLE want(
    want_id int not null auto_increment,
    name varchar(300),
    price varchar(500),
    thumbnail_image_name varchar(300),
    sell_id int,
    idx int,
    primary key (want_id)
)
;
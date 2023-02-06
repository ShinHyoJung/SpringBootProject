CREATE TABLE answer(
    answer_id int not null auto_increment,
    writer varchar(200),
    content longtext,
    create_date datetime,
    update_date datetime,
    inquiry_id int,
    idx int,
    primary key (answer_id)
)
;
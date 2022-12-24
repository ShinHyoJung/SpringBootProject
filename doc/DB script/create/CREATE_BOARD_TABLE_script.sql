CREATE TABLE board (
    board_id int not null auto_increment,
    member_id varchar(100) not null,
    title varchar(200),
    content varchar(10000),
    writer varchar(100),
    create_date datetime,
    update_date datetime,
    idx int,
    primary key (board_id)
)
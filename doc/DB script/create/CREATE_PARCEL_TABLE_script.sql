CREATE TABLE parcel (
    parcel_id int not null auto_increment,
    name varchar(200),
    address varchar(300),
    status int,
    waybill_number varchar(500),
    purchase_id int,
    product_id int,
    sell_id int,
    idx int,
    primary key (parcel_id)
)
;
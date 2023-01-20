CREATE TABLE parcel (
    parcel_id int not null auto_increment,
    name varchar(200),
    address varchar(300),
    detail_address varchar(300),
    zip_code varchar(200),
    quantity int,
    status int,
    waybill_number varchar(500),
    purchase_id int,
    product_id int,
    sell_id int,
    idx int,
    purchase_date datetime,
    ship_date datetime,
    primary key (parcel_id)
)
;
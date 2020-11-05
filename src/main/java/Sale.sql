/**
 * Author:  mazca993
 * Created: 12/08/2018
 */

create table Sale (
sale_Id integer not null,
person_Id integer not null,
date Date not null,
SaleItemList object not null,
status varchar(20) not null,

constraint salePK primary key (sale_Id),
constraint sale_personID foreign key (person_Id) references Customer
);

create table SaleItem (
sale_Id integer not null,
product_Id integer not null,
quantity_Purchased integer,
sale_Price decimal,


constraint saleItemsPK primary key (sale_Id, product_Id),
constraint saleItems_idFK foreign key (product_Id) references Product,
constraint saleItems_saleIdFK foreign key (sale_Id) references Sale
);


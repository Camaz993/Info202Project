/**
 * Author:  mazca993
 * Created: 12/08/2018
 */

create table Customer (
person_Id integer auto_increment (1),
username varchar(20) not null unique,
first_Name varchar(20) not null,
surname varchar(20) not null,
password varchar(20) not null,
emailAddress varchar(50) not null,
shippingAddress varchar(50) not null,
creditCardDetails varchar(30) not null,
saleList OBJECT,
constraint Customer_PK primary key (person_Id)
);



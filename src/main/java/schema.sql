/**
 * Author:  mazca993
 * Created: 12/08/2018
 */

create table Product (
id integer,
name varchar(50),
description varchar(100),
price decimal,
stock integer,
categories varchar(50),
constraint Product_PK primary key (id)
);


create table type(
    id serial primary key,
    "name" varchar(255)
);

create table product(
    id serial primary key,
    "name" varchar(255),
    type_id int references "type"(id),
    expired_date timestamp,
    price int
);

select * from "type";
select * from product;

drop table product;
drop table type;

-- INSERTS
insert into "type"(name) values
    ('Cheese'),
    ('Milk'),
    ('Meat'),
    ('Fruits and Berries'),
    ('Vegetables');

insert into product("name", type_id, expired_date, price) values
('Maasdam cheese', 1, '26.07.24', 901), ('Gouda', 1, '20.06.24', 765), ('Parmesan', 1, '30.06.24', 883),
('Milk', 2, '26.06.24', 158), ('Yogurt', 2, '10.05.23', 82), ('Frozen', 2, '09.12.24', 327),
('Frozen beef', 3, '01.09.24', 937), ('Pork', 3, '13.09.23', 481), ('Frozen turkey', 3, '12.12.24', 535),
('Banana', 4, '28.06.24', 150), ('Frozen strawberry', 4, '10.07.24', 250), ('Watermelon', 4, '10.05.24', 331),
('Potato', 5, '12.11.24', 250), ('Cherie tomato', 5, '12.07.24', 150), ('Garlik', 5, '31.12.24', 139);

insert into product("name", type_id, expired_date, price) values
('Chicken', 3, '11.09.24', 352), ('Steak', 3, '28.06.24', 2300);

-- SELECT
select p.name as Продукт, p.type_id as Тип, p.expired_date as "Годен до", p.price as Цена
from product as p where type_id = 1;

select * from product where product.name like '%Frozen%';

select * from product where product.expired_date < current_date;

select * from product
where price = (select max(price) from product);

select t.name as Тип, count(p.name) as Количество
from "type" as t
join product as p
on p.type_id = t.id
group by t.name;

select t.name as Тип, p.name as Продукт, p.expired_date as "Годен до", p.price as Цена
from product as p
    join type as t
        on p.type_id = t.id
where t.name = 'Cheese'
    or  t.name = 'Milk';

select t.name as Тип, count(p.name) as Количество
from type t
    join product p
        on p.type_id = t.id
group by t.name
having count(p.name) < 5;

select t.name as Тип, p.name as Продукт
from product p
    join "type" t on p.type_id = t.id


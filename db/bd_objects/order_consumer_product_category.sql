create table categories(
    id       serial primary key,
    "name" text
);

create table products(
    id          serial primary key,
    category_id int references categories(id),
    product     text,
    price       int
);

create table consumers(
    id       serial primary key,
    consumer text
);

create table order_details(
    id serial primary key,
    consumer_id int references consumers(id),
    product_id int references products(id)
);


drop table categories;
drop table products;
drop table consumers;
drop table order_details;

select * from categories;
select * from products;
select * from consumers;
select * from order_details;

insert into categories(name) values
    ('Electronics'),
    ('Clothes'),
    ('Home');

insert into products(category_id, product, price) values
    (1, 'E_book Kindle Scribe', 270),
    (1, 'SAMSUNG Galaxy Watch', 160),
    (1, 'DJI Mini 4K', 300),
    (1, 'Samsung 4K The Frame TV with Teak Bezel', 1000),
    (1, 'Mini Projector', 65),

    (2, 'Libin Mens Golf Pants Stretch Work', 40),
    (2, 'NORTHYARD Men Athletic Basketball Shorts', 26),
    (2, 'Mizuno Mens Wave Rider', 125),
    (2, 'Gildan Men Crew T-Shirts', 70),
    (2, 'Champion Men Hoodie, Powerblend', 30),
    (2, 'COOFANDY Men Hawaiian Floral Shirts Cotton', 30),

    (3, 'Tineco Floor Cordless Wet Dry Vacuum Cleaner', 290),
    (3, 'Keurig K-Elite Single-Serve K-Cup Pod Coffee Maker', 100),
    (3, 'Keurig K-Iced Single Serve Coffee Maker', 50),
    (3, 'JISULIFE Handheld Mini Fan', 13),
    (3, 'GOWE King Size Upholstered Platform Bed Frame with Headboard', 615),
    (3, 'Hamilton Beach Power Elite Wave Action Blender For Shakes and Smoothies', 30),
    (3, 'Gravity Electric Pepper and Salt Grinder Set', 18);

insert into consumers(consumer) values
    ('Relaxa'),
    ('She'),
    ('He');

insert into order_details(consumer_id, product_id) values
    (1, 1),
    (1, 3),
    (1, 10),
    (1, 18),

    (2, 2),
    (2, 5),
    (2, 8),
    (2, 11),
    (2, 15),
    (2, 4),

    (3, 17),
    (3, 6),
    (3, 9),
    (3, 13),
    (3, 14),
    (3, 16),
    (3, 7);

create view show_consumers_with_total_sum_more_500_and_more_3_products_of_the_same_category
as
select c.consumer as Покупатель, sum(p.price) as Сумма, cat.name as Категория
from order_details od
    join consumers c on od.consumer_id = c.id
    join products p on od.product_id = p.id
    join categories cat on p.category_id = cat.id
group by (c.consumer, cat.name)
having sum(p.price) > 500 and count(cat.name) > 3;

select * from show_consumers_with_total_sum_more_500_and_more_3_products_of_the_same_category;




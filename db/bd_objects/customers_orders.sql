CREATE TABLE customers(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

select * from customers;

insert into customers(first_name, last_name, age, country)
values ('Константин', 'Хабенский', 52, 'Russia'),
       ('Екатерина', 'Кузнецова', 37, 'Russia'),
       ('Иван', 'Янковский', 33, 'Russia'),
       ('Александр', 'Петров', 35, 'Russia'),
       ('София', 'Каштанова', 37, 'Russia'),
       ('Eva', 'Green', 44, 'France'),
       ('Emily', 'Blunt', 41, 'Britain'),
       ('Uma', 'Thurman', 54, 'USA'),
       ('Gillian', 'Anderson', 56, 'USA'),
       ('Anya', 'Taylor-Joy', 28, 'USA'),
       ('Sophie', 'Turner', 28, 'Britain'),
       ('Timothée', 'Chalamet', 28, 'USA'),
       ('Kate', 'Beckinsale', 51, 'Britain'),
       ('Evangeline', 'Lilly', 45, 'Canada'),
       ('Ana', 'de Armas', 36, 'Cuba'),
       ('Rose', 'Byrne', 45 , 'Australia'),
       ('Cillian', 'Murphy', 48, 'Irish');

SELECT * from customers
WHERE age = (SELECT min(age) FROM customers);

create table orders(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into orders(amount, customer_id)
values (1000, 1),
       (3000, 3),
       (2500, 3),
       (300, 5),
       (800, 6),
       (1200, 7),
       (10000, 9),
       (7300, 9),
       (100, 13),
       (450, 13),
       (10, 17),

select * from customers
where id not in (select customer_id from orders);

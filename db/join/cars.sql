create table car_bodies(
    id     serial primary key,
    "name" text
);

create table car_engines(
    id     serial primary key,
    "name" text
);

create table car_transmissions(
    id     serial primary key,
    "name" text
);

create table cars(
    id              serial primary key,
    "name"          text,
    body_id         int references car_bodies(id),
    engine_id       int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

select * from car_bodies;
select * from car_engines;
select * from car_transmissions;
select * from cars;

drop table cars;

insert into car_bodies(name) values
    ('SEDAN'),
    ('COUPE'),
    ('CONVERTIBLE'),
    ('PICKUP'),

insert into car_engines(name) values
    ('V6'),
    ('V8'),
    ('Electric');

insert into car_transmissions(name) values
    ('Manual'),
    ('Automatic'),
    ('Robotic');

insert into cars(name, body_id, engine_id, transmission_id) values
    ('Toyota Camry', 1, 1, 2),
    ('Audi S4', 1, 1, 2),
    ('Tesla Cybertruck', null , 3, 2),
    ('Canoo Pickup', 4, null, 2),
    ('Porsche Panamera', 1, 1, null),
    ('Tesla Model S', 1, 3, 2),
    ('Toyota Supra', 2, 1, 1);

select c.name as car_name, cb.name as body_name,
ce.name as engine_name, ct.name as transmission_name
from cars c
    left join car_bodies cb on c.body_id = cb.id
        left join car_engines ce on c.engine_id = ce.id
            left join car_transmissions ct on c.transmission_id = ct.id;

select b.name as body_name
from car_bodies b
    left join cars c on c.body_id = b.id
        where c.body_id is null;

select e.name as engine_name
from car_engines e
    left join cars c on c.engine_id = e.id
        where c.engine_id is null;

select ct.name as transmission_name
from car_transmissions ct
    left join cars c on c.transmission_id = ct.id
        where c.transmission_id is null;


create table devices(
    id serial primary key,
    "name" varchar(255),
    price float
);

create table people(
    id serial primary key,
    "name" varchar(255)
);

create table devices_people(
    id serial primary key,
    device_int int references devices(id),
    people_id int references people(id)
);


select * from devices;
select * from people;
select * from devices_people;


insert into devices(name, price)
values ('Air Humidifier', 3000), ('Sony Alpha', 5443),
       ('Coffee machine Bork', 900), ('DJI Mavic 4 pro', 3000)
       ('Kindle Scribe', 200);

insert into people(name) values ('Kris'), ('Ostin'), ('Lexa'), ('Milka');

insert into devices_people(device_int, people_id)
values (1, 1), (1, 2), (2, 2), (2, 3), (2, 3),
       (3, 1),
       (4, 1), (4, 2), (4, 3), (4, 4)
       (5, 2), (5, 3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people p on dp.people_id = p."id"
join devices d on dp.device_int = d.id
group by p."name";

select p.name, avg(d.price)
from devices_people as dp
join people p on dp.people_id = p."id"
join devices d on dp.device_int = d.id
group by p.name
having avg(d.price) > 4000;



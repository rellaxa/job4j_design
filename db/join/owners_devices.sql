create table owners(
    id     serial primary key,
    "name" varchar(255)
);

create table devices(
    id       serial primary key,
    "name"   varchar(255),
    owner_id int references owners(id)
);

select * from owners;
select * from devices;

drop table devices;
drop table owners;

insert into owners(name) values
    ('Owner 1'),
    ('Owner 2'),
    ('Owner 3');

insert into devices(name, owner_id) values
    ('Device 1', 1),
    ('Device 2', 2),
    ('Device 3', 3),
    ('Device 4', null),
    ('Device 5', null),
    ('Device 6', 1);

select * from devices d
         left join owners o on d.owner_id = o.id;

select * from devices d
         right join owners o on d.owner_id = o.id;

select * from devices d
         left join owners o on d.owner_id = o.id
where o.id is null;

select * from owners o
         left join devices d on o.id = d.owner_id;

select * from devices d
         right join owners o on d.owner_id = o.id;

select * from devices d
         full join owners o on d.owner_id = o.id;

select * from devices d
         cross join owners o;





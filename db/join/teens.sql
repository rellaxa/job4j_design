create table teens(
    id serial primary key,
    "name" varchar(255),
    gender varchar(6)
);

select * from teens;
drop table teens;

insert into teens(name, gender) values
    ('Bill', 'male'),
    ('Konnor', 'male'),
    ('Andy', 'male'),
    ('Franklin', 'male'),
    ('Steven', 'male'),

    ('Amelia', 'female'),
    ('Jennifer', 'female'),
    ('Marietta', 'female'),
    ('Katrina', 'female'),
    ('Rachel', 'female');

select m.name as male, f.name as female
from teens m
    cross join teens f
where m.gender = 'male'
    and f.gender = 'female';

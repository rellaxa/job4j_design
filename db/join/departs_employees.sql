create table departments(
    id     serial primary key,
    "name" varchar(255)
);

create table employees(
    id     serial primary key,
    "name" varchar(255),
    department_id int references departments(id)
);

select * from departments;
select * from employees;

drop table employees;

insert into departments(name) values
    ('For Business & Trade'),
    ('For Education'),
    ('For Transport'),
    ('For Health & Social Care');

insert into employees(name, department_id) values
    ('Ruby Noble', null),
    ('Latoya Bond', 2),
    ('Sara Duke', 3),
    ('Bethany Durham', 4),
    ('Fernando Haynes', 2),
    ('Curtis Steel', 2),
    ('Trina Proctor', 3),
    ('Mel Jarvis', 4);

select d.name as Департамент
from departments d
	left join employees e on d.id = e.department_id
where e.department_id is null;

select d.name as Департамент, e.name as Работник
from departments d
	left join employees e on d.id = e.department_id;

select d.name as Департамент, e.name as Работник
from employees e
	right join departments d on e.department_id = d.id;

select d.name as Департамент, e.name as Работник
from departments d
	cross join employees e;

select d.name as Департамент, e.name as Работник
from departments d
	full join employees e on d.id = e.department_id;
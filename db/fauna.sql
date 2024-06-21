create table fauna(
    id             serial primary key,
    "name"         text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('Siberian_tiger', 17, '01.02.1758');
insert into fauna(name, avg_age, discovery_date)
values ('Coral_fish', 35, '06.11.1987');
insert into fauna(name, avg_age, discovery_date)
values ('Pine_owl', 50, null);
insert into fauna(name, avg_age, discovery_date)
values ('Turritopsis_dohrnii', 15000, '29.07.2010');
insert into fauna(name, avg_age, discovery_date)
values ('Somniosus_microcephalus', 300, '13.01.1990');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000 ;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';
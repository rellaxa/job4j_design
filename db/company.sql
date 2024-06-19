create table portfolio(
    id serial primary key,
    name varchar(255)
);

create table companies(
id serial primary key,
name varchar(255),
portfolio_id int references portfolio(id)
);

insert into portfolio(name) values ('Relaxa');

insert into companies(name, portfolio_id) values ('Apple', 1);
insert into companies(name, portfolio_id) values ('Nvidia', 1);
insert into companies(name, portfolio_id) values ('Netflio', 1);

create table person(
    id serial primary key,
    name varchar(255)
);

create table nickname(
    id serial primary key,
    nick varchar(255),
    person_id int references person(id) unique
);

create table cars(
    id serial primary key,
    model varchar(255)
);

create table colors(
    id serial primary key,
    color_name varchar(255)
);

create table factory(
    id serial primary key,
    car_id int references cars(id),
    color_id int references colors(id)
);

insert into cars(model) values ('tesla model s');

insert into colors(color_name) values ('white');
insert into colors(color_name) values ('black');

insert into factory(car_id, color_id) values (1, 1);
insert into factory(car_id, color_id) values (1, 2);
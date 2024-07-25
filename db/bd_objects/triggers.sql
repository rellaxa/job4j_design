create table products(
    id       serial primary key,
    "name"     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- 1. After insert data

create
or replace function tax_after()
	returns trigger as
$$
	BEGIN
		update products
		set price = price * 1.2
		where id = (select id from inserted);
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger tax_after_trigger
	after insert
	on products
	referencing new table as
	                inserted
	for each statement
	execute procedure tax_after();

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 2, 100);

insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 5, 80);

insert into products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 10, 120);

select * from products;

alter table products disable trigger tax_after_trigger;

-- 2. Before insert trigger

create
or replace function tax_before()
	returns trigger as
$$
	BEGIN
		new.price = new.price * 1.2;
		return new;
	END;
$$
LANGUAGE 'plpgsql'

create trigger tax_before_trigger
	before insert
	on products
	for each row
	execute procedure tax_before();

insert into products (name, producer, count, price)
VALUES ('product_4', 'producer_4', 22, 1000);

insert into products (name, producer, count, price)
VALUES ('product_5', 'producer_5', 55, 180);

insert into products (name, producer, count, price)
VALUES ('product_6', 'producer_6', 112, 1120);

select * from products;

-- 3. update history_of_price after insert on products

create table history_of_price(
	id serial primary key,
	"name" varchar(50),
	price integer,
	date timestamp
);


create
or replace function update_history()
	returns trigger as
$$
	BEGIN
		insert into history_of_price(name, price, date) values
		(new.name, new.price, now());
		return new;
	END;
$$
LANGUAGE 'plpgsql';

create trigger update_history_trigger
	after insert
	on products
	for each row
	execute procedure update_history();

insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 22, 1000);

select * from products;
select * from history_of_price;
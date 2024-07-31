create table products(
    id       serial primary key,
    "name"     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- 1. Stored procedure

create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_2', 'producer_2', 15, 32);

create
or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
	BEGIN
        if u_count > 0 THEN
			update products
			set count = count - u_count
			where id = u_id;
		end if;
		if
			tax > 0 THEN
			update produtcts
			set price = price + price * tax;
		end if;
    END;
$$;

call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);


call update_data(0, 0.2, 0);
call update_data(3, 0, 3);

alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;

drop procedure update(u_count integer, tax float, u_id integer);

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

select * from products;

-- 2. Stored function

create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
	BEGIN
		insert into products(name, producer, count, price)
		values(i_name, prod, i_count, i_price);
	END;
$$;

select f_insert_data('product_1', 'producer_1', 25, 50);

create
or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
            select into result count
            from products
            where id = u_id;
        end if;
        if tax > 0 THEN
            update products
            set price = price + price * tax;
            select into result sum(price)
            from products;
        end if;
        return result;
    end;
$$;

select f_update_data(10, 0, 1);

select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

select f_update_data(0, 0.2, 0);

select * from products;

-- 3. Delete data procedure & function

create
or replace procedure delete_data_by_id(d_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products
        where id = d_id;
    END
$$;

call delete_data_by_id(3);

create
or replace function f_delete_data_by_id(d_id integer)
returns boolean
language 'plpgsql'
as
$$
    DECLARE
        result boolean;
    BEGIN
        delete from products
        where id = d_id;
        IF FOUND THEN
            result = true;
        ELSE
            result = false;
        END IF;
        return result;
    END;
$$;

select f_delete_data_by_id(3);

create
or replace procedure delete_data_if_count_zero()
language 'plpgsql'
as $$
    BEGIN
        delete from products
        where count = 0;
    END
$$;

call delete_data_if_count_zero();

create
or replace function f_delete_data_if_count_zero()
returns boolean
language 'plpgsql'
as
$$
    DECLARE
        result boolean;
    BEGIN
        delete from products
        where count = 0;
        IF FOUND THEN
            result = true;
        ELSE
            result = false;
        END IF;
        return result;
    END;
$$;

select f_delete_data_if_count_zero();

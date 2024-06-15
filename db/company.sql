create table portfolio (
	id serial primary key,
	company text,
	shares integer,
	share_price money
);
insert into portfolio (company, shares, share_price) values ('apple', '10', '212');
select * from portfolio
update portfolio set shares=5, share_price=350
delete from portfolio
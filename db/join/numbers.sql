create table numbers(
    num int unique
);

select * from numbers;
drop table numbers;

insert into numbers(num) values (1), (2), (3);

select n1.num as a, n2.num as b, (n1.num * n2.num) as "a*b="
from numbers n1
    cross join  numbers n2;
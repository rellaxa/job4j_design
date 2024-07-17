create table students(
    id serial primary key,
    "name" varchar(50)
);

create table authors(
    id   serial primary key,
    "name" varchar(50)
);

create table books(
    id serial primary key,
    "name" varchar(200),
    author_id integer references authors(id)
);

create table orders(
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

select * from students;
select * from authors;
select * from books;
select * from orders;

insert into students(name) values
    ('Иван Иванов'),
    ('Петр Петров');

insert into authors(name) values
    ('Александр Пушкин'),
    ('Николай Гоголь');

insert into books(name, author_id) values
    ('Евгений Онегин', 1),
    ('Капитанская дочка', 1),
    ('Дубровский', 1),
    ('Мертвые души', 2),
    ('Вий', 2);

insert into orders(book_id, student_id) values
    (1, 1),
    (3, 1),
    (5, 2),
    (4, 1),
    (2, 2);



create view show_students_with_2_or_more_books
as
select s.name as student, count(a.name), a.name as author
from students s
        join orders o on s.id = o.student_id
        join books b on b.id = o.book_id
        join authors a on b.author_id = a.id
group by (s.name, a.name)
having count(a.name) >= 2;

select * from show_students_with_2_or_more_books;

CREATE VIEW vista AS SELECT text 'Hello World' AS hello;

select * from vista;



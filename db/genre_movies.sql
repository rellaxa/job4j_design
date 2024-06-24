create table genre(
    id    serial primary key,
    genre varchar(255)
);

create table movies(
    id       serial primary key,
    movie    text,
    budget   int,
    genre_id int references genre(id)
);

insert into genre(genre) values ('drama');
insert into genre(genre) values ('comedy');
insert into genre(genre) values ('horror');

drop table genre;
drop table movies;

delete from movies;
delete from genre;

select * from movies;

insert into movies(movie, budget, genre_id) values ('Fringe', 4000000, 1);
insert into movies(movie, budget, genre_id) values ('Mothers Instinct', 70000000, 1);
insert into movies(movie, budget, genre_id) values ('Road House', null, 1);

insert into movies(movie, budget, genre_id) values ('Inglourious Basterds', 70000000, 2);
insert into movies(movie, budget, genre_id) values ('1+1', 9500000, 2);
insert into movies(movie, budget, genre_id) values ('Ted', 50000000, 2);

insert into movies(movie, budget, genre_id) values ('Annabelle', 6500000, 3);
insert into movies(movie, budget, genre_id) values ('Insidious', 1500000, 3);
insert into movies(movie, budget, genre_id) values ('The Cabin in the Woods', 30000000, 3);


select m.movie as Фильм, m.budget as Бюджет, g.genre as Жанр
from movies as m join genre as g on m.genre_id = g.id;

select m.movie, m.budget, g.genre
from movies as m join genre as g on m.genre_id = g.id where m.budget > 4000000

select m.movie as Фильм, m.budget as Бюджет, g.genre as Жанр
from movies as m join genre as g on m.genre_id = g.id where m.movie like '%Wood%';




create table roles(
    id serial primary key,
    role varchar(255)
);

create table rules(
    id serial primary key,
    rule varchar(255)
);

create table categories(
    id serial primary key,
    categorie varchar(255)
);

create table states(
    id serial primary key,
    state varchar(255)
);

create table users(
    id serial primary key,
    user_name varchar(255),
    role_id int references roles(id)
);


create table roles_rules(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table items(
    id serial primary key,
    item_name varchar(255),
    user_id int references users(id),
    categorie_id int references categories(id),
    state_id int references states(id)
);

create table comments(
    id serial primary key,
    comment varchar(255),
    item_id int references items(id)
);

create table attachs(
    id serial primary key,
    attach varchar(255),
    item_id int references items(id)
);







-- roles
insert into roles(role) values ('the main plan');
insert into roles(role) values ('the thecond plan');
insert into roles(role) values ('NPC');

-- rules
insert into rules(rule) values ('Be a good person');
insert into rules(rule) values ('Be an asshole');
insert into rules(rule) values ('Be someone else');

-- roles_rules
insert into roles_rules(role_id, rule_id) values (1, 1);
insert into roles_rules(role_id, rule_id) values (2, 2);
insert into roles_rules(role_id, rule_id) values (3, 3);

-- users
insert into users(user_name, role_id) values ('Laura Palmer', 3);
insert into users(user_name, role_id) values ('Leo Johnson', 2);
insert into users(user_name, role_id) values ('Dale Cooper', 1);

-- states
insert into states(state) values ('In work');
insert into states(state) values ('Not started');
insert into states(state) values ('Completed');

-- categories
insert into categories(categorie) values ('Highly important');
insert into categories(categorie) values ('Important');
insert into categories(categorie) values ('No matter');

-- items
insert into items(item_name, user_id, categorie_id, state_id) values ('Investigation', 3, 1, 1);
insert into items(item_name, user_id, categorie_id, state_id) values ('Murder', 2, 2, 3);
insert into items(item_name, user_id, categorie_id, state_id) values ('Alive', 1, 3, 2);

-- comments
insert into comments(comment, item_id) values ('Interesting to watch', 1);
insert into comments(comment, item_id) values ('Why people do this?', 2);
insert into comments(comment, item_id) values ('Life is too short, u have time to realize yourself!', 3);

-- attachments
insert into attachs(attach, item_id) values ('The first case', 1);
insert into attachs(attach, item_id) values ('The thecond case', 2);
insert into attachs(attach, item_id) values ('The third case', 3);




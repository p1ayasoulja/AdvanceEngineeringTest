# AdvanceEngineeringTest
REST Api Application 

#Установка

1.Клонируйте репозиторий с GitHub

2.Создайте базу данных (в моем случае PostgreSQL) и подключите ее к проекту

3.Отредактируйте application.properties для вашей базы данных
(прим. PostgreSQL)

spring.datasource.url=jdbc:postgresql://localhost/{имя вашей базы данных}

spring.datasource.username={ваш никнейм в PSQL}

spring.datasource.password={ваш пароль в PSQL}

#SQL Script for DataBase

create table if not exists projects
(
id bigint not null,
name varchar(255),
parent_id bigint,
primary key (id),
constraint fk_project_parent foreign key (parent_id)
references projects (id)
on delete cascade
on update cascade
);
create table if not exists users
(
id bigint not null,
username varchar(255),
password varchar(255),
primary key (id)
);

create table if not exists user_role
(
user_id bigint not null,
roles varchar(255),
constraint fk_user_role_user foreign key (user_id)
references users (id)
on delete cascade
on update cascade
);
create table if not exists tasks
(
id bigint not null,
name varchar(255),
title varchar(255),
create_time date,
status_change_time date,
user_id bigint,
status varchar(255),
type varchar(255),
project_id bigint,
primary key (id),
constraint fk_task_user foreign key (user_id)
references users (id)
on delete set null
on update cascade,
constraint fk_task_project foreign key (project_id)
references projects (id)
on delete set null
on update cascade
);
insert into users(id,username, password)
values (1,'admin', 'password');
insert into users(id,username, password)
values (2,'user', 'password');

insert into projects(id,name, parent_id)
values (1,'google', null);

insert into projects(id,name, parent_id)
values (2,'yandex', null);

insert into projects(id,name, parent_id)
values (3,'facebook', null);

insert into projects(id,name, parent_id)
values (4,'amazon', 1);

insert into projects(id,name, parent_id)
values (5,'pixel', 1);

insert into projects(id,name, parent_id)
values (6,'instagram', 3);

insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (1,'upgrade google search', 'make it faster', '2023-03-15', '2023-03-15', 1, '1', '1', 1);

insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (2,'update design of google search', 'make it more colourful', '2023-03-13', '2023-03-13', 2, '1', '1', 1);

insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (3,'change icon of goole search', 'more blue', '2023-03-11', '2023-03-11', 1, '2', '0', 1);

insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (4,'change icon of yandex search', 'more red', '2023-03-14', '2023-03-16', 1, '0', '1', 2);
insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (5,'delete yandex plus', 'totally', '2023-03-17', '2023-03-17', 2, '0', '1', 2);

insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (6,'change icon of app', 'make it red', '2023-03-17', '2023-03-17', 1, '0', '1', 3);

insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (7,'fire some workers', 'oml they chillin whole day', '2023-03-17', '2023-03-17', 1, '0', '1', 4);

insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (8,'copy iphone camera', '1 in 1 pls', '2023-03-17', '2023-03-17', 2, '0', '1', 5);

insert into tasks (id,name, title, create_time, status_change_time, user_id, status, type, project_id)
values (9,'add reels', 'tiktok...', '2023-03-12', '2023-03-12', 1, '1', '0', 6);

insert into user_role(user_id, roles)
values ('1', 'ADMIN');
insert into user_role(user_id, roles)
values ('2', 'USER');

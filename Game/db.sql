drop database if exists Game;
create database Game character set utf8mb4;
use Game;
create table user(
    userId  int primary key auto_increment,
    userName  varchar(50),
    password varchar(50)
);
create table scores(
    score int
);
insert into user values (null,"qinke","123");
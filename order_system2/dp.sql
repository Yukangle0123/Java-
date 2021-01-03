drop database if exists order_system2;
create database order_system2  CHARACTER SET utf8mb4;
use order_system2;
create table dish(
    dishId int primary key auto_increment,
    name varchar(50),
    price int
);
create table user(
    userId int primary key auto_increment,
    userName varchar(50),
    password varchar(50),
    isAdmin int
);
create table order_user(
    orderId int primary key auto_increment,
    userId int,
    time datetime,
    isDone int,
    foreign key (userId) references user(userId)
);
drop table if exists order_dish;
create table  order_dish(
    orderId int,
    dishId int,
    name varchar (50),
    price int,
    foreign key (orderId) references order_user(orderId)
);
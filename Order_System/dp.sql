drop database if exists order_system;
create database order_system CHARACTER SET utf8mb4;
use order_system;

drop table if exists dishes;
create table dish(
	dishId int primary key auto_increment,
	name varchar(50),
	price int
);
drop table if exists user;
create table user(
	userId int primary key auto_increment,
	name varchar(50),
	password varchar(50),
	isAdmin int
);
drop table if exists order_user;
create table order_user(
	orderId int primary key auto_increment,
	userId int,
	time datetime,
	isDone int ,
	foreign key(userId) references user(userId)
);
drop table if exists order_dish;
create table order_dish(
	orderId int ,
	dishId int ,
	foreign key (orderId) references order_user(orderId),
	foreign key (dishId) references dish(dishId)
);

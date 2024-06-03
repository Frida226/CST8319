CREATE DATABASE flowershop;
USE flowershop;

CREATE TABLE users(
id int primary key auto_increment, 
username varchar(50),
password varchar(50),
email varchar(50),
phonenumber varchar(20)
);
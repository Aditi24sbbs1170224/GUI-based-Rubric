create database if not exists StudentDB;
use StudentDB;

create table if not exists students(
    id int auto_increment primary key,
    name varchar(50),
    email varchar(50),
    course varchar(50)
);

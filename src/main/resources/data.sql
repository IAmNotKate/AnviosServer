DROP DATABASE IF EXISTS anvios;
CREATE DATABASE anvios CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE anvios;

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id       int primary key auto_increment,
    username varchar(50) unique not null,
    password varchar(50)        not null,
    name     varchar(255)       not null,
    surname     varchar(255)       not null,
    email         varchar(255) not null
);


DROP TABLE IF EXISTS post;
CREATE TABLE post
(
    id           int primary key auto_increment,
    creator_id   int          not null,
    title        varchar(255) not null,
    text         varchar(255) not null,
    date_created datetime     not null,
    FOREIGN KEY (creator_id) REFERENCES user (id)
);

DROP TABLE IF EXISTS bound;
CREATE TABLE device_bound
(
    device_token varchar(255) primary key,
    user_id      int not null,
    FOREIGN KEY (user_id) REFERENCES user (id)
);

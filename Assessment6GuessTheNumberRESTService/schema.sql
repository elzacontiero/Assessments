create database bulls;

USE bulls;

create table game (
	id int auto_increment primary key,
	answer varchar(4) not null,
	gamestatus varchar(12) not null
);

create table round (
	id int auto_increment primary key,
    game_id int references game(id),
    attempt varchar(4) not null,
    result varchar(4) not null,
    tstamp datetime
);

create database bullstest;
use bullstest;
create table game (
	id int auto_increment primary key,
	answer varchar(4) not null,
	gamestatus varchar(12) not null
);

create table round (
	id int auto_increment primary key,
    game_id int references game(id),
    attempt varchar(4) not null,
    result varchar(4) not null,
    tstamp datetime
);

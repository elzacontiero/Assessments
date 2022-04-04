create database bulls;
select now();
USE bulls;

create table game (
	id int auto_increment primary key,
	answer varchar(4) not null,
	gamestatus int not null
);

create table round (
	id int auto_increment primary key,
    game_id int references game(id),
    attempt varchar(4) not null,
    tstamp datetime 
);

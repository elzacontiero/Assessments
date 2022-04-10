drop database superheroes;
create database superheroes;
use superheroes;

create table characters (
    id int auto_increment primary key,
    name varchar(80),
    description varchar(200),
    superpower varchar(80),
    -- H=hero; V=villain
    character_type enum('H','V')
);

create table organizations (
    id int auto_increment primary key,
    name varchar(80) not null,
    description varchar(120),
    address varchar(120)
);

-- Maps characters to organizations.
-- Maps characters to organizations.
create table characters_orgs_map (
    id int auto_increment primary key,
    character_id int,
    org_id int,
    foreign key (character_id) references characters(id) on delete cascade,
    foreign key (org_id) references organizations(id) on delete cascade
);

-- Records of sightings
create table recordings (
    id int auto_increment primary key,
    character_id int,
    address varchar(120),
    -- See http://wiki.gis.com/wiki/index.php/Decimal_degrees for required precision
    latitude decimal(8,6),
    longitude decimal(8,6),
    time_of_sight timestamp not null,
    foreign key (character_id) references characters(id) on delete cascade
);

-- drop table if exists spittle;
-- drop table if exists spitter;
--
-- create table spitter (
--   id identity,
--   username varchar(25) not null,
--   password varchar(25) not null,
--   fullName varchar(100) not null,
--   email varchar(50) not null,
--   updateByEmail boolean not null,
--   status varchar(10) not null
-- );
--
-- create table spittle (
--   id integer identity primary key,
--   spitter integer not null,
--   message varchar(2000) not null,
--   postedTime datetime not null,
--   foreign key (spitter) references spitter(id)
-- );

drop table if exists person;

CREATE TABLE person (
  id integer identity primary key,
  name varchar(2000) not null,
  age integer not null
);

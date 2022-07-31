create table if not exists post (
   id serial primary key,
   name TEXT,
   description text,
   created timestamp,
   visible boolean,
   city_id int
);


create table if not exists candidate (
   id serial primary key,
   name TEXT,
   description text,
   created timestamp,
   photo bytea,
   visible boolean
);

CREATE TABLE if not exists users (
  id SERIAL PRIMARY KEY,
  email TEXT UNIQUE,
  password TEXT
);
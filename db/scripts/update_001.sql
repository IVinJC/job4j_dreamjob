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

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  email TEXT,
  password TEXT
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email)
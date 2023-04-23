--liquibase formatted sql

--changeset agusev:1
CREATE TABLE IF NOT EXISTS users
(
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(64) NOT NULL UNIQUE,
  firstname VARCHAR(64),
  lastname VARCHAR(64),
  role VARCHAR(32)
);

--changeset agusev:2
CREATE TABLE IF NOT EXISTS movie
(
    id SERIAL PRIMARY KEY,
    title VARCHAR(64) NOT NULL UNIQUE,
    genre VARCHAR(32),
    release_date DATE
);

--changeset agusev:3
CREATE TABLE IF NOT EXISTS person
(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(64),
    lastname VARCHAR(64),
    birth_date DATE,
    role VARCHAR(32)
);

--changeset agusev:4
CREATE TABLE IF NOT EXISTS review
(
    id BIGSERIAL PRIMARY KEY,
    movie_id INT REFERENCES movie (id) NOT NULL,
    user_id BIGINT REFERENCES users (id) NOT NULL,
    text VARCHAR(128) NOT NULL,
    film_score INT NOT NULL
);

--changeset agusev:5
CREATE TABLE IF NOT EXISTS movie_person
(
    id BIGSERIAL PRIMARY KEY,
    movie_id INT NOT NULL REFERENCES movie (id) ON DELETE CASCADE,
    person_id BIGINT NOT NULL REFERENCES person (id) ON DELETE CASCADE
);
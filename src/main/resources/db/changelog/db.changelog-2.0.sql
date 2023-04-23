--liquibase formatted sql

--changeset agusev:1
INSERT INTO users (id, username, firstname, lastname, role)
VALUES (1, 'misha@gmail.com', 'Mihail', 'Taranov', 'USER'),
       (2, 'maxim@gmail.com', 'Maxim', 'Maximov', 'ADMIN'),
       (3, 'petr@gmail.com', 'Petr', 'Petrov', 'USER'),
       (4, 'sergey@gmail.com', 'Sergey', 'Sergeev', 'USER'),
       (5, 'dmitriy@gmail.com', 'Dmitriy', 'Dmitriev', 'USER');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

--changeset agusev:2
INSERT INTO movie (id, title, genre, release_date)
VALUES (1, 'The Matrix', 'ACTION', '1999-03-31'),
       (2, 'The Wolf of Wall Street', 'COMEDY', '2013-12-25'),
       (3, 'Interstellar', 'DRAMA', '2014-11-7'),
       (4, 'Fight Club', 'DRAMA', '1999-10-15'),
       (5, 'The Gentlemen', 'ACTION', '2020-01-01');
SELECT SETVAL('movie_id_seq', (SELECT MAX(id) FROM movie));

--changeset agusev:3
INSERT INTO person (id, firstname, lastname, birth_date, role)
VALUES (1, 'Keanu', 'Reeves', '1964-09-02', 'ACTOR'),
       (2, 'David', 'Silver', '1952-07-14', 'PRODUCER'),
       (3, 'Leonardo', 'DiCaprio', '1974-11-11', 'ACTOR'),
       (4, 'Martin', 'Scorsese', '1942-11-17', 'DIRECTOR'),
       (5, 'Matthew', 'McConaughey', '1969-11-04', 'ACTOR'),
       (6, 'Emma', 'Thomas', '1971-12-20', 'PRODUCER'),
       (7, 'Brad', 'Pitt', '1963-12-18', 'ACTOR'),
       (9, 'David', 'Fincher', '1962-08-28', 'ACTOR'),
       (10, 'Guy', 'Ritchie', '1968-09-10', 'PRODUCER');
SELECT SETVAL('person_id_seq', (SELECT MAX(id) FROM person));

--changeset agusev:4
INSERT INTO review (user_id, movie_id, text, film_score)
VALUES ((SELECT id FROM users where username = 'misha@gmail.com'), (SELECT id FROM movie where title = 'The Gentlemen'), 'Good', 8),
       ((SELECT id FROM users where username = 'misha@gmail.com'), (SELECT id FROM movie where title = 'The Wolf of Wall Street'), 'Fantastic', 9),
       ((SELECT id FROM users where username = 'maxim@gmail.com'), (SELECT id FROM movie where title = 'The Matrix'), 'Great', 8),
       ((SELECT id FROM users where username = 'maxim@gmail.com'), (SELECT id FROM movie where title = 'Interstellar'), 'Unbelivable', 10),
       ((SELECT id FROM users where username = 'petr@gmail.com'), (SELECT id FROM movie where title = 'Fight Club'), 'Not bad', 6),
       ((SELECT id FROM users where username = 'petr@gmail.com'), (SELECT id FROM movie where title = 'The Wolf of Wall Street'), 'Normal', 7),
       ((SELECT id FROM users where username = 'sergey@gmail.com'), (SELECT id FROM movie where title = 'The Matrix'), 'Bad', 5),
       ((SELECT id FROM users where username = 'sergey@gmail.com'), (SELECT id FROM movie where title = 'The Gentlemen'), 'So so', 6),
       ((SELECT id FROM users where username = 'dmitriy@gmail.com'), (SELECT id FROM movie where title = 'Interstellar'), 'Unreal', 10),
       ((SELECT id FROM users where username = 'dmitriy@gmail.com'), (SELECT id FROM movie where title = 'Fight Club'), 'Nice', 7);

--changeset agusev:5
INSERT INTO movie_person (movie_id, person_id)
VALUES ((SELECT id FROM movie where title = 'The Matrix'), (SELECT id FROM person where firstname = 'Keanu' AND lastname = 'Reeves')),
       ((SELECT id FROM movie where title = 'The Matrix'), (SELECT id FROM person where firstname = 'David' AND lastname = 'Silver')),
       ((SELECT id FROM movie where title = 'The Wolf of Wall Street'), (SELECT id FROM person where firstname = 'Leonardo' AND lastname = 'DiCaprio')),
       ((SELECT id FROM movie where title = 'The Wolf of Wall Street'), (SELECT id FROM person where firstname = 'Martin' AND lastname = 'Scorsese')),
       ((SELECT id FROM movie where title = 'Interstellar'), (SELECT id FROM person where firstname = 'Matthew' AND lastname = 'McConaughey')),
       ((SELECT id FROM movie where title = 'Interstellar'), (SELECT id FROM person where firstname = 'Emma' AND lastname = 'Thomas')),
       ((SELECT id FROM movie where title = 'Fight Club'), (SELECT id FROM person where firstname = 'Brad' AND lastname = 'Pitt')),
       ((SELECT id FROM movie where title = 'Fight Club'), (SELECT id FROM person where firstname = 'David' AND lastname = 'Fincher')),
       ((SELECT id FROM movie where title = 'The Gentlemen'), (SELECT id FROM person where firstname = 'Guy' AND lastname = 'Ritchie')),
       ((SELECT id FROM movie where title = 'The Gentlemen'), (SELECT id FROM person where firstname = 'Matthew' AND lastname = 'McConaughey'));

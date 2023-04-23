insert into users (id, username, firstname, lastname, role)
values (1, 'misha@gmail.com', 'Mihail', 'Taranov', 'USER'),
       (2, 'maxim@gmail.com', 'Maxim', 'Maximov', 'ADMIN'),
       (3, 'petr@gmail.com', 'Petr', 'Petrov', 'USER'),
       (4, 'sergey@gmail.com', 'Sergey', 'Sergeev', 'USER'),
       (5, 'dmitriy@gmail.com', 'Dmitriy', 'Dmitriev', 'USER');

insert into movie (id, title, genre, release_date)
values (1, 'The Matrix', 'ACTION', '1999-03-31'),
       (2, 'The Wolf of Wall Street', 'COMEDY', '2013-12-25'),
       (3, 'Interstellar', 'DRAMA', '2014-11-7'),
       (4, 'Fight Club', 'DRAMA', '1999-10-15'),
       (5, 'The Gentlemen', 'ACTION', '2020-01-01');

insert into person (id, firstname, lastname, birth_date, role)
values (1, 'Keanu', 'Reeves', '1964-09-02', 'ACTOR'),
       (2, 'David', 'Silver', '1952-07-14', 'PRODUCER'),
       (3, 'Leonardo', 'DiCaprio', '1974-11-11', 'ACTOR'),
       (4, 'Martin', 'Scorsese', '1942-11-17', 'DIRECTOR'),
       (5, 'Matthew', 'McConaughey', '1969-11-04', 'ACTOR'),
       (6, 'Emma', 'Thomas', '1971-12-20', 'PRODUCER'),
       (7, 'Brad', 'Pitt', '1963-12-18', 'ACTOR'),
       (8, 'David', 'Fincher', '1962-08-28', 'ACTOR'),
       (9, 'Guy', 'Ritchie', '1968-09-10', 'PRODUCER');

insert into review (id, user_id, movie_id, text, film_score)
values (1, (select id from users where username = 'misha@gmail.com'), (select id from movie where title = 'The Gentlemen'), 'Good', 8),
       (2, (select id from users where username = 'misha@gmail.com'), (select id from movie where title = 'The Wolf of Wall Street'), 'Fantastic', 9),
       (3, (select id from users where username = 'maxim@gmail.com'), (select id from movie where title = 'The Matrix'), 'Great', 8),
       (4, (select id from users where username = 'maxim@gmail.com'), (select id from movie where title = 'Interstellar'), 'Unbelivable', 10),
       (5, (select id from users where username = 'petr@gmail.com'), (select id from movie where title = 'Fight Club'), 'Not bad', 6),
       (6, (select id from users where username = 'petr@gmail.com'), (select id from movie where title = 'The Wolf of Wall Street'), 'Normal', 7),
       (7, (select id from users where username = 'sergey@gmail.com'), (select id from movie where title = 'The Matrix'), 'Bad', 5),
       (8, (select id from users where username = 'sergey@gmail.com'), (select id from movie where title = 'The Gentlemen'), 'So so', 6),
       (9, (select id from users where username = 'dmitriy@gmail.com'), (select id from movie where title = 'Interstellar'), 'Unreal', 10),
       (10, (select id from users where username = 'dmitriy@gmail.com'), (select id from movie where title = 'Fight Club'), 'Nice', 7);

insert into movie_person (movie_id, person_id)
values ((select id from movie where title = 'The Matrix'), (select id from person where firstname = 'Keanu' and lastname = 'Reeves')),
       ((select id from movie where title = 'The Matrix'), (select id from person where firstname = 'David' and lastname = 'Silver')),
       ((select id from movie where title = 'The Wolf of Wall Street'), (select id from person where firstname = 'Leonardo' and lastname = 'DiCaprio')),
       ((select id from movie where title = 'The Wolf of Wall Street'), (select id from person where firstname = 'Martin' and lastname = 'Scorsese')),
       ((select id from movie where title = 'Interstellar'), (select id from person where firstname = 'Matthew' and lastname = 'McConaughey')),
       ((select id from movie where title = 'Interstellar'), (select id from person where firstname = 'Emma' and lastname = 'Thomas')),
       ((select id from movie where title = 'Fight Club'), (select id from person where firstname = 'Brad' and lastname = 'Pitt')),
       ((select id from movie where title = 'Fight Club'), (select id from person where firstname = 'David' and lastname = 'Fincher')),
       ((select id from movie where title = 'The Gentlemen'), (select id from person where firstname = 'Guy' and lastname = 'Ritchie')),
       ((select id from movie where title = 'The Gentlemen'), (select id from person where firstname = 'Matthew' and lastname = 'McConaughey'));

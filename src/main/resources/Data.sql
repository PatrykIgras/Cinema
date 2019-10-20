INSERT INTO rooms (room_id, name, capacity, description) VALUES (1, 'Sala 1', 130, '10 rzędów po 13 miejsc');
INSERT INTO rooms (room_id, name, capacity, description) VALUES (2, 'Sala 2', 121, '11 rzędów po 11 miejsc');
INSERT INTO rooms (room_id, name, capacity, description) VALUES (3, 'Sala 3', 150, '10 rzędów po 15 miejsc');
INSERT INTO rooms (room_id, name, capacity, description) VALUES (4, 'Sala 4', 144, '12 rzędów po 12 miejsc');

INSERT INTO movies (movie_id, title, category, length, description, required_age) VALUES (5, 'After', 'ROMANTIC', 106, 'Ekranizacja powiesci...', 18);
INSERT INTO movies (movie_id, title, category, length, description, required_age) VALUES (6, 'Avengers: koniec gry', 'ACTION', 182, 'Życie w kosmosie...', 15);
INSERT INTO movies (movie_id, title, category, length, description, required_age) VALUES (7, 'Green book', 'COMEDY', 130, 'O takim jednym...', 15);
INSERT INTO movies (movie_id, title, category, length, description, required_age) VALUES (8, 'Hellboy', 'ACTION', 121, 'Człowiek z piekła...', 18);
INSERT INTO movies (movie_id, title, category, length, description, required_age) VALUES (9, 'Impostor', 'HORROR', 90, 'Koieta chcąc uciec od męża...', 18);
INSERT INTO movies (movie_id, title, category, length, description, required_age) VALUES (10, 'Przemytnik', 'DRAMA', 116, 'Western 21 wieku...', 15);
INSERT INTO movies (movie_id, title, category, length, description, required_age) VALUES (11, 'Niedobrani', 'COMEDY', 121, 'Trudno coś o tym napisać...', 15);

INSERT INTO posters (poster_id, movie_id, file_path) VALUES (12, 8, '/images/posterHellboy.png');
INSERT INTO posters (poster_id, movie_id, file_path) VALUES (13, 9, '/images/posterImpostor.png');
INSERT INTO posters (poster_id, movie_id, file_path) VALUES (14, 10, '/images/posterPrzemytnik.png');

INSERT INTO sessions (session_id, movie_id, room_id, start_time) VALUES (15, 8, 2, TIMESTAMP '2019-05-01 12:00:00');
INSERT INTO sessions (session_id, movie_id, room_id, start_time) VALUES (16, 8, 1, TIMESTAMP '2019-05-02 18:00:00');
INSERT INTO sessions (session_id, movie_id, room_id, start_time) VALUES (17, 10, 3, TIMESTAMP '2019-05-02 21:15:00');

INSERT INTO tickets (ticket_id, session_id, seat, price) VALUES (18, 15, 'r10m2', 21.00);
INSERT INTO tickets (ticket_id, session_id, seat, price) VALUES (19, 15, 'r8m1', 21.00);
INSERT INTO tickets (ticket_id, session_id, seat, price) VALUES (20, 15, 'r8m2', 18.00);
INSERT INTO tickets (ticket_id, session_id, seat, price) VALUES (21, 15, 'r5m11', 18.00);

SELECT setval('public.hibernate_sequence', 21, true);
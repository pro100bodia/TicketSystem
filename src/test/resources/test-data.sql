INSERT INTO customer (nickname, password, first_name, last_name, email, role) VALUES
  ('test-serhiilytka', '$2a$04$sBdQjNR9AlTv1XG32g.0LO8Zfoq9QDhA02vKn07AMvRZsdzU.gJgq', 'Serhii', 'Lytka', 'serhii@gmail.com', 'ROLE_ADMIN'),
  ('test-marypublic', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'Mary', 'Public', 'mary@gmail.com', 'ROLE_USER'),
  ('test-johndou', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'John', 'Dou', 'john@gmail.com', 'ROLE_USER'),
  ('test-bohdanpukhalskyi', '$2a$04$sBdQjNR9AlTv1XG32g.0LO8Zfoq9QDhA02vKn07AMvRZsdzU.gJgq', 'Bohdan', 'Pukhalskyi', 'bohdan@gmail.com', 'ROLE_ADMIN'),
  ('test-becherovka', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'Joan', 'Becher', 'becherovka@gmail.com', 'ROLE_USER'),
  ('test-heidy', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'Heidemarie', 'Stefanyshyn-Piper', 'heidy@gmail.com', 'ROLE_USER');


INSERT INTO ticket (bought_at, `tier`, seat, price) VALUES
  ('2020-05-30 12:00:00', 1, 1, 12.0),
  ('2020-05-30 12:30:00', 1, 2, 12.0),
  ('2020-05-30 13:00:00', 2, 3, 11.0),
  ('2020-05-30 13:30:00', 5, 4, 10.0);

INSERT INTO place(title, country, state, city, street, building, places_num) VALUES
  ('test-Ukraina Palace', 'Ukraine', 'Kyivska', 'Kyiv', 'Velyka Vasylkivska St', '103', 20000),
  ('test-Arena Lviv', 'Ukraine', 'Lvivska', 'Lviv', 'Stryiska St', '199', 20000),
  ('test-Motoball Stadium', 'Ukraine', 'Khmelnytska', 'Kamianets-Podilskyi', 'Prospect Hrushevskoho', '31', 200),
  ('test-NAU Stadium', 'Ukraine', 'Kyivska', 'Kyiv', 'Liubomyra Huzara Ave.', '1', 100);

INSERT INTO event(title, created_at, occurred_at, place_id) VALUES
  ('test-motoball: Podillia - Zoria', '2020-05-30T17:00:00', '2020-07-15T17:00:00', 3),
  ('test-motoball: Podillia - Voshod', '2020-05-30T17:00:00', '2020-07-18T17:00:00', 3),
  ('test-alyona-alyona grand tour', '2020-05-30T17:00:00', '2020-08-24T17:00:00', 2),
  ('test-basketball: Kyiv-Basket - Cherkaski-Mavpy', '2020-05-30T17:00:00', '2020-07-23T17:00:00', 4);

INSERT INTO ticket_user (user_id, ticket_id) VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (3, 3);

INSERT INTO ticket_event (ticket_id, event_id) VALUES
  (1, 1),
  (1, 2),
  (3, 3),
  (4, 4);

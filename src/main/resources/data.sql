INSERT INTO "user" ("nickname", "password", "first_name", "last_name", "email", "role") VALUES
  ('serhiilytka', '$2a$04$sBdQjNR9AlTv1XG32g.0LO8Zfoq9QDhA02vKn07AMvRZsdzU.gJgq', 'Serhii', 'Lytka', 'serhii@gmail.com', 'ROLE_ADMIN'),
  ('marypublic', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'Mary', 'Public', 'mary@gmail.com', 'ROLE_USER'),
  ('johndou', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'John', 'Dou', 'john@gmail.com', 'ROLE_USER'),
  ('bohdanpukhalskyi', '$2a$04$sBdQjNR9AlTv1XG32g.0LO8Zfoq9QDhA02vKn07AMvRZsdzU.gJgq', 'Bohdan', 'Pukhalskyi', 'bohdan@gmail.com', 'ROLE_ADMIN'),
  ('becherovka', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'Joan', 'Becher', 'becherovka@gmail.com', 'ROLE_USER'),
  ('heidy', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'Heidemarie', 'Stefanyshyn-Piper', 'heidy@gmail.com', 'ROLE_USER');


INSERT INTO "ticket" ("bought_at", "tier", "seat", "price") VALUES
  ('2020-05-30 12:00:00', 1, 1, 12.0),
  ('2020-05-30 12:30:00', 1, 2, 12.0),
  ('2020-05-30 13:00:00', 2, 3, 11.0),
  ('2020-05-30 13:30:00', 5, 4, 10.0);

INSERT INTO "place"("title", "country", "state", "city", "street", "building", "places_num") VALUES
  ('Ukraina Palace', 'Ukraine', 'Kyivska', 'Kyiv', 'Velyka Vasylkivska St', '103', 20000),
  ('Arena Lviv', 'Ukraine', 'Lvivska', 'Lviv', 'Stryiska St', '199', 20000),
  ('Motoball Stadium', 'Ukraine', 'Khmelnytska', 'Kamianets-Podilskyi', 'Prospect Hrushevskoho', '31', 200),
  ('NAU Stadium', 'Ukraine', 'Kyivska', 'Kyiv', 'Liubomyra Huzara Ave.', '1', 100);

INSERT INTO "event"("title", "created_at", "occurred_at", "place_id") VALUES
  ('motoball: Podillia - Zoria', '2020-05-30', '2020-07-15', 3),
  ('motoball: Podillia - Voshod', '2020-05-30', '2020-07-18', 3),
  ('alyona-alyona grand tour', '2020-05-30', '2020-08-24', 2),
  ('basketball: Kyiv-Basket - Cherkaski-Mavpy', '2020-05-30', '2020-07-23', 4);

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

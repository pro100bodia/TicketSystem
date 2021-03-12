DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS "ticket" CASCADE;
DROP TABLE IF EXISTS "event" CASCADE;
DROP TABLE IF EXISTS "place" CASCADE;

DROP TABLE IF EXISTS "ticket_user" CASCADE;
DROP TABLE IF EXISTS "ticket_event" CASCADE;

CREATE TABLE "custormer" (
    "id" SERIAL,
    "nickname" VARCHAR(255) NOT NULL,
    "first_name" VARCHAR(255),
    "last_name" VARCHAR(255),
    "password" VARCHAR(255),
    "email" VARCHAR(255),
    "role" VARCHAR(255),
    PRIMARY KEY (id));

CREATE TABLE "ticket" (
    "id" SERIAL,
    "bought_at" TIMESTAMP,
    "tier" INT,
    "seat" INT,
    "price" DOUBLE PRECISION,
    PRIMARY KEY (id));

CREATE TABLE "event" (
    "id" SERIAL,
    "title" VARCHAR(255),
    "created_at" TIMESTAMP,
    "occurred_at" TIMESTAMP,
    "place_id" BIGINT,
    PRIMARY KEY (id));

CREATE TABLE "place" (
    "id" SERIAL,
    "title" VARCHAR(255),
    "country" VARCHAR(255),
    "city" VARCHAR(255),
    "state" VARCHAR(255),
    "street" VARCHAR(255),
    "building" VARCHAR(255),
    "places_num" BIGINT,
    PRIMARY KEY (id));


CREATE TABLE "ticket_user" ("user_id" BIGINT NOT NULL, "ticket_id" BIGINT NOT NULL, PRIMARY KEY ("user_id", "ticket_id"));
ALTER TABLE "ticket_user" ADD CONSTRAINT TicketUserForeignKey FOREIGN KEY ("ticket_id") REFERENCES "ticket";
ALTER TABLE "ticket_user" ADD CONSTRAINT UserTicketForeignKey FOREIGN KEY ("user_id") REFERENCES "customer";

CREATE TABLE "ticket_event" ("ticket_id" BIGINT NOT NULL, "event_id" BIGINT NOT NULL, PRIMARY KEY ("ticket_id", "event_id"));
ALTER TABLE "ticket_event" ADD CONSTRAINT TicketEventForeignKey FOREIGN KEY ("ticket_id") REFERENCES "ticket";
ALTER TABLE "ticket_event" ADD CONSTRAINT EventTicketForeignKey FOREIGN KEY ("event_id") REFERENCES "event";

ALTER TABLE "event" ADD CONSTRAINT PlaceFOREIGNKEY FOREIGN KEY ("place_id") REFERENCES "place";
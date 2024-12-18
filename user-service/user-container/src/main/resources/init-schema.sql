DROP SCHEMA IF EXISTS "user" CASCADE;

CREATE SCHEMA "user";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS "user".warehouses CASCADE;
CREATE TABLE "user".warehouses
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    address_street character varying COLLATE pg_catalog."default" NOT NULL,
    address_city character varying COLLATE pg_catalog."default" NOT NULL,
    address_postal_code character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT warehouses_pkey PRIMARY KEY (id)
);

DROP TYPE IF EXISTS user_role;
CREATE TYPE user_role AS ENUM ('CUSTOMER', 'SUPER_ADMIN', 'WAREHOUSE_ADMIN');

DROP TABLE IF EXISTS "user".users CASCADE;
CREATE TABLE "user".users
(
    id uuid NOT NULL,
    role user_role NOT NULL,
    warehouse_id uuid,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
ALTER TABLE "user".users
    ADD CONSTRAINT "FK_WAREHOUSE_ID" FOREIGN KEY (warehouse_id)
        REFERENCES "user".warehouses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

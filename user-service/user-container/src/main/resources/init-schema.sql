DROP SCHEMA IF EXISTS "user" CASCADE;

CREATE SCHEMA "user";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS user_role;
CREATE TYPE user_role AS ENUM ('CUSTOMER', 'SUPER_ADMIN', 'WAREHOUSE_ADMIN');

DROP TABLE IF EXISTS "user".warehouses CASCADE;
CREATE TABLE "user".warehouses
(
    id uuid NOT NULL,
    code CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    name CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT warehouses_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "user".users CASCADE;
CREATE TABLE "user".users
(
    id UUID NOT NULL,
    username CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    email CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    password CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    role user_role NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "user".user_profiles CASCADE;
CREATE TABLE "user".user_profiles
(
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    firstname CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    lastname CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    date_of_birth DATE NOT NULL,
    phone_number CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    profile_picture CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_profiles_pkey PRIMARY KEY (id)
);
ALTER TABLE "user".user_profiles
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES "user".users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS "user".user_addresses CASCADE;
CREATE TABLE "user".user_addresses
(
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    street CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    city CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    postal_code CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    CONSTRAINT user_addresses_pkey PRIMARY KEY (id)
);
ALTER TABLE "user".user_addresses
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES "user".users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS "user".user_admin_warehouses CASCADE;
CREATE TABLE "user".user_admin_warehouses
(
    id UUID NOT NULL,
    user_id UUID NOT NULL,
    warehouse_id UUID NOT NULL,
    CONSTRAINT admins_pkey PRIMARY KEY (id)
);
ALTER TABLE "user".user_admin_warehouses
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES "user".users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
ALTER TABLE "user".user_admin_warehouses
    ADD CONSTRAINT fk_warehouse_id FOREIGN KEY (warehouse_id)
        REFERENCES "user".warehouses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
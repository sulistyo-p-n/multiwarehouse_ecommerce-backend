DROP SCHEMA IF EXISTS "warehouse" CASCADE;
CREATE SCHEMA "warehouse";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- "warehouse".warehouses
DROP TABLE IF EXISTS "warehouse".warehouses CASCADE;
CREATE TABLE "warehouse".warehouses
(
    id uuid NOT NULL,
    code CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    name CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    description TEXT,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT warehouses_pkey PRIMARY KEY (id)
);

-- "warehouse".warehouse_addresses
DROP TABLE IF EXISTS "warehouse".warehouse_addresses CASCADE;
CREATE TABLE "warehouse".warehouse_addresses
(
    id uuid NOT NULL,
    warehouse_id uuid NOT NULL,
    street CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    city CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    postal_code CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    CONSTRAINT warehouse_addresses_pkey PRIMARY KEY (id)
);
ALTER TABLE "warehouse".warehouse_addresses
    ADD CONSTRAINT fk_warehouse_id FOREIGN KEY (warehouse_id)
        REFERENCES "warehouse".warehouses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
DROP SCHEMA IF EXISTS "warehouse" CASCADE;
CREATE SCHEMA "warehouse";

DROP SCHEMA IF EXISTS "warehouse-test" CASCADE;
CREATE SCHEMA "warehouse-test";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS "warehouse".warehouses CASCADE;
CREATE TABLE "warehouse".warehouses
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

DROP TABLE IF EXISTS "warehouse".users CASCADE;
CREATE TABLE "warehouse".users
(
    id uuid NOT NULL,
    role user_role NOT NULL,
    warehouse_id uuid,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);
ALTER TABLE "warehouse".users
    ADD CONSTRAINT "FK_WAREHOUSE_ID" FOREIGN KEY (warehouse_id)
        REFERENCES "warehouse".warehouses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS "warehouse".product_categories CASCADE;
CREATE TABLE "warehouse".product_categories
(
    id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT product_categories_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "warehouse".products CASCADE;
CREATE TABLE "warehouse".products
(
    id uuid NOT NULL,
    product_category_id uuid NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default" NOT NULL,
    price numeric(10,2) NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);
ALTER TABLE "warehouse".products
    ADD CONSTRAINT "FK_PRODUCT_CATEGORY_ID" FOREIGN KEY (product_category_id)
        REFERENCES "warehouse".product_categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

DROP TABLE IF EXISTS "warehouse".stocks CASCADE;
CREATE TABLE "warehouse".stocks
(
    id uuid NOT NULL,
    warehouse_id uuid NOT NULL,
    product_id uuid NOT NULL,
    quantity integer NOT NULL,
    CONSTRAINT stocks_pkey PRIMARY KEY (id)
);
ALTER TABLE "warehouse".stocks
    ADD CONSTRAINT "FK_WAREHOUSE_ID" FOREIGN KEY (warehouse_id)
        REFERENCES "warehouse".warehouses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
ALTER TABLE "warehouse".stocks
    ADD CONSTRAINT "FK_PRODUCT_ID" FOREIGN KEY (product_id)
        REFERENCES "warehouse".products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
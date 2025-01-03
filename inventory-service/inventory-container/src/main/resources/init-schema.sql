DROP SCHEMA IF EXISTS "inventory" CASCADE;
CREATE SCHEMA "inventory";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- "inventory".products
DROP TABLE IF EXISTS "inventory".products CASCADE;
CREATE TABLE "inventory".products
(
    id UUID NOT NULL,
    product_category_id UUID NOT NULL,
    code CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    name CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);

-- "inventory".warehouses
DROP TABLE IF EXISTS "inventory".warehouses CASCADE;
CREATE TABLE "inventory".warehouses
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

-- "inventory".inventories
DROP TABLE IF EXISTS "inventory".inventories CASCADE;
CREATE TABLE "inventory".inventories
(
    id UUID NOT NULL,
    warehouse_id UUID NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT inventories_pkey PRIMARY KEY (id)
);
ALTER TABLE "inventory".inventories
    ADD CONSTRAINT fk_warehouse_id FOREIGN KEY (warehouse_id)
        REFERENCES "inventory".warehouses (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

-- "inventory".product_stocks
DROP TABLE IF EXISTS "inventory".product_stocks CASCADE;
CREATE TABLE "inventory".product_stocks
(
    id UUID NOT NULL,
    inventory_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT product_stocks_pkey PRIMARY KEY (id)
);
ALTER TABLE "inventory".product_stocks
    ADD CONSTRAINT fk_inventory_id FOREIGN KEY (inventory_id)
        REFERENCES "inventory".inventories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
ALTER TABLE "inventory".product_stocks
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
        REFERENCES "inventory".products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

-- "inventory".stock_journal_type
DROP TYPE IF EXISTS stock_journal_type;
CREATE TYPE stock_journal_type AS ENUM ('ADDICTION', 'REDUCTION');

-- "inventory".stock_journals
DROP TABLE IF EXISTS "inventory".stock_journals CASCADE;
CREATE TABLE "inventory".stock_journals
(
    id UUID NOT NULL,
    product_stock_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    type stock_journal_type NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT stock_journals_pkey PRIMARY KEY (id)
);
ALTER TABLE "inventory".stock_journals
    ADD CONSTRAINT fk_product_stock_id FOREIGN KEY (product_stock_id)
        REFERENCES "inventory".product_stocks (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

-- "inventory".stock_mutation_status
DROP TYPE IF EXISTS stock_mutation_status;
CREATE TYPE stock_mutation_status AS ENUM ('PENDING', 'APPROVED', 'REJECTED');

-- "inventory".stock_mutations
DROP TABLE IF EXISTS "inventory".stock_mutations CASCADE;
CREATE TABLE "inventory".stock_mutations
(
    id UUID NOT NULL,
    source_inventory_id UUID NOT NULL,
    target_inventory_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    status stock_mutation_status NOT NULL,
    CONSTRAINT stock_mutations_pkey PRIMARY KEY (id)
);
ALTER TABLE "inventory".stock_mutations
    ADD CONSTRAINT fk_source_inventory_id FOREIGN KEY (source_inventory_id)
        REFERENCES "inventory".inventories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
ALTER TABLE "inventory".stock_mutations
    ADD CONSTRAINT fk_target_inventory_id FOREIGN KEY (target_inventory_id)
        REFERENCES "inventory".inventories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
ALTER TABLE "inventory".stock_mutations
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
        REFERENCES "inventory".products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;
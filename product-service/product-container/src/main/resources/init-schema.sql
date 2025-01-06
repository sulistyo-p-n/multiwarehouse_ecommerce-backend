DROP SCHEMA IF EXISTS "product" CASCADE;
CREATE SCHEMA "product";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- "product".product_categories
DROP TABLE IF EXISTS "product".product_categories CASCADE;
CREATE TABLE "product".product_categories
(
    id UUID NOT NULL,
    code CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    name CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    description TEXT,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT product_categories_pkey PRIMARY KEY (id)
);

-- "product".products
DROP TABLE IF EXISTS "product".products CASCADE;
CREATE TABLE "product".products
(
    id UUID NOT NULL,
    product_category_id UUID NOT NULL,
    code CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    name CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    description TEXT,
    price NUMERIC(10,2) NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);
ALTER TABLE "product".products
    ADD CONSTRAINT fk_product_category_id FOREIGN KEY (product_category_id)
        REFERENCES "product".product_categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

-- "product".product_images
DROP TABLE IF EXISTS "product".product_images CASCADE;
CREATE TABLE "product".product_images
(
    id UUID NOT NULL,
    product_id UUID NOT NULL,
    name CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    path CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    description TEXT,
    front BOOLEAN DEFAULT TRUE NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    CONSTRAINT product_images_pkey PRIMARY KEY (id)
);
ALTER TABLE "product".product_images
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id)
        REFERENCES "product".products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

-- "product".inventories
DROP TABLE IF EXISTS "product".inventories CASCADE;
CREATE TABLE "product".inventories
(
    id UUID NOT NULL,
    warehouse_id UUID NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT inventories_pkey PRIMARY KEY (id)
);

-- "product".inventory_stocks
DROP TABLE IF EXISTS "product".inventory_stocks CASCADE;
CREATE TABLE "product".inventory_stocks
(
    id UUID NOT NULL,
    inventory_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT inventory_stocks_pkey PRIMARY KEY (id)
);
ALTER TABLE "product".inventory_stocks
    ADD CONSTRAINT fk_inventory_id FOREIGN KEY (inventory_id)
        REFERENCES "product".inventories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

-- "product".product_stocks_m_view
DROP MATERIALIZED VIEW IF EXISTS "product".product_stocks_m_view;
CREATE MATERIALIZED VIEW "product".product_stocks_m_view
TABLESPACE pg_default
AS
    SELECT
        p.id AS id,
        p.name AS name,
        p.code AS code,
        COALESCE(SUM(s.quantity), 0) AS quantity
    FROM
        "product".products p
    LEFT JOIN
        "product".inventory_stocks s
        ON p.id = s.product_id
    LEFT JOIN
        "product".inventories i
        ON s.inventory_id = i.id
	WHERE
		i.active = TRUE
	AND
		i.deleted_at IS NULL
    GROUP BY
        p.id
    ORDER BY
        p.id
WITH DATA;
refresh materialized VIEW "product".product_stocks_m_view;

-- "product".refresh_product_stocks_m_view
DROP function IF EXISTS "product".refresh_product_stocks_m_view;
CREATE OR replace function "product".refresh_product_stocks_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW "product".product_stocks_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

-- "product".trigger_refresh_product_stocks_m_view_on_products
DROP trigger IF EXISTS trigger_refresh_product_stocks_m_view_on_products ON "product".products;
CREATE trigger trigger_refresh_product_stocks_m_view_on_products
after INSERT OR UPDATE OR DELETE OR truncate
ON "product".products FOR each statement
EXECUTE PROCEDURE "product".refresh_product_stocks_m_view();

-- "product".trigger_refresh_product_stocks_m_view_on_inventory_stocks
DROP trigger IF EXISTS trigger_refresh_product_stocks_m_view_on_inventory_stocks ON "product".inventory_stocks;
CREATE trigger trigger_refresh_product_stocks_m_view_on_inventory_stocks
after INSERT OR UPDATE OR DELETE OR truncate
ON "product".inventory_stocks FOR each statement
EXECUTE PROCEDURE "product".refresh_product_stocks_m_view();

-- "product".trigger_refresh_product_stocks_m_view_on_inventory_stocks
DROP trigger IF EXISTS trigger_refresh_product_stocks_m_view_on_inventories ON "product".inventories;
CREATE trigger trigger_refresh_product_stocks_m_view_on_inventories
after INSERT OR UPDATE OR DELETE OR truncate
ON "product".inventories FOR each statement
EXECUTE PROCEDURE "product".refresh_product_stocks_m_view();
DROP SCHEMA IF EXISTS "auth" CASCADE;

CREATE SCHEMA "auth";

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TYPE IF EXISTS user_role;
CREATE TYPE user_role AS ENUM ('CUSTOMER', 'SUPER_ADMIN', 'WAREHOUSE_ADMIN');

DROP TABLE IF EXISTS "auth".users CASCADE;
CREATE TABLE "auth".users
(
    id UUID NOT NULL,
    username CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    email CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL UNIQUE,
    password CHARACTER VARYING COLLATE pg_catalog."default" NOT NULL,
    role user_role NOT NULL,
    enable BOOLEAN DEFAULT FALSE NOT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "auth".user_admin_warehouses CASCADE;
CREATE TABLE "auth".user_admin_warehouses
(
    id UUID NOT NULL,
    user_id UUID NOT NULL UNIQUE,
    warehouse_id UUID NOT NULL UNIQUE,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    CONSTRAINT admins_pkey PRIMARY KEY (id)
);
ALTER TABLE "auth".user_admin_warehouses
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES "auth".users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
    NOT VALID;

INSERT INTO "auth".users (id, username, email, password, role)
VALUES ('9cc9f3fa-bc72-462f-86eb-82d32418bece', 'superadmin', 'superadmin@ikiae.com', '{bcrypt}$2a$10$r9qqLybMBC5yz1oDledQteFOkhrqJRKTnSPfi79hZhFtnW4wt3vSO', 'SUPER_ADMIN');
CREATE TABLE users
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(30)        NOT NULL,
    last_name VARCHAR(30)        NOT NULL,
    email     VARCHAR(50) UNIQUE NOT NULL,
    age       INTEGER             NOT NULL
);
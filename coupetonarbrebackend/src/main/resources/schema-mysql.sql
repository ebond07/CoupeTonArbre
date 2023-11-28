USE `coupetonarbre-db`;

-- to be modified
create table if not exists clients (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id VARCHAR(36),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50),
    address VARCHAR(50),
    postal_code VARCHAR (9)
);


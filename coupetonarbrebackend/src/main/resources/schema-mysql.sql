USE `coupetonarbre-db`;

-- to be modified
CREATE TABLE IF NOT EXISTS clients (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    client_id VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(25) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS quote_requests (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    quote_request_id VARCHAR(255) NOT NULL,
    client_id VARCHAR(255) NOT NULL,
    client_first_name VARCHAR(50) NOT NULL,
    client_last_name VARCHAR(50) NOT NULL,
    time VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    price DOUBLE,
    description VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    service VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL
)

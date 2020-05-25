CREATE TABLE message (
    id SERIAL PRIMARY KEY,
    message VARCHAR(200) NOT NULL,
    date VARCHAR(25) NOT NULL,
    flag int NOT NULL
);
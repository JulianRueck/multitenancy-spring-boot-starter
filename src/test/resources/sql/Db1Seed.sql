CREATE TABLE customer (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO customer (name) VALUES ('TestCustomer1');
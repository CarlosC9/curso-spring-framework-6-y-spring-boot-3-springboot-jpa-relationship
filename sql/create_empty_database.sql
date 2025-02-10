DROP DATABASE IF EXISTS db_jpa_relationship;
CREATE DATABASE db_jpa_relationship;

USE db_jpa_relationship;

CREATE TABLE clients
(
    id       bigint not null auto_increment,
    name     varchar(255),
    lastname varchar(255),
    primary key (id)
);

CREATE TABLE invoices
(
    id          bigint not null auto_increment,
    description varchar(255),
    total       bigint,
    primary key (id)
);

ALTER TABLE invoices
    ADD column client_id bigint not null,
    ADD FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE;
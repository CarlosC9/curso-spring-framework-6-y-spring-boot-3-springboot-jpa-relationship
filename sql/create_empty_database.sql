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

CREATE TABLE addresses
(
  id     bigint not null auto_increment,
  street varchar(255),
  number int,
  primary key (id)
);

CREATE TABLE client_details
(
  id      bigint not null auto_increment,
  points  int,
  premium boolean,
  primary key (id)
);

CREATE TABLE students
(
  id       bigint not null auto_increment,
  name     varchar(255),
  lastname varchar(255),
  primary key (id)
);

CREATE TABLE courses
(
  id       bigint not null auto_increment,
  name     varchar(255),
  instructor varchar(255),
  primary key (id)
);

CREATE TABLE students_courses
(
  course_id bigint not null,
  student_id bigint not null,
  primary key (course_id, student_id),
  foreign key (course_id) REFERENCES courses(id) ON DELETE CASCADE,
  foreign key (student_id) REFERENCES students(id) ON DELETE CASCADE
);

ALTER TABLE invoices
  ADD column client_id bigint not null,
  ADD FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE;

ALTER TABLE addresses
  ADD COLUMN client_id bigint not null,
  ADD FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE;

ALTER TABLE client_details
  ADD COLUMN client_id bigint not null,
  ADD FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE CASCADE;
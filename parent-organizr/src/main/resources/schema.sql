CREATE TABLE IF NOT EXISTS Class_list
(
    id           INTEGER AUTO_INCREMENT,
    class_number varchar(255) NOT NULL,
    class_letter varchar(255) NOT NULL,
    class_name   varchar(255) NOT NULL,
    start_year   varchar(255) NOT NULL,
    filename     varchar(255) NOT NULL,
    date_created TIMESTAMP    NOT NULL,
    date_updated TIMESTAMP,
    primary key (id)
);

INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('1', 'a', '1a', '2020', '1A_2020', CURRENT_TIMESTAMP);
INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('1', 'b', '1b', '2020', '1B_2020', CURRENT_TIMESTAMP);
INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('2', 'a', '2a', '2020', '2A_2020', CURRENT_TIMESTAMP);
INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('2', 'b', '2b', '2020', '2B_2020', CURRENT_TIMESTAMP);
INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('3', 'a', '3a', '2020', '3A_2020', CURRENT_TIMESTAMP);

-- CREATE TABLE IF NOT EXISTS Person
-- (
--     id           INTEGER AUTO_INCREMENT,
--     first_name   varchar(255) NOT NULL,
--     last_name    varchar(255) NOT NULL,
--     date_created datetime DEFAULT (NOW()) NOT NULL,
--     date_updated TIMESTAMP,
--     primary key (id),
-- );

-- INSERT INTO Person (first_name, last_name, date_created)
-- VALUES ('John', 'Hutch', CURRENT_TIMESTAMP);
-- INSERT INTO Person (first_name, last_name, date_created)
-- VALUES ('Jane', 'Hutch', CURRENT_TIMESTAMP);
-- INSERT INTO Person (first_name, last_name, date_created)
-- VALUES ('Jack', 'Hutch', CURRENT_TIMESTAMP);
-- INSERT INTO Person (first_name, last_name, date_created)
-- VALUES ('Jill', 'Hutch', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Parent
(
    id           INTEGER AUTO_INCREMENT,
    first_name   varchar(255)             NOT NULL,
    last_name    varchar(255)             NOT NULL,
    email        varchar(255),
    address      varchar(255),
    phone_number varchar(255),
    class_id     INTEGER,
    date_created datetime DEFAULT (NOW()) NOT NULL,
    date_updated TIMESTAMP,
    foreign key (class_id) references Class_list (id)
);
-- INSERT INTO Parent (id, first_name, last_name, email, address, phone_number, date_created)
-- VALUES (1, 'John', 'Hutch', 'john@hutch.com', '123 Main St', '555-555-5555', CURRENT_TIMESTAMP);
-- INSERT INTO Parent (id, first_name, last_name, email, address, phone_number, date_created)
-- VALUES (2, 'Jane', 'Hutch', 'jane@hutch.com', '123 Main St', '555-555-4444', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Pupil
(
    id           INTEGER AUTO_INCREMENT,
    first_name   varchar(255)             NOT NULL,
    last_name    varchar(255)             NOT NULL,
    class_id     INTEGER,
    birthdate    DATE,
    date_created datetime DEFAULT (NOW()) NOT NULL,
    date_updated TIMESTAMP,
    primary key (id),
    --foreign key (id) references Person (id),
    foreign key (class_id) references Class_list (id)
);
--
-- // Insert children
INSERT INTO Pupil (id, first_name, last_name, class_id, birthdate, date_created)
VALUES (3, 'Nancy', 'Drew', 1, '2015-01-01', CURRENT_TIMESTAMP);
INSERT INTO Pupil (id, first_name, last_name, class_id, birthdate, date_created)
VALUES (4, 'Tom', 'Waits', 1, '2016-04-08', CURRENT_TIMESTAMP);
--
-- CREATE TABLE IF NOT EXISTS ParentContract
-- (
--     id           INTEGER AUTO_INCREMENT,
--     parent_id    INTEGER,
--     child_id     INTEGER,
--     date_created TIMESTAMP    NOT NULL,
--     date_updated TIMESTAMP,
--     primary key (id),
--     foreign key (parent_id) references Parent (id),
--     foreign key (child_id) references Child (id)
-- );
--
-- INSERT INTO ParentContract (parent_id, child_id, date_created)
-- VALUES (1, 3, CURRENT_TIMESTAMP);
-- INSERT INTO ParentContract (parent_id, child_id, date_created)
-- VALUES (1, 4, CURRENT_TIMESTAMP);
-- INSERT INTO ParentContract (parent_id, child_id, date_created)
-- VALUES (2, 4, CURRENT_TIMESTAMP);
-- INSERT INTO ParentContract (parent_id, child_id, date_created)
-- VALUES (2, 3, CURRENT_TIMESTAMP);
--
-- CREATE TABLE IF NOT EXISTS Watch (
--     id INTEGER AUTO_INCREMENT,
--     time_slot varchar(255) NOT NULL,
--     assignment varchar(255) NOT NULL,
--     date_created datetime DEFAULT (NOW()) NOT NULL,
--     date_updated TIMESTAMP,
--     primary key (time_slot, assignment),
--     --foreign key (time_slot) references Time_slot (id),
--     --foreign key (assignment) references Assignment (id)
--
-- );
--
-- CREATE TABLE IF NOT EXISTS Time_slot (
--     id INTEGER AUTO_INCREMENT,
--     start_time TIME NOT NULL,
--     end_time TIME NOT NULL,
--     time_slot_name varchar(255) NOT NULL,
--     date_created datetime DEFAULT (NOW()) NOT NULL,
--     date_updated TIMESTAMP,
--     primary key (id)
-- );
--
-- CREATE TABLE IF NOT EXISTS Assignment (
--     id INTEGER AUTO_INCREMENT,
--     assignment_name varchar(255) NOT NULL,
--     date_created datetime DEFAULT (NOW()) NOT NULL,
--     date_updated TIMESTAMP,
--     primary key (id)
-- );

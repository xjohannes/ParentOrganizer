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
VALUES ('2', 'a', '2a', '2020', '2A_2019', CURRENT_TIMESTAMP);
INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('2', 'b', '2b', '2020', '2B_2019', CURRENT_TIMESTAMP);
INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('3', 'a', '3a', '2020', '3A_2018', CURRENT_TIMESTAMP);
INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('3', 'b', '3b', '2020', '3B_2018', CURRENT_TIMESTAMP);
INSERT INTO Class_list (class_number, class_letter, class_name, start_year, filename, date_created)
VALUES ('10', '-', '10', '2020', '10_2011', CURRENT_TIMESTAMP);


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
    date_created datetime DEFAULT (NOW()) NOT NULL,
    date_updated TIMESTAMP,
    primary key (id)
    -- foreign key (class_id) references Class_list (id)
);
INSERT INTO Parent (id, first_name, last_name, email, address, phone_number, date_created)
VALUES (1, 'John', 'Hutch', 'john@hutch.com', '123 Main St', '555-555-5555', CURRENT_TIMESTAMP);
INSERT INTO Parent (id, first_name, last_name, email, address, phone_number, date_created)
VALUES (2, 'Jane', 'Hutch', 'jane@hutch.com', '123 Main St', '555-555-4444', CURRENT_TIMESTAMP);
INSERT INTO Parent (id, first_name, last_name, email, address, phone_number, date_created)
VALUES (3, 'Lisa', 'Danielson', 'lisa@)lisandra.com', 'Gml Gravs vei 17', '920 63 000', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Pupil
(
    id           INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    first_name   varchar(255)                  NOT NULL,
    last_name    varchar(255)                  NOT NULL,
    class_id     INTEGER,
    birthdate    DATE,
    date_created datetime DEFAULT (NOW())      NOT NULL,
    date_updated TIMESTAMP,
    primary key (id),
    foreign key (class_id) references Class_list (id)
);
--
-- // Insert children
INSERT INTO Pupil (id, first_name, last_name, class_id, birthdate, date_created)
VALUES (3, 'Nancy', 'Drew', 1, '2016-01-01', CURRENT_TIMESTAMP);
INSERT INTO Pupil (id, first_name, last_name, class_id, birthdate, date_created)
VALUES (4, 'Tom', 'Waits', 1, '2016-04-08', CURRENT_TIMESTAMP);
INSERT INTO Pupil (id, first_name, last_name, class_id, birthdate, date_created)
VALUES (5, 'Sally', 'Hutch', 1, '2016-05-08', CURRENT_TIMESTAMP);
INSERT INTO Pupil (id, first_name, last_name, class_id, birthdate, date_created)
VALUES (6, 'Harry', 'Hutch', 2, '2018-06-08', CURRENT_TIMESTAMP);
INSERT INTO Pupil (id, first_name, last_name, class_id, birthdate, date_created)
VALUES (7, 'Hermione', 'Hutch', 7, '2018-06-08', CURRENT_TIMESTAMP);
--
CREATE TABLE IF NOT EXISTS Parent_contract
(
    id           INTEGER AUTO_INCREMENT,
    parent_id    INTEGER   NOT NULL,
    pupil_id     INTEGER   NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    primary key (id),
    foreign key (parent_id) references Parent (id),
    foreign key (pupil_id) references Pupil (id),
    unique (parent_id, pupil_id)
);
--
INSERT INTO Parent_contract (parent_id, pupil_id, date_created)
VALUES (1, 5, CURRENT_TIMESTAMP);
INSERT INTO Parent_contract (parent_id, pupil_id, date_created)
VALUES (1, 6, CURRENT_TIMESTAMP);
INSERT INTO Parent_contract (parent_id, pupil_id, date_created)
VALUES (2, 5, CURRENT_TIMESTAMP);
INSERT INTO Parent_contract (parent_id, pupil_id, date_created)
VALUES (2, 6, CURRENT_TIMESTAMP);
INSERT INTO Parent_contract (parent_id, pupil_id, date_created)
VALUES (3, 3, CURRENT_TIMESTAMP);
INSERT INTO Parent_contract (parent_id, pupil_id, date_created)
VALUES (3, 4, CURRENT_TIMESTAMP);
--
CREATE TABLE IF NOT EXISTS Event
(
    id           INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    name   varchar(255)                        NOT NULL,
    date   DATE                                NOT NULL,
    start_time   TIME                          NOT NULL,
    end_time     TIME                          NOT NULL,
    date_created datetime DEFAULT (NOW())      NOT NULL,
    date_updated TIMESTAMP,
    primary key (id), UNIQUE (name, date)
);

INSERT INTO Event (name, date, start_time, end_time, date_created)
VALUES ('Trafikkvakt', '2020-09-01', '08:15:00', '08:45:00', CURRENT_TIMESTAMP);
INSERT INTO Event (name, date, start_time, end_time, date_created)
VALUES ('Trafikkvakt', '2020-09-02', '08:15:00', '08:45:00', CURRENT_TIMESTAMP);
INSERT INTO Event (name, date, start_time, end_time, date_created)
VALUES ('Trafikkvakt', '2020-09-03', '08:15:00', '08:45:00', CURRENT_TIMESTAMP);
INSERT INTO Event (name, date, start_time, end_time, date_created)
VALUES ('Julemarked', '2020-12-01', '10:00:00', '17:00:00', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Location
(
    id           INTEGER AUTO_INCREMENT UNIQUE                NOT NULL,
    side         enum('Storskolen', 'Lilleskolen', 'Krysset') NOT NULL,
    name         varchar(255)                                 NOT NULL,
    room         varchar(255),
    floor        varchar(255),
    description  varchar(255),
    date_created datetime DEFAULT (NOW())                     NOT NULL,
    date_updated TIMESTAMP,
    primary key (id), UNIQUE (name, room)
);
INSERT INTO Location (side, name, room, floor, date_created)
VALUES (1, 'Hovedbygget', '5.klasserommet', '2', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, date_created)
VALUES (2, '1.klassebygget', 'Hovedrommet', '1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, date_created)
VALUES (2, 'Det blå huset', '2.klasserommet', '1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, date_created)
VALUES (2, 'Det blå huset', '3.klasserommet', '1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, date_created)
VALUES (2, 'Det blå huset', 'Lillesalen', '-1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, date_created)
VALUES (2, 'Det røde huset', '4.klasserommet', '1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, date_created)
VALUES (3, 'Ute', 'Øvre fotgjengerfelt', '0', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, date_created)
VALUES (3, 'Ute', 'Nedre fotgjengerfelt', '0', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, description, date_created)
VALUES (1, 'Ute', 'Hovedbygget', '0', 'Plassen foran', CURRENT_TIMESTAMP);
INSERT INTO Location (side, name, room, floor, description, date_created)
VALUES (2, 'Ute', 'Lilleskolen', '0', 'Sandplassen', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Assignment
(
    id           INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    name         varchar(255)                  NOT NULL,
    location_id  INTEGER                       NOT NULL,
    description  varchar(255),
    date_created datetime DEFAULT (NOW())      NOT NULL,
    date_updated TIMESTAMP,
    primary key (id)
);
INSERT INTO Assignment (name, location_id, date_created)
VALUES ('Trafikkvakt', 7, CURRENT_TIMESTAMP);
INSERT INTO Assignment (name, location_id, date_created)
VALUES ('Trafikkvakt', 8, CURRENT_TIMESTAMP);
INSERT INTO Assignment (name, location_id, date_created)
VALUES ('Trafikkvakt', 9, CURRENT_TIMESTAMP);
INSERT INTO Assignment (name, location_id, date_created)
VALUES ('Rigge', 9, CURRENT_TIMESTAMP);
INSERT INTO Assignment (name, location_id, date_created)
VALUES ('Café', 1, CURRENT_TIMESTAMP);
INSERT INTO Assignment (name, location_id, date_created)
VALUES ('Delikatesser', 2, CURRENT_TIMESTAMP);
INSERT INTO Assignment (name, location_id, description, date_created)
VALUES ('Rake løv', 2, 'Alt løv skal rakes og legges i gjennomsiktige avfallssekker. Sekkene skal settes ved komposthaugen ved lilleskolen.', CURRENT_TIMESTAMP);
INSERT INTO Assignment (name, location_id, date_created)
VALUES ('Rydde', 9,  CURRENT_TIMESTAMP);
INSERT INTO Assignment (name, location_id, date_created)
VALUES ('Café 10.klasse', 3, CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Event_assignment
(
    id            INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    event_id      INTEGER                       NOT NULL,
    assignment_id INTEGER                       NOT NULL,
    date_created  datetime DEFAULT (NOW())      NOT NULL,
    date_updated  TIMESTAMP,
    primary key (id),
    foreign key (event_id) references Event (id),
    foreign key (assignment_id) references Assignment (id),
    unique (event_id, assignment_id)
);

INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (1, 1, CURRENT_TIMESTAMP);
INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (2, 2, CURRENT_TIMESTAMP);
INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (3, 3, CURRENT_TIMESTAMP);
INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (4, 4, CURRENT_TIMESTAMP);
INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (4, 5, CURRENT_TIMESTAMP);
INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (4, 6, CURRENT_TIMESTAMP);
INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (4, 7, CURRENT_TIMESTAMP);
INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (4, 8, CURRENT_TIMESTAMP);
INSERT INTO Event_assignment (event_id, assignment_id, date_created)
VALUES (4, 9, CURRENT_TIMESTAMP);


CREATE TABLE IF NOT EXISTS Time_slot
(
    id             INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    start_time     TIME                          NOT NULL,
    end_time       TIME                          NOT NULL,
    date_created   datetime DEFAULT (NOW())      NOT NULL,
    date_updated   TIMESTAMP,
    primary key (id), UNIQUE (start_time, end_time)
);

INSERT INTO Time_slot (start_time, end_time, date_created)
VALUES ('08:15:00', '08:45:00', CURRENT_TIMESTAMP);
INSERT INTO Time_slot (start_time, end_time, date_created)
VALUES ('17:00:00', '21:00:00', CURRENT_TIMESTAMP);
INSERT INTO Time_slot (start_time, end_time, date_created)
VALUES ('10:00:00', '12:00:00', CURRENT_TIMESTAMP);
INSERT INTO Time_slot (start_time, end_time, date_created)
VALUES ('12:00:00', '14:00:00', CURRENT_TIMESTAMP);
INSERT INTO Time_slot (start_time, end_time, date_created)
VALUES ('14:00:00', '16:00:00', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Watch
(
    id            INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    assignment_id INTEGER                       NOT NULL,
    parent_id     INTEGER,
    pupil_id      INTEGER,
    time_slot_id  INTEGER,
    date_created datetime DEFAULT (NOW())       NOT NULL,
    date_updated TIMESTAMP,
    primary key (time_slot_id, assignment_id),
    foreign key (time_slot_id) references Time_slot (id),
    foreign key (assignment_id) references Assignment (id),
    CHECK (parent_id IS NOT NULL OR pupil_id IS NOT NULL)
);

INSERT INTO Watch (time_slot_id, assignment_id, parent_id, date_created)
VALUES ('1', '1', '1', CURRENT_TIMESTAMP);
INSERT INTO Watch (time_slot_id, assignment_id, parent_id, date_created)
VALUES ('2', '2', '2', CURRENT_TIMESTAMP);
INSERT INTO Watch (time_slot_id, assignment_id, parent_id, date_created)
VALUES ('3', '3', '3', CURRENT_TIMESTAMP);
INSERT INTO Watch (time_slot_id, assignment_id, parent_id, date_created)
VALUES ('1', '4', '1', CURRENT_TIMESTAMP);
INSERT INTO Watch (time_slot_id, assignment_id, pupil_id, date_created)
VALUES ('1', '7', '3', CURRENT_TIMESTAMP);






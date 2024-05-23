CREATE TABLE IF NOT EXISTS Class_list
(
    id           INTEGER AUTO_INCREMENT,
    class_letter varchar(2) NOT NULL,
    class_name   varchar(4) ,
    start_year   varchar(4) NOT NULL,
    filename     varchar(255),
    date_created TIMESTAMP DEFAULT (NOW()),
    date_updated TIMESTAMP,
    primary key (class_letter, start_year)
);

INSERT INTO Class_list (class_letter, class_name, start_year, filename)
VALUES ('a', '1a', '2023', '1A_2020');
INSERT INTO Class_list (class_letter, class_name, start_year, filename)
VALUES ('b', '1b', '2023', '1B_2020');
INSERT INTO Class_list (class_letter, class_name, start_year, filename)
VALUES ('a', '2a', '2022', '2A_2019');
INSERT INTO Class_list (class_letter, class_name, start_year, filename)
VALUES ('b', '2b', '2022', '2B_2019');
INSERT INTO Class_list (class_letter, class_name, start_year, filename)
VALUES ('a', '3a', '2021', '3A_2018');
INSERT INTO Class_list (class_letter, class_name, start_year, filename)
VALUES ('b', '3b', '2021', '3B_2018');
INSERT INTO Class_list (class_letter, class_name, start_year, filename)
VALUES ('a', '10a', '2014', '10_2011');


CREATE TABLE IF NOT EXISTS Parent
(
    id           INTEGER AUTO_INCREMENT,
    first_name   varchar(100) NOT NULL,
    last_name    varchar(100) NOT NULL,
    email        varchar(100),
    phone_number varchar(20) NOT NULL,
    date_created TIMESTAMP DEFAULT (NOW()),
    date_updated TIMESTAMP,
    primary key (id),
    UNIQUE (email)

    -- foreign key (class_id) references Class_list (id)
);
INSERT INTO Parent (first_name, last_name, email, phone_number)
VALUES ('John', 'Hutch', 'john@hutch.com', '92147348');
INSERT INTO Parent (first_name, last_name, email, phone_number)
VALUES ('Jane', 'Hutch', 'jane@hutch.com', '9 48 9 90 9');
INSERT INTO Parent (first_name, last_name, email, phone_number)
VALUES ('Lisa', 'Danielson', 'lisa@lisandra.com', '920 63 000');

CREATE TABLE IF NOT EXISTS Pupil
(
    id           INTEGER AUTO_INCREMENT NOT NULL,
    first_name   varchar(70)                  NOT NULL,
    last_name    varchar(70)                  NOT NULL,
    class_id     INTEGER                      NOT NULL,
    phone_number varchar(20),
    birthdate    DATE,
    date_created TIMESTAMP DEFAULT (NOW()),
    date_updated TIMESTAMP,
    primary key (first_name, last_name, class_id)
   -- foreign key (class_id) references Class_list (id)
);
--
-- // Insert children
INSERT INTO Pupil (first_name, last_name, class_id, birthdate)
VALUES ('Nancy', 'Drew', 1, '2016-01-01');
INSERT INTO Pupil (first_name, last_name, class_id, birthdate)
VALUES ('Tom', 'Waits', 1, '2016-04-08');
INSERT INTO Pupil (first_name, last_name, class_id, birthdate)
VALUES ('Sally', 'Hutch', 1, '2016-05-08');
INSERT INTO Pupil (first_name, last_name, class_id, birthdate)
VALUES ('Harry', 'Hutch', 2, '2018-06-08');
INSERT INTO Pupil (first_name, last_name, class_id, birthdate)
VALUES ('Hermione', 'Hutch', 7, '2018-06-08');

CREATE TABLE IF NOT EXISTS parent_pupil
(
    id           INTEGER,
    parent_id    INTEGER,
    pupil_id     INTEGER,
    primary key (parent_id, pupil_id)
);

INSERT INTO Parent_Pupil (parent_id, pupil_id)
VALUES (1, 3);
INSERT INTO Parent_Pupil (parent_id, pupil_id)
VALUES (1, 4);
INSERT INTO Parent_Pupil (parent_id, pupil_id)
VALUES (2, 3);
INSERT INTO Parent_Pupil (parent_id, pupil_id)
VALUES (2, 4);
INSERT INTO Parent_Pupil (parent_id, pupil_id)
VALUES (3, 1);
INSERT INTO Parent_Pupil (parent_id, pupil_id)
VALUES (3, 2);

CREATE TABLE IF NOT EXISTS Event
(
    id           INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    name   varchar(50)                        NOT NULL,
    date   DATE                                NOT NULL,
    start_time   TIME                          NOT NULL,
    end_time     TIME                          NOT NULL,
    date_created TIMESTAMP DEFAULT (NOW())      NOT NULL,
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
    side         varchar(11) check (side in ('Storskolen', 'Lilleskolen', 'Krysset')) NOT NULL,
    building     varchar(30)                                 NOT NULL,
    room         varchar(20),
    floor        CHAR(2),
    description  varchar(255),
    date_created TIMESTAMP DEFAULT (NOW())                     NOT NULL,
    date_updated TIMESTAMP,
    primary key (id), UNIQUE (building, room)
);
INSERT INTO Location (side, building, room, floor, date_created)
VALUES ('Storskolen', 'Hovedbygget', '5.klasserommet', '2', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, date_created)
VALUES ('Lilleskolen', '1.klassebygget', 'Hovedrommet', '1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, date_created)
VALUES ('Lilleskolen', 'Det blå huset', '2.klasserommet', '1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, date_created)
VALUES ('Lilleskolen', 'Det blå huset', '3.klasserommet', '1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, date_created)
VALUES ('Lilleskolen', 'Det blå huset', 'Lillesalen', '-1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, date_created)
VALUES ('Lilleskolen',  'Det røde huset', '4.klasserommet', '1', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, date_created)
VALUES ('Krysset', 'Ute', 'Øvre fotgjengerfelt', '0', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, date_created)
VALUES ('Krysset', 'Ute', 'Nedre fotgjengerfelt', '0', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, description, date_created)
VALUES ('Storskolen', 'Ute', 'Hovedbygget', '0', 'Plassen foran', CURRENT_TIMESTAMP);
INSERT INTO Location (side, building, room, floor, description, date_created)
VALUES ('Lilleskolen', 'Ute', 'Lilleskolen', '0', 'Sandplassen', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Task
(
    id           INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    task_name    varchar(70)                  NOT NULL,
    leader_id    INTEGER,
    location_id  INTEGER                       NOT NULL,
    description  varchar(255),
    date_created TIMESTAMP DEFAULT (NOW())      NOT NULL,
    date_updated TIMESTAMP,
    primary key (id),
    foreign key (leader_id) references Parent (id)
);
INSERT INTO Task (task_name, location_id, date_created)
VALUES ('Trafikkvakt', 7, CURRENT_TIMESTAMP);
INSERT INTO Task (task_name, location_id, date_created)
VALUES ('Trafikkvakt', 8, CURRENT_TIMESTAMP);
INSERT INTO Task (task_name, location_id, date_created)
VALUES ('Trafikkvakt', 9, CURRENT_TIMESTAMP);
INSERT INTO Task (task_name, location_id, date_created)
VALUES ('Rigge', 9, CURRENT_TIMESTAMP);
INSERT INTO Task (task_name, location_id, date_created)
VALUES ('Café', 1, CURRENT_TIMESTAMP);
INSERT INTO Task (task_name, location_id, date_created)
VALUES ('Delikatesser', 2, CURRENT_TIMESTAMP);
INSERT INTO Task (task_name, location_id, description, date_created)
VALUES ('Rake løv', 2, 'Alt løv skal rakes og legges i gjennomsiktige avfallssekker. Sekkene skal settes ved komposthaugen ved lilleskolen.', CURRENT_TIMESTAMP);
INSERT INTO Task (task_name, location_id, date_created)
VALUES ('Rydde', 9,  CURRENT_TIMESTAMP);
INSERT INTO Task (task_name, location_id, date_created)
VALUES ('Café 10.klasse', 3, CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Event_task
(
    id            INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    event_id      INTEGER                       NOT NULL,
    task_id INTEGER                       NOT NULL,
    date_created  TIMESTAMP DEFAULT (NOW())      NOT NULL,
    date_updated  TIMESTAMP,
    primary key (id),
    foreign key (event_id) references Event (id),
    foreign key (task_id) references Task (id),
    unique (event_id, task_id)
);

INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (1, 1, CURRENT_TIMESTAMP);
INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (2, 2, CURRENT_TIMESTAMP);
INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (3, 3, CURRENT_TIMESTAMP);
INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (4, 4, CURRENT_TIMESTAMP);
INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (4, 5, CURRENT_TIMESTAMP);
INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (4, 6, CURRENT_TIMESTAMP);
INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (4, 7, CURRENT_TIMESTAMP);
INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (4, 8, CURRENT_TIMESTAMP);
INSERT INTO Event_task (event_id, task_id, date_created)
VALUES (4, 9, CURRENT_TIMESTAMP);


CREATE TABLE IF NOT EXISTS Timeslot
(
    id             INTEGER AUTO_INCREMENT UNIQUE NOT NULL,
    start_time     TIME                          NOT NULL,
    end_time       TIME                          NOT NULL,
    date_created   timestamp DEFAULT (NOW())      NOT NULL,
    date_updated   TIMESTAMP,
    primary key (id), UNIQUE (start_time, end_time)
);

INSERT INTO Timeslot (start_time, end_time, date_created)
VALUES ('08:15:00', '08:45:00', CURRENT_TIMESTAMP);
INSERT INTO Timeslot (start_time, end_time, date_created)
VALUES ('17:00:00', '21:00:00', CURRENT_TIMESTAMP);
INSERT INTO Timeslot (start_time, end_time, date_created)
VALUES ('10:00:00', '12:00:00', CURRENT_TIMESTAMP);
INSERT INTO Timeslot (start_time, end_time, date_created)
VALUES ('12:00:00', '14:00:00', CURRENT_TIMESTAMP);
INSERT INTO Timeslot (start_time, end_time, date_created)
VALUES ('14:00:00', '16:00:00', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS Watch
(
    id            INTEGER AUTO_INCREMENT   NOT NULL,
    task_id INTEGER                        NOT NULL,
    parent_id     INTEGER,
    pupil_id      INTEGER,
    date          DATE                     NOT NULL,
    start_time    TIME                     NOT NULL,
    end_time      TIME,
    date_created TIMESTAMP DEFAULT (NOW()) NOT NULL,
    date_updated TIMESTAMP,
    primary key (date, start_time, task_id),
    foreign key (task_id) references Task (id),
    CHECK (parent_id IS NOT NULL OR pupil_id IS NOT NULL)
);

INSERT INTO Watch (task_id, parent_id, date, start_time,  date_created)
VALUES ('1', '1', '2023-12-02', '10:00', CURRENT_TIMESTAMP);
INSERT INTO Watch (task_id, parent_id, date, start_time,  date_created)
VALUES ('2', '2', '2023-12-02', '10:00', CURRENT_TIMESTAMP);
INSERT INTO Watch (task_id, parent_id, date, start_time,  date_created)
VALUES ('3', '3', '2023-12-02', '12:00', CURRENT_TIMESTAMP);
INSERT INTO Watch (task_id, parent_id, date, start_time,  date_created)
VALUES ('4', '1', '2023-12-02', '14:00', CURRENT_TIMESTAMP);
INSERT INTO Watch (task_id, pupil_id,  date, start_time, date_created)
VALUES ('9', '5', '2023-12-02', '10:00', CURRENT_TIMESTAMP);






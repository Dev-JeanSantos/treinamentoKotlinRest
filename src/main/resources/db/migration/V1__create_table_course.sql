CREATE TABLE course(
    id bigint not null auto_increment,
    name varchar(50) not null,
    category varchar(50) not null,
    primary key(id)
);

INSERT INTO course (id, name, category ) VALUES (1, 'Kotlin', 'PROGRAMAÇÃO');
INSERT INTO course (id, name, category ) VALUES (2, 'AWS', 'DEVOPS');
INSERT INTO course (id, name, category ) VALUES (3, 'ADOBE XD', 'UX');
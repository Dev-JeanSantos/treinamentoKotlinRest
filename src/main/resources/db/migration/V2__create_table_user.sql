CREATE TABLE user(
    id bigint not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

INSERT INTO  USER (id, name, email ) VALUES (1, 'Jean', 'email@gmail.com');

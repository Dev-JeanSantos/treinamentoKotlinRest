CREATE TABLE tb_course(
    id bigint not null auto_increment,
    name varchar(50) not null,
    category varchar(50) not null,
    primary key(id)
);

INSERT INTO TB_COURSE (id, name, category ) VALUES (1, 'Kotlin', 'PROGRAMAÇÃO');
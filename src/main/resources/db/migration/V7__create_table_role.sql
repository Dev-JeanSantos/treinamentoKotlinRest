create table  role(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO role (id, name) VALUES (1 ,'READ_WRITE');

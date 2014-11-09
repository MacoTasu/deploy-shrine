# --- !Ups

CREATE TABLE revelation (
  id int(10) NOT NULL AUTO_INCREMENT,
  type SMALLINT NOT NULL,
  word varchar(64) NOT NULL,
  PRIMARY KEY (id)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# --- !Downs
DROP TABLE revelation

CREATE TABLE IF NOT EXISTS book (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  author varchar(45) NOT NULL,
  category varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX index_id ON book(id);
CREATE INDEX index_author ON book(author);
CREATE INDEX index_category ON book(category);

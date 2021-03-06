DROP TABLE families IF EXISTS;
DROP TABLE persons IF EXISTS;

CREATE TABLE families (
  fid BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(30)
);

CREATE TABLE persons (
  pid BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(30),
  family_id  BIGINT
);

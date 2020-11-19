CREATE TABLE IF NOT EXISTS login (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nickName VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS country (
  id BIGINT UNSIGNED NOT NULL,
  name VARCHAR(255) NULL,
  abbreviation VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS state (
  id BIGINT UNSIGNED NOT NULL,
  name VARCHAR(255) NULL,
  countryID BIGINT UNSIGNED NULL,
  abbreviation VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_state_country
    FOREIGN KEY (countryID)
    REFERENCES country (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS city (
  id BIGINT UNSIGNED NOT NULL,
  name VARCHAR(255) NULL,
  stateID BIGINT UNSIGNED NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_city_state
    FOREIGN KEY (stateID)
    REFERENCES state (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS address (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  descriptionAddress TEXT NULL,
  descriptionAditionalAddress TEXT NULL,
  number INT NULL,
  zipCode VARCHAR(100) NULL,
  cityID BIGINT UNSIGNED NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_address_1
    FOREIGN KEY (cityID)
    REFERENCES city (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS user (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  userUUID VARCHAR(36) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  dateOfBirth DATE NULL,
  loginID BIGINT UNSIGNED NOT NULL,
  addressID BIGINT UNSIGNED NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_login
    FOREIGN KEY (loginID)
    REFERENCES login (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_user_1
    FOREIGN KEY (addressID)
    REFERENCES address (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS email (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  userID BIGINT UNSIGNED NOT NULL,
  email VARCHAR(255) NOT NULL,
  primaryEmail BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_email
    FOREIGN KEY (userID)
    REFERENCES user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS phone (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  userID BIGINT UNSIGNED NOT NULL,
  number VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_phone
    FOREIGN KEY (userID)
    REFERENCES user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS userToken (
  id BIGINT NOT NULL AUTO_INCREMENT,
  userID VARCHAR(36) NOT NULL,
  token TEXT NULL,
  tokenSecret TEXT NULL,
  PRIMARY KEY (id));

INSERT INTO country (id, name, abbreviation) VALUES(1, 'brasil', 'br'),(2, 'estados unidos', 'eua');
INSERT INTO state (id, name, countryID,abbreviation) VALUES(1, 'rio de janeiro',1, 'rj'),(2, 'nova york',2 , 'ny');
INSERT INTO city (id, name, stateID) VALUES(1, 'rio de janeiro - city',1),(2, 'nova york - city',2);

CREATE TABLE IF NOT EXISTS admin.login (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  nickName VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX nickName_UNIQUE (nickName ASC))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admin.country (
  id BIGINT UNSIGNED NOT NULL,
  name VARCHAR(255) NULL,
  abbreviation VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admin.state (
  id BIGINT UNSIGNED NOT NULL,
  name VARCHAR(255) NULL,
  countryID BIGINT UNSIGNED NULL,
  abbreviation VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_state_country_idx (countryID ASC),
  CONSTRAINT fk_state_country
    FOREIGN KEY (countryID)
    REFERENCES admin.country (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admin.city (
  id BIGINT UNSIGNED NOT NULL,
  name VARCHAR(255) NULL,
  stateID BIGINT UNSIGNED NULL,
  PRIMARY KEY (id),
  INDEX fk_city_state_idx (stateID ASC),
  CONSTRAINT fk_city_state
    FOREIGN KEY (stateID)
    REFERENCES admin.state (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admin.address (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  descriptionAddress TEXT NULL,
  descriptionAditionalAddress TEXT NULL,
  number INT NULL,
  zipCode VARCHAR(100) NULL,
  cityID BIGINT UNSIGNED NULL,
  PRIMARY KEY (id),
  INDEX fk_address_1_idx (cityID ASC),
  CONSTRAINT fk_address_1
    FOREIGN KEY (cityID)
    REFERENCES admin.city (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admin.user (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  userUUID VARCHAR(36) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  dateOfBirth DATE NULL,
  loginID BIGINT UNSIGNED NOT NULL,
  addressID BIGINT UNSIGNED NULL,
  PRIMARY KEY (id),
  INDEX fk_user_login_idx (loginID ASC),
  INDEX fk_user_1_idx (addressID ASC),
  CONSTRAINT fk_user_login
    FOREIGN KEY (loginID)
    REFERENCES admin.login (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_user_1
    FOREIGN KEY (addressID)
    REFERENCES admin.address (id)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admin.email (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  userID BIGINT UNSIGNED NOT NULL,
  email VARCHAR(255) NOT NULL,
  primaryEmail BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_user_idx (userID ASC),
  CONSTRAINT fk_user_email
    FOREIGN KEY (userID)
    REFERENCES admin.user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admin.phone (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  userID BIGINT UNSIGNED NOT NULL,
  number VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_user_idx (userID ASC),
  CONSTRAINT fk_user_phone
    FOREIGN KEY (userID)
    REFERENCES admin.user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS admin.userToken (
  id BIGINT NOT NULL AUTO_INCREMENT,
  userID VARCHAR(36) NOT NULL,
  token TEXT NULL,
  tokenSecret TEXT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

INSERT INTO country (id, name, abbreviation) VALUES(1, 'brasil', 'br'),(2, 'estados unidos', 'eua');
INSERT INTO state (id, name, countryID,abbreviation) VALUES(1, 'rio de janeiro',1, 'rj'),(2, 'nova york',2 , 'ny');
INSERT INTO city (id, name, stateID) VALUES(1, 'rio de janeiro - city',1),(2, 'nova york - city',2);
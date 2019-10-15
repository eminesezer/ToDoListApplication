DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  id                  INT(10) AUTO_INCREMENT PRIMARY KEY,
  username            VARCHAR(100) NOT NULL,
  `password`          VARCHAR(255) NOT NULL,
  `password_plaintext` VARCHAR(30) NOT NULL,
  `authority_type` ENUM('USER','ADMIN','UNKNOWN') NOT NULL DEFAULT 'UNKNOWN',
  `is_enabled`        TINYINT(1)   NOT NULL DEFAULT 0,
  UNIQUE KEY unq_username (username),
  INDEX idx_username_enaabled (username, `is_enabled`)
);
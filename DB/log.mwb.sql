-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema logthingsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `logthingsdb` ;

-- -----------------------------------------------------
-- Schema logthingsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `logthingsdb` DEFAULT CHARACTER SET utf8 ;
USE `logthingsdb` ;

-- -----------------------------------------------------
-- Table `log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `log` ;

CREATE TABLE IF NOT EXISTS `log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trip_by_land` VARCHAR(50) NULL,
  `details` VARCHAR(100) NULL,
  `hikes` VARCHAR(45) NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sea_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sea_log` ;

CREATE TABLE IF NOT EXISTS `sea_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trip_by_sea` VARCHAR(100) NULL,
  `details` VARCHAR(100) NULL,
  `dinghy_adventures` VARCHAR(100) NULL,
  `dates` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `maintenace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `maintenace` ;

CREATE TABLE IF NOT EXISTS `maintenace` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `log_id` INT NOT NULL,
  `maintenance_item` VARCHAR(100) NULL,
  `description` VARCHAR(100) NULL,
  `interval` VARCHAR(50) NULL,
  `sea_log_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_maintenace_log_idx` (`log_id` ASC),
  INDEX `fk_maintenace_sea_log1_idx` (`sea_log_id` ASC),
  CONSTRAINT `fk_maintenace_log`
    FOREIGN KEY (`log_id`)
    REFERENCES `log` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_maintenace_sea_log1`
    FOREIGN KEY (`sea_log_id`)
    REFERENCES `sea_log` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS log@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'log'@'localhost' IDENTIFIED BY 'log';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'log'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `log`
-- -----------------------------------------------------
START TRANSACTION;
USE `logthingsdb`;
INSERT INTO `log` (`id`, `trip_by_land`, `details`, `hikes`, `date`) VALUES (1, 'Glacier', NULL, NULL, NULL);

COMMIT;


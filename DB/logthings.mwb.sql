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
-- Table `land_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `land_log` ;

CREATE TABLE IF NOT EXISTS `land_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `trip_by_land` VARCHAR(50) NULL,
  `details` VARCHAR(100) NULL,
  `duration` INT NULL,
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
-- Table `maintenace_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `maintenace_log` ;

CREATE TABLE IF NOT EXISTS `maintenace_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sea_log_id` INT NOT NULL,
  `log_id` INT NOT NULL,
  `maintenance_item` VARCHAR(100) NULL,
  `description` VARCHAR(100) NULL,
  `interval` VARCHAR(50) NULL,
  `fixes` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_maintenace_log_idx` (`log_id` ASC),
  INDEX `fk_maintenace_sea_log1_idx` (`sea_log_id` ASC),
  CONSTRAINT `fk_maintenace_log`
    FOREIGN KEY (`log_id`)
    REFERENCES `land_log` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_maintenace_sea_log1`
    FOREIGN KEY (`sea_log_id`)
    REFERENCES `sea_log` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hike_adventure`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hike_adventure` ;

CREATE TABLE IF NOT EXISTS `hike_adventure` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `land_log_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `miles` DECIMAL(2) NULL,
  `details` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hike_adventure_land_log1_idx` (`land_log_id` ASC),
  CONSTRAINT `fk_hike_adventure_land_log1`
    FOREIGN KEY (`land_log_id`)
    REFERENCES `land_log` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dinghy_adventure`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dinghy_adventure` ;

CREATE TABLE IF NOT EXISTS `dinghy_adventure` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sea_log_id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `nautical_miles` DECIMAL(2) NULL,
  `details` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dinghy_adventure_sea_log1_idx` (`sea_log_id` ASC),
  CONSTRAINT `fk_dinghy_adventure_sea_log1`
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
-- Data for table `land_log`
-- -----------------------------------------------------
START TRANSACTION;
USE `logthingsdb`;
INSERT INTO `land_log` (`id`, `trip_by_land`, `details`, `duration`) VALUES (1, 'Glacier National Park', 'Boondocking', 28);
INSERT INTO `land_log` (`id`, `trip_by_land`, `details`, `duration`) VALUES (2, 'Alaska', 'Homer, AK and Anchorage, AK', 20);

COMMIT;


-- -----------------------------------------------------
-- Data for table `hike_adventure`
-- -----------------------------------------------------
START TRANSACTION;
USE `logthingsdb`;
INSERT INTO `hike_adventure` (`id`, `land_log_id`, `name`, `miles`, `details`) VALUES (1, 2, 'Thunderbird Falls', 1, '800 ft. elevation');

COMMIT;


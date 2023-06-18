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
-- Table `adventure_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `adventure_log` ;

CREATE TABLE IF NOT EXISTS `adventure_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category` VARCHAR(45) NULL,
  `details` VARCHAR(250) NULL,
  `duration` INT NULL,
  `activity` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `activity_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activity_log` ;

CREATE TABLE IF NOT EXISTS `activity_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `adventure_log_id` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  `distance` INT NULL,
  `details` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_activity_log_adventure_log2_idx` (`adventure_log_id` ASC),
  CONSTRAINT `fk_activity_log_adventure_log2`
    FOREIGN KEY (`adventure_log_id`)
    REFERENCES `adventure_log` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `maintenance_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `maintenance_log` ;

CREATE TABLE IF NOT EXISTS `maintenance_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `adventure_log_id` INT NOT NULL,
  `description` VARCHAR(200) NULL,
  `maintenance_item` VARCHAR(100) NULL,
  `interval` VARCHAR(100) NULL,
  `fixes` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_maintenance_log_adventure_log1_idx` (`adventure_log_id` ASC),
  CONSTRAINT `fk_maintenance_log_adventure_log1`
    FOREIGN KEY (`adventure_log_id`)
    REFERENCES `adventure_log` (`id`)
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
-- Data for table `adventure_log`
-- -----------------------------------------------------
START TRANSACTION;
USE `logthingsdb`;
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (1, 'land', 'Glacier National Park', 28, 'Hike, bike, horse ride, whitewater rafting, and more');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (2, 'land', 'Homer, Alaska', 20, 'Hikes');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (3, 'land', 'Dinosaur National Monument, CO/UT', 14, 'Hike, swim, set out first trailCams');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (4, 'land', 'Yellowstone National Park', 14, 'Hikes');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (5, 'land', 'White Sands National Park, NM', 10, 'Hikes');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (6, 'land', 'Coronado National Forest, AZ', 14, 'Hikes, Dirt Biking');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (7, 'land', 'Kofa National Wildlife Refuge, AZ', 14, 'Hikes, Dirt Biking');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (8, 'land', 'Lava River Cave, AZ', 1, 'Cave Hikes');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (9, 'land', 'Kaibab National Forest, AZ', 14, 'Hikes');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (10, 'land', 'Grand Canyon, AZ', 5, 'Hikes');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (11, 'land', 'Jean/Roach Dry Lake Beds, NV', 7, 'Hikes, Dirt Biking, Target practice');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (12, 'land', 'Valley of Fire State Park', 14, 'Hikes, Visiting Vegas');
INSERT INTO `adventure_log` (`id`, `category`, `details`, `duration`, `activity`) VALUES (13, 'land', 'Providence Canyon State Park, GA', 5, 'Hikes');

COMMIT;


-- -----------------------------------------------------
-- Data for table `activity_log`
-- -----------------------------------------------------
START TRANSACTION;
USE `logthingsdb`;
INSERT INTO `activity_log` (`id`, `adventure_log_id`, `name`, `distance`, `details`) VALUES (1, 1, 'Ptarmigan Tunnel', 16, 'Ptarmigan Lake, Ptarmigan Falls, and Ptarmigan Tunnel.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `maintenance_log`
-- -----------------------------------------------------
START TRANSACTION;
USE `logthingsdb`;
INSERT INTO `maintenance_log` (`id`, `adventure_log_id`, `description`, `maintenance_item`, `interval`, `fixes`) VALUES (1, 1, 'Truck and generator maintenance.', 'oil change, hitch lube, rig generator oil change, backup generator oil change', '28', 'n/a');

COMMIT;


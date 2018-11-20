-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sbb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sbb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sbb` DEFAULT CHARACTER SET utf8 ;
USE `sbb` ;

-- -----------------------------------------------------
-- Table `sbb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb`.`user` (
  `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `birthday` DATE NOT NULL,
  `enabled` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb`.`role` (
  `role_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `role_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sbb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb`.`station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb`.`station` (
  `station_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `station_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`station_id`),
  UNIQUE INDEX `station_name_UNIQUE` (`station_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb`.`train`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb`.`train` (
  `train_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `train_name` VARCHAR(50) NOT NULL,
  `quantity_seats` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`train_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb`.`schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb`.`schedule` (
  `record_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `train_id` INT(10) UNSIGNED NOT NULL,
  `station_id` INT(10) UNSIGNED NOT NULL,
  `order_station` INT(10) UNSIGNED NOT NULL,
  `free_seats` INT(10) UNSIGNED NOT NULL,
  `arrival_time` DATETIME NOT NULL,
  `departure_time` DATETIME NOT NULL,
  PRIMARY KEY (`record_id`),
  INDEX `fk_train_id_schedule_idx` (`train_id` ASC),
  INDEX `fk_station_id_schedule_idx` (`station_id` ASC),
  CONSTRAINT `fk_station_id_schedule`
    FOREIGN KEY (`station_id`)
    REFERENCES `sbb`.`station` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_train_id_schedule`
    FOREIGN KEY (`train_id`)
    REFERENCES `sbb`.`train` (`train_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 45
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb`.`ticket` (
  `ticket_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) UNSIGNED NOT NULL,
  `train_id` INT(11) UNSIGNED NOT NULL,
  `departure_station_id` INT(11) UNSIGNED NOT NULL,
  `destination_station_id` INT(11) UNSIGNED NOT NULL,
  `departure_time` DATETIME NOT NULL,
  `arrival_time` DATETIME NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `birthday` DATE NOT NULL,
  PRIMARY KEY (`ticket_id`),
  UNIQUE INDEX `ticket_id_UNIQUE` (`ticket_id` ASC),
  INDEX `fk_user_id_ticket_idx` (`user_id` ASC),
  INDEX `fk_train_id_ticket_idx` (`train_id` ASC),
  CONSTRAINT `fk_train_id_ticket`
    FOREIGN KEY (`train_id`)
    REFERENCES `sbb`.`train` (`train_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id_ticket`
    FOREIGN KEY (`user_id`)
    REFERENCES `sbb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sbb2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sbb2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sbb2` DEFAULT CHARACTER SET utf8 ;
USE `sbb2` ;

-- -----------------------------------------------------
-- Table `sbb2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb2`.`user` (
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
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb2`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb2`.`role` (
  `role_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `fk_user_id_role`
    FOREIGN KEY (`user_id`)
    REFERENCES `sbb2`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb2`.`station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb2`.`station` (
  `station_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `station_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`station_id`),
  UNIQUE INDEX `station_name_UNIQUE` (`station_name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb2`.`train`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb2`.`train` (
  `train_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `train_name` VARCHAR(50) NOT NULL,
  `quantity_seats` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`train_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb2`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb2`.`route` (
  `route_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `train_id` INT(10) UNSIGNED NOT NULL,
  `station_id` INT(10) UNSIGNED NOT NULL,
  `order_station` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`route_id`),
  INDEX `fk_train_id_idx` (`train_id` ASC),
  INDEX `fk_station_id_idx` (`station_id` ASC),
  CONSTRAINT `fk_station_id_route`
    FOREIGN KEY (`station_id`)
    REFERENCES `sbb2`.`station` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_train_id_route`
    FOREIGN KEY (`train_id`)
    REFERENCES `sbb2`.`train` (`train_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb2`.`schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb2`.`schedule` (
  `schedule_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `route_id` INT(10) UNSIGNED NOT NULL,
  `free_seats` INT(10) UNSIGNED NOT NULL,
  `arrival_time` DATETIME NOT NULL,
  `departure_time` DATETIME NOT NULL,
  PRIMARY KEY (`schedule_id`),
  INDEX `fk_route_id_schedule_idx` (`route_id` ASC),
  CONSTRAINT `fk_route_id_schedule`
    FOREIGN KEY (`route_id`)
    REFERENCES `sbb2`.`route` (`route_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb2`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb2`.`ticket` (
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
    REFERENCES `sbb2`.`train` (`train_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id_ticket`
    FOREIGN KEY (`user_id`)
    REFERENCES `sbb2`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sbb3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sbb3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sbb3` DEFAULT CHARACTER SET utf8 ;
USE `sbb3` ;

-- -----------------------------------------------------
-- Table `sbb3`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb3`.`user` (
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
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb3`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb3`.`role` (
  `role_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  INDEX `user_id` (`user_id` ASC),
  CONSTRAINT `fk_user_id_role`
    FOREIGN KEY (`user_id`)
    REFERENCES `sbb3`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb3`.`station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb3`.`station` (
  `station_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `station_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`station_id`),
  UNIQUE INDEX `station_name_UNIQUE` (`station_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb3`.`train`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb3`.`train` (
  `train_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `train_name` VARCHAR(50) NOT NULL,
  `quantity_seats` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`train_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb3`.`route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb3`.`route` (
  `route_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `train_id` INT(10) UNSIGNED NOT NULL,
  `station_id` INT(10) UNSIGNED NOT NULL,
  `order_station` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`route_id`),
  INDEX `fk_train_id_idx` (`train_id` ASC),
  INDEX `fk_station_id_idx` (`station_id` ASC),
  CONSTRAINT `fk_station_id_route`
    FOREIGN KEY (`station_id`)
    REFERENCES `sbb3`.`station` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_train_id_route`
    FOREIGN KEY (`train_id`)
    REFERENCES `sbb3`.`train` (`train_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb3`.`schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb3`.`schedule` (
  `schedule_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `route_id` INT(10) UNSIGNED NOT NULL,
  `schedule_id_last` INT(10) UNSIGNED NULL DEFAULT NULL,
  `free_seats` INT(10) UNSIGNED NOT NULL,
  `arrival_time` DATETIME NOT NULL,
  `departure_time` DATETIME NOT NULL,
  PRIMARY KEY (`schedule_id`),
  INDEX `fk_route_id_schedule_idx` (`route_id` ASC),
  CONSTRAINT `fk_route_id_schedule`
    FOREIGN KEY (`route_id`)
    REFERENCES `sbb3`.`route` (`route_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sbb3`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sbb3`.`ticket` (
  `ticket_id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) UNSIGNED NOT NULL,
  `train_id` INT(11) UNSIGNED NOT NULL,
  `schedule_id_dep` INT(10) UNSIGNED NOT NULL,
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
    REFERENCES `sbb3`.`train` (`train_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id_ticket`
    FOREIGN KEY (`user_id`)
    REFERENCES `sbb3`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

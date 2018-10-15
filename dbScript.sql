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
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
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
  `role_id` INT(10) UNSIGNED NOT NULL,
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
DEFAULT CHARACTER SET = utf8;

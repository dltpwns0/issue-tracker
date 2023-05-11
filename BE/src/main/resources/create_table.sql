-- MySQL Workbench Forward Engineering
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- -----------------------------------------------------
-- Schema issue_tracker_database
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema issue_tracker_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `issue_tracker_database` DEFAULT CHARACTER SET utf8 ;
USE `issue_tracker_database` ;
-- -----------------------------------------------------
-- Table `issue_tracker_database`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issue_tracker_database`.`USER` (
    `id` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `nickname` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
    UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `issue_tracker_database`.`MILESTONE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issue_tracker_database`.`MILESTONE` (
    `name` VARCHAR(45) NOT NULL,
    `scheduled_completion_date` DATETIME NULL,
    `description_for_label` TEXT(65535) NULL,
    PRIMARY KEY (`name`))
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `issue_tracker_database`.`ISSUE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issue_tracker_database`.`ISSUE` (
    `number` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NULL,
    `contents` MEDIUMTEXT NULL,
    `state` TINYINT(1) NULL,
    `created_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id` VARCHAR(45) NOT NULL,
    `milestone_name` VARCHAR(45) NULL,
    PRIMARY KEY (`number`),
    INDEX `fk_issue_user_id_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_issue_milestone_name_idx` (`milestone_name` ASC) VISIBLE,
    CONSTRAINT `fk_issue_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `issue_tracker_database`.`USER` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_issue_milestone_name`
    FOREIGN KEY (`milestone_name`)
    REFERENCES `issue_tracker_database`.`MILESTONE` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `issue_tracker_database`.`COMMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issue_tracker_database`.`COMMENT` (
    `id` INT NOT NULL,
    `contents` MEDIUMTEXT NOT NULL,
    `created_data` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
    `deleted` TINYINT(1) NULL,
    `user_id` VARCHAR(45) NOT NULL,
    `issue_number` INT NOT NULL,
    INDEX `fk_comment_user_id_idx` (`user_id` ASC) VISIBLE,
    PRIMARY KEY (`id`),
    INDEX `fk_comment_issue_number_idx` (`issue_number` ASC) VISIBLE,
    CONSTRAINT `fk_comment_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `issue_tracker_database`.`USER` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_comment_issue_number`
    FOREIGN KEY (`issue_number`)
    REFERENCES `issue_tracker_database`.`ISSUE` (`number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `issue_tracker_database`.`LABEL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issue_tracker_database`.`LABEL` (
    `name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(45) NULL,
    `background_color` VARCHAR(7) NULL,
    `text_color` VARCHAR(7) NULL,
    `issue_number` INT NULL,
    INDEX `Number_idx` (`issue_number` ASC) VISIBLE,
    PRIMARY KEY (`name`),
    CONSTRAINT `fk_label_issue_number`
    FOREIGN KEY (`issue_number`)
    REFERENCES `issue_tracker_database`.`ISSUE` (`number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `issue_tracker_database`.`ASSIGNS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issue_tracker_database`.`ASSIGNS` (
    `user_id` VARCHAR(45) NOT NULL,
    `issue_number` INT NOT NULL,
    INDEX `asdf_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_assigns_issue_number_idx` (`issue_number` ASC) VISIBLE,
    CONSTRAINT `fk_assigns_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `issue_tracker_database`.`USER` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_assigns_issue_number`
    FOREIGN KEY (`issue_number`)
    REFERENCES `issue_tracker_database`.`ISSUE` (`number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `issue_tracker_database`.`IMAGE_FOR_USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issue_tracker_database`.`IMAGE_FOR_USER` (
    `url` VARCHAR(200) NOT NULL,
    `USER_id` VARCHAR(45) NOT NULL,
    UNIQUE INDEX `url_UNIQUE` (`url` ASC) VISIBLE,
    INDEX `fk_IMAGE_FOR_USER_USER1_idx` (`USER_id` ASC) VISIBLE,
    CONSTRAINT `fk_IMAGE_FOR_USER_USER1`
    FOREIGN KEY (`USER_id`)
    REFERENCES `issue_tracker_database`.`USER` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
    ENGINE = InnoDB;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
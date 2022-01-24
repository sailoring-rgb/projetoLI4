-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema GuiaTuristico
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GuiaTuristico
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GuiaTuristico` DEFAULT CHARACTER SET utf8 ;
USE `GuiaTuristico` ;

-- -----------------------------------------------------
-- Table `GuiaTuristico`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GuiaTuristico`.`users` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `location` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GuiaTuristico`.`places`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GuiaTuristico`.`places` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `placeId` VARCHAR(45) NULL,
  `category` VARCHAR(45) NULL,
  `location` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GuiaTuristico`.`reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GuiaTuristico`.`reviews` (
  `placeName` VARCHAR(45) NULL,
  `classification` VARCHAR(45) NULL,
  `text` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  `places_id` INT NOT NULL,
  PRIMARY KEY (`users_id`, `places_id`),
  INDEX `fk_reviews_places1_idx` (`places_id` ASC) VISIBLE,
  CONSTRAINT `fk_reviews_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `GuiaTuristico`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reviews_places1`
    FOREIGN KEY (`places_id`)
    REFERENCES `GuiaTuristico`.`places` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GuiaTuristico`.`plans`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GuiaTuristico`.`plans` (
  `name` VARCHAR(45) NULL,
  `startTime` DATETIME NULL,
  `finishTime` DATETIME NULL,
  `city` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`users_id`),
  CONSTRAINT `fk_plans_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `GuiaTuristico`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `GuiaTuristico` ;

-- -----------------------------------------------------
-- Placeholder table for view `GuiaTuristico`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GuiaTuristico`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `GuiaTuristico`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GuiaTuristico`.`view1`;
USE `GuiaTuristico`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE TABLE `guacamole_db`.`guacamole_images` (
`image_id` INT NOT NULL AUTO_INCREMENT,
`image_name` VARCHAR(128) NULL,
`image_readme` VARCHAR(4096) NULL,
`image_download` VARCHAR(1024) NULL,
`image_scoreboard` VARCHAR(1024) NULL,
`image_writeups` VARCHAR(1024) NULL,
PRIMARY KEY (`image_id`));
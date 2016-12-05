CREATE TABLE `surveillance`.`locations` (
  `id` INT NOT NULL,
  `latitude` VARCHAR(45) NOT NULL,
  `longitude` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL)
COMMENT = 'This table stores the locations of the users.';
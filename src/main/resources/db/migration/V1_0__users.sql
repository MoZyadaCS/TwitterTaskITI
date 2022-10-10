CREATE TABLE `twitter_project`.`users` (
                                           `id` BIGINT(50) NOT NULL AUTO_INCREMENT,
                                           `name` VARCHAR(255) NULL,
                                           `email` VARCHAR(255) NULL,
                                           `password_digest` VARCHAR(255) NULL,
                                           `created_at` DATETIME NOT NULL,
                                           `updated_at` DATETIME NOT NULL,
                                           `remember_token` VARCHAR(255) NULL,
                                           `slug` VARCHAR(255) NULL,
                                           PRIMARY KEY (`id`),
                                           UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
                                           UNIQUE INDEX `slug_UNIQUE` (`slug` ASC) ,
                                           INDEX `remember_token_index` (`remember_token` ASC) );


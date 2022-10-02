<<<<<<< HEAD
CREATE TABLE `twitter_project`.`tweets` (
                                            `id` BIGINT(50) NOT NULL AUTO_INCREMENT,
                                            `content` VARCHAR(255) NULL,
                                            `user_id` BIGINT(50) NOT NULL,
                                            `created_at` DATETIME NOT NULL,
                                            `updated_at` DATETIME NOT NULL,
                                            PRIMARY KEY (`id`),
                                            INDEX `user_id` (`user_id` ASC) ,
                                            INDEX `created_at` (`created_at` ASC) ,
                                            CONSTRAINT `user_id`
                                                FOREIGN KEY (`user_id`)
                                                    REFERENCES `twitter_project`.`users` (`id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION);
=======
CREATE TABLE `twitter_project`.`tweets` (
                                            `id` BIGINT(50) NOT NULL AUTO_INCREMENT,
                                            `content` VARCHAR(255) NULL,
                                            `user_id` BIGINT(50) NOT NULL,
                                            `created_at` DATETIME NOT NULL,
                                            `updated_at` DATETIME NOT NULL,
                                            PRIMARY KEY (`id`),
                                            INDEX `user_id` (`user_id` ASC) ,
                                            INDEX `created_at` (`created_at` ASC) ,
                                            CONSTRAINT `user_id`
                                                FOREIGN KEY (`user_id`)
                                                    REFERENCES `twitter_project`.`users` (`id`)
                                                    ON DELETE NO ACTION
                                                    ON UPDATE NO ACTION);
>>>>>>> 8ada25e (cleaning the code)

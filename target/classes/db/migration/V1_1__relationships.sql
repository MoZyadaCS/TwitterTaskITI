CREATE TABLE `twitter_project`.`relationships` (
                                                   `id` BIGINT(50) NOT NULL AUTO_INCREMENT,
                                                   `follower_id` BIGINT(50) NULL,
                                                   `followed_id` BIGINT(50) NULL,
                                                   `created_at` DATETIME NOT NULL,
                                                   `updated_at` DATETIME NOT NULL,
                                                   FOREIGN KEY (follower_id) REFERENCES users(id),
                                                   FOREIGN KEY (followed_id) REFERENCES users(id),
                                                   PRIMARY KEY (`id`),
                                                   INDEX `follower_id` (`follower_id` ASC),
                                                   INDEX `followed_id` (`followed_id` ASC));

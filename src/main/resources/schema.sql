-- Drop existing tables if they exist
DROP TABLE IF EXISTS UserGroup;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Group`;

-- Create User table
CREATE TABLE `User` (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL UNIQUE
);

-- Create Group table
CREATE TABLE `Group` (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description VARCHAR(255)
);

-- Create UserGroup table for the many-to-many relationship
CREATE TABLE UserGroup (
                           user_id BIGINT NOT NULL,
                           group_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, group_id),
                           FOREIGN KEY (user_id) REFERENCES `User` (id) ON DELETE CASCADE,
                           FOREIGN KEY (group_id) REFERENCES `Group` (id) ON DELETE CASCADE
);

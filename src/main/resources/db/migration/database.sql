
-- Insert sample data into User Table
INSERT INTO User (username, password, email)
VALUES ('john_doe', 'password123', 'john.doe@example.com'),
       ('jane_smith', 'password456', 'jane.smith@example.com'),
       ('alice_jones', 'password789', 'alice.jones@example.com');

-- Insert sample data into Group Table
INSERT INTO `Group` (name, description)
VALUES ('Admin', 'Administrators group'),
       ('Users', 'General users group'),
       ('Moderators', 'Moderators group');

-- Insert sample data into UserGroup Table (many-to-many relationship)
INSERT INTO UserGroup (user_id, group_id)
VALUES (1, 1), -- John Doe is in Admin group
       (2, 2), -- Jane Smith is in Users group
       (3, 2), -- Alice Jones is in Users group
       (3, 3); -- Alice Jones is also in Moderators group

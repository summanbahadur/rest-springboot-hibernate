-- Insert sample data into the Group table
INSERT INTO `Group` (id, name, description) VALUES (1, 'Admin Group', 'Group for administrators');
INSERT INTO `Group` (id, name, description) VALUES (2, 'User Group', 'Group for regular users');
INSERT INTO `Group` (id, name, description) VALUES (3, 'Guest Group', 'Group for guest users');

-- Insert sample data into the User table
INSERT INTO `User` (id, username, password, email) VALUES (1, 'admin', 'adminpassword', 'admin@example.com');
INSERT INTO `User` (id, username, password, email) VALUES (2, 'user1', 'user1password', 'user1@example.com');
INSERT INTO `User` (id, username, password, email) VALUES (3, 'guest', 'guestpassword', 'guest@example.com');

-- Establish relationships between Users and Groups
INSERT INTO UserGroup (user_id, group_id) VALUES (1, 1); -- admin belongs to Admin Group
INSERT INTO UserGroup (user_id, group_id) VALUES (2, 2); -- user1 belongs to User Group
INSERT INTO UserGroup (user_id, group_id) VALUES (3, 3); -- guest belongs to Guest Group

-- Add user1 to Admin Group as well
INSERT INTO UserGroup (user_id, group_id) VALUES (2, 1); -- user1 also belongs to Admin Group

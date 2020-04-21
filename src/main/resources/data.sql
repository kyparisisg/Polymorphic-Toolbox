-- INSERT INTO Users (first_name, last_name, email, password, role, register_date)
-- VALUES ('Giannis', 'K', 'tuk12920@temple.edu', 'admin', 'ADMIN', NOW() );
--
-- INSERT INTO Users (first_name, last_name, email, password, role, register_date)
-- VALUES ('Matthew', 'Jahn', 'tug68805@temple.edu', 'user', 'USER', SYSDATE() );

--Encrypted password for "admin"
INSERT INTO Users (first_name, last_name, email, password, role, register_date)
VALUES ('Giannis', 'K', 'tuk12920@temple.edu', '$2a$10$l/hXkTYppnqpW8rITe6QTu3o/jgwA1xUqy21LgAoGANbUFr5mYxJy', 'ROLE_ADMIN', NOW() );

--Encrypted password for "user"
INSERT INTO Users (first_name, last_name, email, password, role, register_date)
VALUES ('Matthew', 'Jahn', 'tug68805@temple.edu', '$2a$10$GZv3j7gZJ1trrloPvKeFG.WPTSTtT8iU2w17ECmhnjUC/3nMNu0uW', 'ROLE_USER', SYSDATE() );
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO Servers (name, ip, port, username_cred, password_cred, health, register_date)
VALUES ('Localhost SSH test', '127.0.0.1', '22222', 'username', 'password', '1', Now() );

--INSERT INTO Servers (name, ip, port, username_cred, password_cred, register_date)
--VALUES ('Linode SSH test', '97.107.128.107', '22', 'giannis', 'giannispswd', SYSDATE() );

INSERT INTO Servers (name, ip, port, username_cred, password_cred, register_date)
VALUES ('SSH test', '192.168.1.1', '22', 'giannis', 'giannispswd', SYSDATE() );

INSERT INTO Servers (name, ip, port, username_cred, password_cred, health, register_date, key_location)
VALUES ('TEST EC2', 'ec2-54-163-124-124.compute-1.amazonaws.com', '22', 'ec2-user', '', '1', SYSDATE(), '\src\main\resources\privateKeys\testkey1.pem' );

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO Permissions (user_id, server_id, creation_date)
VALUES('1', '1', SYSDATE());

INSERT INTO Permissions (user_id, server_id, creation_date, username_cred, password_cred)
VALUES('2', '2', SYSDATE(), 'newUsername', 'newPassword');

/*
SELECT name AS "Server Name", first_name, last_name
FROM Permissions
JOIN Users ON Permissions.user_id = Users.id
JOIN Servers ON Permissions.server_id = Servers.id
*/

/*
SELECT first_name, last_name, SERVERS.username_cred AS "Old Username", SERVERS.password_cred AS "Old Password",
    Permissions.username_cred AS "New Username", Permissions.password_cred AS "New Password"
FROM Permissions
JOIN Users ON Permissions.user_id = Users.id
JOIN Servers ON Permissions.server_id = Servers.id
 */


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

INSERT INTO Transactions (user_id, src_server, dst_server, file_name, creation_date, status)
VALUES('1', '1', '2', 'test file', SYSDATE(), '0');

/*
SELECT first_name, last_name, s1.name AS "Source Server", s2.name AS "Destination Server", file_name, creation_date
FROM Transactions
JOIN Users ON Transactions.user_id = Users.id
JOIN Servers S1 ON Transactions.src_server = S1.id
JOIN Servers S2 ON Transactions.dst_server = S2.id
 */


INSERT INTO Servers (name, ip, port, username_cred, password_cred, health, register_date)
VALUES ('MacOS', '192.168.0.3', '22', 'kiouris', 'Oknat-73i', '1', Now() );

INSERT INTO Permissions (user_id, server_id, creation_date)
VALUES('1', '4', SYSDATE());

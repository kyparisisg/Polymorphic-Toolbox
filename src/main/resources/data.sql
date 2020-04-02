INSERT INTO Users (first_name, last_name, email, password, role, register_date)
VALUES ('admin', 'test', 'test@test.com', 'admin', 'admin', NOW() );

INSERT INTO Users (first_name, last_name, email, password, role, register_date)
VALUES ('user', 'lastname', 'user@test.com', 'user', 'user', SYSDATE() );

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
VALUES('1', '1', SYSDATE())

/*SELECT name, first_name, last_name
FROM PERMISSIONS
JOIN SERVERS ON PERMISSIONS.SERVER_ID = SERVERS.ID
JOIN USERS ON PERMISSIONS.USER_ID = USERS.ID*/

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
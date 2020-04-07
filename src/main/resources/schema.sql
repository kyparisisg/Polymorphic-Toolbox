CREATE TABLE Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(200),
    last_name VARCHAR(200) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL DEFAULT 'password',
    role VARCHAR(6) NOT NULL,
    register_date DATE
);

CREATE TABLE Servers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    ip VARCHAR(200) UNIQUE NOT NULL,
    port INT DEFAULT 22,
    username_cred VARCHAR(50)  NOT NULL,
    password_cred VARCHAR(50) NOT NULL,
    health INT DEFAULT 0,
    register_date DATE,
    key_location VARCHAR(200) UNIQUE
);

CREATE TABLE Permissions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    server_id INT NOT NULL,
    creation_date DATE,
    username_cred VARCHAR(50),
    password_cred VARCHAR(50),
    valid INT DEFAULT 0
--   PRIMARY KEY (user_id, server_id)
--     CONSTRAINT fk_user FOREIGN KEY('user_id') REFERENCES Users ('id')
--     CONSTRAINT fk_server FOREIGN KEY('server_id') REFERENCES Servers ('id')
);
ALTER TABLE Permissions ADD FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Permissions ADD FOREIGN KEY (server_id) REFERENCES Servers(id);

/*CREATE TABLE Specific_Creds(
    user_id INT NOT NULL,
    server_id INT NOT NULL,
    username_cred VARCHAR(50)  NOT NULL,
    password_cred VARCHAR(50) NOT NULL,
    valid INT DEFAULT 0,
    PRIMARY KEY (user_id, server_id)
);
ALTER TABLE Specific_Creds ADD FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Specific_Creds ADD FOREIGN KEY (server_id) REFERENCES Servers(id);*/

CREATE TABLE Transactions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    src_server INT NOT NULL,
    dst_server INT NOT NULL,
    file_name VARCHAR(200),
    creation_date DATE,
    status INT DEFAULT 0
);
ALTER TABLE Transactions ADD FOREIGN KEY (user_id) REFERENCES Users(id);
ALTER TABLE Transactions ADD FOREIGN KEY (src_server) REFERENCES Servers(id);
ALTER TABLE Transactions ADD FOREIGN KEY (dst_server) REFERENCES Servers(id);
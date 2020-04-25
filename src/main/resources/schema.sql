CREATE TABLE Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(200),
    last_name VARCHAR(200) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(150) NOT NULL DEFAULT 'password',
    role VARCHAR(16) NOT NULL,
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
    key_location VARCHAR(200)
);

CREATE TABLE Permissions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    server_id INT NOT NULL,
    creation_date DATE,
    username_cred VARCHAR(50),
    password_cred VARCHAR(50),
    valid INT DEFAULT 0,
    CONSTRAINT fk_perm_user FOREIGN KEY(user_id) REFERENCES Users (id),
    CONSTRAINT fk_perm_server FOREIGN KEY(server_id) REFERENCES Servers (id)
);

CREATE TABLE Transactions(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    src_server INT NOT NULL,
    dst_server INT NOT NULL,
    file_name VARCHAR(200),
    creation_date DATE,
    status INT DEFAULT 0,
    CONSTRAINT fk_trans_user FOREIGN KEY(user_id) REFERENCES Users (id),
    CONSTRAINT fk_src_server FOREIGN KEY(src_server) REFERENCES Servers (id),
    CONSTRAINT fk_dst_server FOREIGN KEY(dst_server) REFERENCES Servers (id)
);

CREATE TABLE Bucket_cred(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    bucket_name VARCHAR(100) NOT NULL,
    private_key VARCHAR(100) NOT NULL,
    public_key VARCHAR(100) NOT NULL
)
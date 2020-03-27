CREATE TABLE Users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(200),
    last_name VARCHAR(200) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL DEFAULT 'defpassword',
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
    key_location VARCHAR(1000) UNIQUE
);
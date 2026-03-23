CREATE DATABASE IF NOT EXISTS Session12_DB;
USE Session12_DB;

CREATE TABLE Doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) UNIQUE, 
    full_name VARCHAR(100),
    pass VARCHAR(50)        
);

INSERT INTO Doctors (code, full_name, pass) 
VALUES ('DOC01', 'Dr. Kien', '123456');


CREATE TABLE Vitals (
    p_id INT PRIMARY KEY,
    temperature DOUBLE,
    heart_rate INT
);

INSERT INTO Vitals (p_id, temperature, heart_rate) VALUES (1, 36.5, 80);


DELIMITER //
CREATE PROCEDURE GET_SURGERY_FEE(IN surgery_id INT, OUT total_cost DECIMAL(10,2))
BEGIN
    IF surgery_id = 505 THEN
        SET total_cost = 1500.50;
    ELSE
        SET total_cost = 0;
    END IF;
END //
DELIMITER ;


CREATE TABLE Results (
    id INT PRIMARY KEY AUTO_INCREMENT,
    data TEXT
);


CREATE TABLE Inpatients (
    p_id VARCHAR(10) PRIMARY KEY,
    p_name VARCHAR(100),
    p_age INT,
    department VARCHAR(100),
    diagnosis TEXT,
    admission_days INT DEFAULT 0
);

DELIMITER //
CREATE PROCEDURE CALCULATE_DISCHARGE_FEE(IN patient_id VARCHAR(10), OUT total_fee DECIMAL(15,2))
BEGIN
    DECLARE days INT;
    SELECT admission_days INTO days FROM Inpatients WHERE p_id = patient_id;
    SET total_fee = days * 500000;
END //
DELIMITER ;
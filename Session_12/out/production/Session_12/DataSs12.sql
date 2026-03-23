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
    -- Giả sử logic tính toán chi phí đơn giản
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
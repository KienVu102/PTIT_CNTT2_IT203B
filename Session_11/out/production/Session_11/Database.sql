CREATE DATABASE Hospital_DB;
USE Hospital_DB;


CREATE TABLE Patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(255),
    diagnosis TEXT
);
INSERT INTO Patients (fullname, diagnosis) VALUES 
('kien', 'Dau dau'), 
('nguyen van a', 'Dau bung'), 
('tran thi b', 'Sot cao');



CREATE TABLE Pharmacy (
    medicine_name VARCHAR(255),
    stock INT
);
INSERT INTO Pharmacy VALUES ('Paracetamol', 100), ('Vitamin C', 50), ('Decolgen', 20);

CREATE TABLE Beds (
    bed_id VARCHAR(50) PRIMARY KEY,
    bed_status VARCHAR(50)
);
INSERT INTO Beds VALUES ('1', 'Empty'), ('2', 'Empty');

CREATE TABLE Doctors (
    doctor_id VARCHAR(10) PRIMARY KEY,
    full_name VARCHAR(100),
    specialization VARCHAR(100)
);

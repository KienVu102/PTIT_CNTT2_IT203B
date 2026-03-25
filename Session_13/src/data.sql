DROP DATABASE IF EXISTS Rikkei_Hospital;
CREATE DATABASE Rikkei_Hospital;
USE Rikkei_Hospital;

-- Phần cho Bài 5
CREATE TABLE Patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT
);

CREATE TABLE Beds (
    bed_id INT PRIMARY KEY,
    status VARCHAR(20) DEFAULT 'Trong'
);

CREATE TABLE Finance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    amount DOUBLE
);

-- Dữ liệu mẫu cho giường
INSERT INTO Beds VALUES (201, 'Trong'), (202, 'Trong'), (203, 'Trong');

CREATE TABLE Medicine_Inventory (
    medicine_id INT PRIMARY KEY,
    medicine_name VARCHAR(100),
    quantity INT
);

CREATE TABLE Prescription_History (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    medicine_id INT,
    date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Patient_Wallet (
    patient_id INT PRIMARY KEY,
    balance DOUBLE
);

CREATE TABLE Invoices (
    invoice_id INT PRIMARY KEY,
    status VARCHAR(20)
);

CREATE TABLE Benh_Nhan (
    id INT PRIMARY KEY,
    so_du DOUBLE,
    trang_thai VARCHAR(50)
);

CREATE TABLE Giuong_Benh (
    ma_giuong INT PRIMARY KEY,
    trang_thai VARCHAR(50),
    ma_bn_nam INT
);

CREATE TABLE BenhNhan (
    id INT PRIMARY KEY,
    ho_ten VARCHAR(100)
);

CREATE TABLE DichVuSuDung (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ma_benh_nhan INT,
    ten_dich_vu VARCHAR(100)
);

-- Bài 1 & 2
INSERT INTO Medicine_Inventory VALUES (101, 'Paracetamol', 10);
INSERT INTO Patient_Wallet VALUES (1, 1000000);
INSERT INTO Invoices VALUES (10, 'UNPAID');

-- Bài 3
INSERT INTO Benh_Nhan VALUES (1, 1000000, 'Dang dieu tri');
INSERT INTO Giuong_Benh VALUES (101, 'Co nguoi', 1);

-- Bài 4
INSERT INTO BenhNhan VALUES (1, 'Nguyen Van A'), (2, 'Tran Thi B');
INSERT INTO DichVuSuDung (ma_benh_nhan, ten_dich_vu) VALUES (1, 'Tiem thuoc'), (1, 'Xet nghiem');
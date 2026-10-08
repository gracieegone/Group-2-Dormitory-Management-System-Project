CREATE DATABASE DormitoryManagementSystem;

USE DormitoryManagementSystem;


-- =========================
-- RESIDENT
-- =========================

CREATE TABLE Resident (
    residentID INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roomNumber VARCHAR(20),
    contactNumber VARCHAR(30)
);


-- =========================
-- HALL MANAGER
-- =========================

CREATE TABLE Hall_Manager (
    managerID INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    contactNumber VARCHAR(30)
);


-- =========================
-- MAINTENANCE STAFF
-- =========================

CREATE TABLE Maintenance_Staff (
    staffID INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contactNumber VARCHAR(30)
);


-- =========================
-- REPAIR REQUEST
-- =========================

CREATE TABLE Repair_Request (
    requestID INT PRIMARY KEY,
    residentID INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    issueType VARCHAR(100) NOT NULL,
    priority VARCHAR(30) NOT NULL,
    description TEXT,
    dateSubmitted DATE NOT NULL,

    FOREIGN KEY (residentID)
        REFERENCES Resident(residentID)
);


-- =========================
-- MAINTENANCE TASK
-- =========================

CREATE TABLE Maintenance_Task (
    taskID INT PRIMARY KEY,
    requestID INT NOT NULL,
    staffID INT NOT NULL,
    assignedDate DATE,
    progress VARCHAR(50),
    completionDate DATE,

    FOREIGN KEY (requestID)
        REFERENCES Repair_Request(requestID),

    FOREIGN KEY (staffID)
        REFERENCES Maintenance_Staff(staffID)
);


-- =========================
-- FACULTY REPORT
-- =========================

CREATE TABLE Faculty_Report (
    reportID INT PRIMARY KEY,
    managerID INT NOT NULL,

    FOREIGN KEY (managerID)
        REFERENCES Hall_Manager(managerID)
);


-- =========================
-- SAMPLE DATA
-- =========================

INSERT INTO Resident
(residentID, name, email, password, roomNumber, contactNumber)
VALUES
(123, 'Resident User', 'resident123@dorm.local',
 '123', '309', 'N/A');


INSERT INTO Hall_Manager
(managerID, name, email, password, contactNumber)
VALUES
(456, 'Hall Manager', 'manager456@dorm.local',
 '456', 'N/A');


INSERT INTO Maintenance_Staff
(staffID, name, contactNumber)
VALUES
(789, 'Maintenance Staff', 'N/A');


INSERT INTO Repair_Request
(requestID, residentID, status, issueType, priority,
 description, dateSubmitted)
VALUES
(101, 123, 'In-Progress', 'Noise Complaint', 'Medium',
 'Loud noise from nearby room during quiet hours',
 '2026-09-01');


INSERT INTO Repair_Request
(requestID, residentID, status, issueType, priority,
 description, dateSubmitted)
VALUES
(102, 123, 'Pending', 'Bed Issue', 'Low',
 'Bed frame needs repair',
 '2026-09-05');
 
SHOW DATABASES;

USE dormitorymanagementsystem;

UPDATE Resident
SET password = '123'
WHERE residentID = 123;

USE dormitorymanagementsystem;

SELECT * FROM Repair_Request;

SELECT * FROM Maintenance_Task;


CREATE database blood_donor_db;
use blood_donor_db;
create table donor(
donor_id INT PRIMARY KEY auto_increment,
donor_name VARCHAR(100) not null,
age INT not null,
gender VARCHAR(10),
blood_group VARCHAR(5) not null,
phone VARCHAR(15) not null,
city VARCHAR(50) not null
);

INSERT INTO Donor
(donor_name, age, gender, blood_group, phone, city)
VALUES
('Arun Kumar', 22, 'Male', 'O+', '9000000001', 'Chennai'),
('Priya Sharma', 21, 'Female', 'A+', '9000000002', 'Coimbatore'),
('Rahul Raj', 24, 'Male', 'B+', '9000000003', 'Madurai'),
('Divya S', 23, 'Female', 'AB+', '9000000004', 'Chennai'),
('Karthik M', 25, 'Male', 'O-', '9000000005', 'Salem'),
('Sneha R', 20, 'Female', 'B-', '9000000006', 'Trichy'),
('Vijay Kumar', 26, 'Male', 'A-', '9000000007', 'Erode'),
('Meena Devi', 22, 'Female', 'O+', '9000000008', 'Chennai'),
('Sanjay P', 28, 'Male', 'AB-', '9000000009', 'Vellore'),
('Anitha K', 24, 'Female', 'B+', '9000000010', 'Tirunelveli');
SELECT * FROM DONOR;
SELECT donor_id, donor_name, blood_group, city
FROM Donor
WHERE blood_group = 'O+';

CREATE TABLE BloodRequest(
request_id INT PRIMARY KEY auto_increment,
patient_name VARCHAR(100) not null,
blood_group VARCHAR(5) not null,
hospital VARCHAR(100) not null,
city VARCHAR(50) not null,
units_required INT not null,
request_status VARCHAR(20) DEFAULT 'Pending'
);

INSERT INTO BloodRequest
(patient_name, blood_group, hospital, city, units_required)
VALUES
('Patient A', 'O+', 'City General Hospital', 'Chennai', 2),
('Patient B', 'B+', 'Central Medical Hospital', 'Madurai', 1),
('Patient C', 'A-', 'Green Care Hospital', 'Coimbatore', 3),
('Patient D', 'AB+', 'Life Line Hospital', 'Salem', 2),
('Patient E', 'O-', 'People Care Hospital', 'Trichy', 1);
select * from bloodrequest;

show tables;

select*from donor;




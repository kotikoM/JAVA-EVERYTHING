--@Block
CREATE TABLE User (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL,
    mail VARCHAR(40) NOT NULL,
    latitude DECIMAL(9, 6) NOT NULL,
    longitude DECIMAL(9, 6) NOT NULL
);

--@Block
CREATE TABLE Task (
    taskID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description TEXT,
    due_date DATE,
    status ENUM("Complete", "Not Complete") NOT NULL,
    userID INT NOT NULL,
    FOREIGN KEY (userID) REFERENCES User(userID)
);


--@Block
CREATE TABLE Financial_Record (
    recordID INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    type ENUM("Income", "Expense") NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    description TEXT,
    userID INT NOT NULL,
    FOREIGN KEY (userID) REFERENCES user(userID)
);

--@Block
CREATE TABLE Employee (
    employeeID INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    contact_info VARCHAR(100),
    role VARCHAR(50),
    salary DECIMAL(10, 2),
    userID INT,
    FOREIGN KEY (userID) REFERENCES User(userID)
);

--@Block
CREATE TABLE Inventory_Item (
    itemID INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    employeeID INT,
    FOREIGN KEY (employeeID) REFERENCES Employee(employeeID)
);

--@Block
CREATE TABLE Crop (
    cropID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    species VARCHAR(100) NOT NULL,
    planting_date DATE,
    harvest_date DATE,
    description TEXT,
    requirements TEXT
);

--@Block
CREATE TABLE Harvest_Entry (
    employeeID INT,
    cropID INT,
    quantity_kg DECIMAL(10, 3) NOT NULL,
    date DATE NOT NULl,
    notes TEXT,
    PRIMARY KEY (employeeID, cropID),
    FOREIGN KEY (employeeID) REFERENCES employee (employeeID),
    FOREIGN KEY (cropID) REFERENCES crop (cropID)
);
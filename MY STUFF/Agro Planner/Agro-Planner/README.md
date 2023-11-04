# Agro Planner

## Description

The **Agro Planner** is a comprehensive web application designed to streamline and enhance the management of agricultural operations. This system provides a user-friendly dashboard offering a holistic view of critical information, including current tasks, weather conditions, crop status, and financial data. Whether you are a small-scale farmer or a large agricultural enterprise, this system is tailored to meet your needs.

### Key Features

- **Crop Management**: Create and manage profiles for each cultivated crop. Keep track of planting and harvesting schedules.

- **Task Scheduling**: Plan and schedule tasks tailored to your needs in order to improve efficiency.

- **Weather Integration**: Access real-time weather data, forecasts, and alerts.

- **Inventory Management**: Efficiently track and manage your inventory of seeds, fertilizers, pesticides, and equipment.

- **Financial Tools**: Keep a close eye on your farming finances by tracking expenses, income, and calculating profit/loss. Generate financial reports and statements to assist with budgeting and financial planning.

This Agricultural Management System is designed to empower farmers and agricultural professionals with the tools and information they need to make informed decisions, increase productivity, and maximize agricultural yields. Whether you are a seasoned farmer or just starting, this system is your trusted partner in agriculture.


# Controllers 

## User Controller

The `UserController` in the `com.agriculture.AgroPlanner.controller` package is part of the AgroPlanner application and is responsible for handling HTTP requests related to user management. This controller provides endpoints for creating, retrieving, and deleting user data.

### Endpoints

- `POST /users`: Insert a new user into the AgroPlanner system.
- `GET /users`: Retrieve a paginated list of all users.
- `GET /users/{userID}`: Retrieve information about a specific user by their unique ID.
- `DELETE /users/{userID}`: Delete a specific user by their unique ID.

## Task Controller

The `TaskController` in the `com.agriculture.AgroPlanner.controller` package is part of the AgroPlanner application and is responsible for handling HTTP requests related to tasks. This controller provides endpoints for creating, retrieving, and deleting task data, as well as specific endpoints for user-related tasks.

### General Task Endpoints

- `POST /tasks`: Insert a new task into the AgroPlanner system.
- `GET /tasks`: Retrieve a paginated list of all tasks.
- `GET /tasks/{taskID}`: Retrieve information about a specific task by its unique ID.
- `DELETE /tasks/{taskID}`: Delete a specific task by its unique ID.
- `PATCH /tasks/{taskId}` : Update status of specific task

### User-Specific Task Endpoints

- `GET /tasks/users/{userID}`: Retrieve a paginated list of all tasks associated with a specific user.
- `GET /tasks/users/{userID}/complete`: Retrieve a paginated list of all completed tasks associated with a specific user.
- `GET /users/{userID}/tasks/incomplete`: Retrieve a paginated list of all incomplete tasks associated with a specific user.

## Financial Record Controller

The `FinancialRecordController` in the `com.agriculture.AgroPlanner.controller` package is part of the AgroPlanner application and is responsible for handling HTTP requests related to financial records. This controller provides endpoints for creating, retrieving, and deleting financial records, as well as endpoints for financial statistics.

### General Financial Record Endpoints

- `PUT /finances`: Insert a new financial record into the AgroPlanner system.
- `GET /finances`: Retrieve a paginated list of all financial records.
- `GET /finances/{recordID}`: Retrieve information about a specific financial record by its unique ID.
- `DELETE /finances/{recordID}`: Delete a specific financial record by its unique ID.

### Financial Records by Type

- `GET /finances/incomes`: Retrieve a paginated list of income records.
- `GET /finances/expenses`: Retrieve a paginated list of expense records.

### User-Specific Financial Records

- `GET /users/{userID}/finances`: Retrieve a paginated list of financial records associated with a specific user.

## Employee Controller

The `EmployeeController` in the `com.agriculture.AgroPlanner.controller` package is part of the AgroPlanner application and is responsible for handling HTTP requests related to employee management. This controller provides endpoints for creating, retrieving, and deleting employee data, as well as an endpoint for retrieving employees associated with a specific user.

### General Employee Endpoints

- `POST /employees`: Insert a new employee into the AgroPlanner system.
- `GET /employees`: Retrieve a paginated list of all employees.
- `GET /employees/{employeeID}`: Retrieve information about a specific employee by their unique ID.
- `DELETE /employees/{employeeID}`: Delete a specific employee by their unique ID.

### User-specific Endpoints
- `GET /employees/users/{userID}`: Retrieve a paginated list of employees associated with a specific user.

## Inventory Controller

The `InventoryController` in the `com.agriculture.AgroPlanner.controller` package is part of the AgroPlanner application and is responsible for handling HTTP requests related to inventory management. This controller provides endpoints for creating, retrieving, and deleting inventory items.

### Inventory Endpoints

- `POST /inventory`: Insert a new inventory item into the AgroPlanner system.
- `GET /inventory`: Retrieve a paginated list of all inventory items.
- `GET /inventory/{itemID}`: Retrieve information about a specific inventory item by its unique ID.
- `DELETE /inventory/{itemID}`: Delete a specific inventory item by its unique ID.


## Crop Controller

The `CropController` in the `com.agriculture.AgroPlanner.controller` package is part of the AgroPlanner application and is responsible for handling HTTP requests related to crops. This controller provides endpoints for creating, retrieving, and deleting crop data.

### Endpoints

- `POST /crops`: Insert a new crop into the AgroPlanner system.
- `GET /crops`: Retrieve a paginated list of all crops.
- `GET /crops/{cropID}`: Retrieve information about a specific crop by its unique ID.
- `DELETE /crops/{cropID}`: Delete a specific crop by its unique ID.

## Harvest Controller

The `HarvestController` in the `com.agriculture.AgroPlanner.controller` package is part of the AgroPlanner application and is responsible for handling HTTP requests related to harvest entries. This controller provides endpoints for creating, retrieving, and deleting harvest entries, as well as endpoints for specific queries and statistics related to harvests.

### General Harvest Entry Endpoints

- `POST /harvest`: Insert a new harvest entry into the AgroPlanner system.
- `GET /harvest`: Retrieve a paginated list of all harvest entries.
- `GET /harvest/{employeeID}/{cropID}`: Retrieve information about a specific harvest entry by employee ID and crop ID.
- `DELETE /harvest/{employeeID}/{cropID}`: Delete a specific harvest entry by employee ID and crop ID.

### Harvest Entries by Employee

- `GET /harvest/employees/{employeeID}`: Retrieve a paginated list of harvest entries by employee ID.
- `GET /harvest/crops/{cropID}`: Retrieve a paginated list of harvest entries by crop ID.

### Harvest Entry Statistics

- `GET /harvest/crops/stats`: Retrieve statistics related to harvest entries.

## StatisticsController

The `StatisticsController` class is a RESTful controller in the `com.agriculture.AgroPlanner.controller` package that handles statistics-related requests.
It is designed to work with the AgroPlanner application. 

### Endpoints
#### Harvest Entry Statistics

- `GET /harvest/crops/stats`: Retrieve statistics related to harvest entries.
#### Financial Statistics

- `GET /finances/stats`: Retrieve financial statistics for records in the system.

## WeatherController

The `WeatherController` class is a RESTful controller in the `com.agriculture.AgroPlanner.controller` package that handles weather-related requests. 
It is designed to work with the AgroPlanner application. Under the hood is uses public weather api - [weatherapi.com](http://weatherapi.com) 

### Endpoints

This controller is mapped to the following endpoint:
- `GET /weather/{lat}/{lon}` - Retrieves current weather information and forecasts for a given latitude and longitude.


---
See full endpoint list in [`Endpoints.java`](src\main\java\com\agriculture\AgroPlanner\constants\Endpoints.java) class.  
See back end database ddl [`ddl.sql`](src/main/resources/sql/ddl.sql)

---
>**DO NOT CHANGE METHOD NAMES IN REPOSITORIES.**

# Traditional Feast Order Management System

## Overview

This Java application manages traditional feast services for special events such as weddings, anniversaries, and traditional ceremonies. The system provides comprehensive functionality for customer management, menu display, order processing, and data persistence.

## Key Features

1. **Customer Management**

   - Register new customers
   - Update customer information
   - Search for customers by name

2. **Menu Management**

   - Display feast menu options with details
   - Sort menus by price

3. **Order Processing**

   - Place new feast orders
   - Update existing order information
   - Calculate total costs based on menu prices and number of tables

4. **Data Persistence**

   - Save customer data to binary file
   - Save order data to binary file
   - Load data from files on startup

5. **Reporting**
   - Display customer lists (sorted by name)
   - Display order lists (sorted by event date)

## Technical Implementation

The system is built using object-oriented programming principles:

- **Encapsulation**: Data and methods are encapsulated within appropriate classes
- **Abstraction**: Complex operations are abstracted through service classes
- **Inheritance**: Controllers extend a base controller for common functionality
- **Polymorphism**: Used in display and validation operations

## Project Structure

The application follows the MVC (Model-View-Controller) architecture:

- **Models**: Customer, Menu, Order
- **Views**: Display, ViewMenu
- **Controllers**: Various controllers for specific operations
- **Services**: Business logic implementation
- **Validation**: Input validation logic

## Data Storage

- Customer data: `customers.dat` (binary file)
- Order data: `feast_order_service.dat` (binary file)
- Menu data: `feastMenu.csv` (CSV file)

## Usage

1. Run the application
2. Navigate through the main menu to access different features
3. Follow the prompts to perform operations
4. Save data before exiting

## Requirements

- Java 8 or higher
- Maven for dependency management

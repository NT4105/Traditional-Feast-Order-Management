package controller;

import java.util.Scanner;
import model.Customer;
import service.RegisterService;
import validation.CustomerValidation;

public class RegisterController {
    private RegisterService registerService;
    private static CustomerValidation customerValidation;

    public RegisterController() {
        this.registerService = new RegisterService();
        this.customerValidation = new CustomerValidation();
    }

    public void registerCustomer() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter customer information:");

            System.out.print("Customer ID (Cxxxx/Gxxxx/Kxxxx): ");
            String customerId = scanner.nextLine().trim();
            if (!customerValidation.isValidCustomerId(customerId)) {
                System.out.println("Invalid customer ID. Please try again.");
                continue;
            }

            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine().trim();
            if (!customerValidation.isValidCustomerName(customerName)) {
                System.out.println("Invalid customer name. Please try again.");
                continue;
            }

            System.out.print("Phone Number: ");
            String phone = scanner.nextLine().trim();
            if (!customerValidation.isValidPhone(phone)) {
                System.out.println("Invalid phone number. Please try again.");
                continue;
            }

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            if (!customerValidation.isValidEmail(email)) {
                System.out.println("Invalid email. Please try again.");
                continue;
            }

            Customer customer = new Customer(customerId, customerName, phone, email);
            if (registerService.registerCustomer(customer)) {
                System.out.println("Customer registration successful!");
            } else {
                System.out.println("Customer registration failed. Please check the information.");
            }

            System.out.print("Do you want to continue registering new customers? (Y/N): ");
            String choice = scanner.nextLine().trim().toUpperCase();
            if (choice.equals("N")) {
                break;
            }
        }
    }
}

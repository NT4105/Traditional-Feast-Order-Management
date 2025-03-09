package controller;

import model.Customer;
import service.RegisterService;
import validation.CustomerValidation;

public class RegisterController extends BaseController {
    private RegisterService registerService;

    public RegisterController() {
        this.registerService = new RegisterService();
    }

    public void registerCustomer() {
        System.out.println("\n=== Customer Registration ===");

        while (true) {
            try {
                Customer customer = getCustomerInfo();
                if (customer != null) {
                    if (registerService.registerCustomer(customer)) {
                        System.out.println("✓ Customer registered successfully!");
                        BaseController.hasUnsavedChanges = true;
                    }
                }

                if (confirmBackToMain()) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error during registration: " + e.getMessage());
            }
        }
    }

    private Customer getCustomerInfo() {
        try {
            System.out.println("\nEnter customer details:");

            String customerId;
            do {
                System.out.print("Customer ID (Cxxxx/Gxxxx/Kxxxx): ");
                customerId = scanner.nextLine().trim();
            } while (!CustomerValidation.isValidCustomerId(customerId));

            String customerName;
            do {
                System.out.print("Customer Name (2-25 characters): ");
                customerName = scanner.nextLine().trim();
            } while (!CustomerValidation.isValidCustomerName(customerName));

            String phone;
            do {
                System.out.print("Phone Number (10 digits): ");
                phone = scanner.nextLine().trim();
            } while (!CustomerValidation.isValidPhone(phone));

            String email;
            do {
                System.out.print("Email: ");
                email = scanner.nextLine().trim();
            } while (!CustomerValidation.isValidEmail(email));

            return new Customer(customerId, customerName, phone, email);

        } catch (Exception e) {
            System.out.println("Error collecting customer information: " + e.getMessage());
            return null;
        }
    }
}

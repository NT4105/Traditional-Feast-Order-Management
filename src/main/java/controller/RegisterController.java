package controller;

import model.Customer;
import service.RegisterService;
import validation.CustomerValidation;
import view.ViewMenu;

import java.util.List;

public class RegisterController extends BaseController {
    private RegisterService registerService;
    private ViewMenu viewMenu;

    public RegisterController() {
        this.registerService = new RegisterService();
        this.viewMenu = new ViewMenu();
    }

    public RegisterService getRegisterService() {
        return registerService;
    }

    public void registerCustomer() {
        System.out.println("\n=== Customer Registration ===");

        while (true) {
            viewMenu.RegisterMenu();
            int choice = getValidChoice(1, 2);

            try {
                if (choice == 1) {
                    processRegistration();
                }
                if (choice == 2) {
                    return;
                }
            } catch (Exception e) {
                System.out.println("Error during registration: " + e.getMessage());
            }
        }
    }

    private void processRegistration() {
        Customer customer = getCustomerInfo();
        if (customer == null) {
            // Người dùng đã chọn quay lại main menu
            return;
        }

        boolean registered = registerService.registerCustomer(customer);
        if (registered) {
            System.out.println("Customer registered successfully!");
            BaseController.hasUnsavedChanges = true;
        } else {
            System.out.println("Failed to register customer.");
        }

        if (confirmBackToMain()) {
            return;
        }

        // Gọi lại processRegistration nếu người dùng muốn tiếp tục
        processRegistration();
    }

    private Customer getCustomerInfo() {
        try {
            System.out.println("\nEnter customer details:");

            String customerId;
            while (true) {
                System.out.print("Customer ID (Cxxxx/Gxxxx/Kxxxx): ");
                customerId = scanner.nextLine().trim();

                // Kiểm tra ID hợp lệ
                if (!CustomerValidation.isValidCustomerId(customerId)) {
                    continue;
                }

                // Kiểm tra ID đã tồn tại
                List<Customer> existingCustomers = registerService.getAllCustomers();
                if (CustomerValidation.isCustomerIdRegistered(customerId, existingCustomers)) {
                    System.out.println("Error: Customer ID already exists! Please use a different ID.");

                    // Hỏi người dùng có muốn tiếp tục hay không
                    System.out.print("Do you want to try another ID? (Y/N): ");
                    String choice = scanner.nextLine().trim().toUpperCase();
                    if (choice.equals("N")) {
                        return null; // Người dùng chọn quay lại main menu
                    }
                    continue;
                }

                break;
            }

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

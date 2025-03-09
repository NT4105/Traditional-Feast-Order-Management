package controller;

import model.Customer;
import service.UpdateService;
import validation.CustomerValidation;

public class UpdateController extends BaseController {
    private UpdateService updateService;

    public UpdateController() {
        this.updateService = new UpdateService();
    }

    public void updateCustomer() {
        System.out.println("\n=== Update Customer Information ===");

        while (true) {
            try {
                // Get customer ID
                System.out.print("\nEnter Customer ID to update (Cxxxx/Gxxxx/Kxxxx): ");
                String customerId = scanner.nextLine().trim();

                // Validate customer ID format
                if (!CustomerValidation.isValidCustomerId(customerId)) {
                    continue;
                }

                // Find customer
                Customer customer = updateService.findCustomerById(customerId);
                if (customer == null) {
                    System.out.println("❌ This customer does not exist.");
                    continue;
                }

                // Display current information
                System.out.println("\nCurrent Customer Information:");
                System.out.println("Name: " + customer.getCustomerName());
                System.out.println("Phone: " + customer.getPhone());
                System.out.println("Email: " + customer.getEmail());

                // Update fields
                updateCustomerFields(customer);

                if (confirmBackToMain()) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error during update: " + e.getMessage());
            }
        }
    }

    private void updateCustomerFields(Customer customer) {
        // Update name
        System.out.print("\nEnter new name (press Enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            if (CustomerValidation.isValidCustomerName(newName)) {
                customer.setCustomerName(newName);
            }
        }

        // Update phone
        System.out.print("Enter new phone (press Enter to keep current): ");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.isEmpty()) {
            if (CustomerValidation.isValidPhone(newPhone)) {
                customer.setPhone(newPhone);
            }
        }

        // Update email
        System.out.print("Enter new email (press Enter to keep current): ");
        String newEmail = scanner.nextLine().trim();
        if (!newEmail.isEmpty()) {
            if (CustomerValidation.isValidEmail(newEmail)) {
                customer.setEmail(newEmail);
            }
        }

        if (updateService.updateCustomer(customer)) {
            System.out.println("✓ Customer information updated successfully!");
            BaseController.hasUnsavedChanges = true;
        }
    }
}
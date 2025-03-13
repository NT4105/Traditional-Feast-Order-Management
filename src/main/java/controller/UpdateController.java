package controller;

import model.Customer;
import service.UpdateService;
import validation.CustomerValidation;
import view.ViewMenu;
import service.RegisterService;

public class UpdateController extends BaseController {
    private UpdateService updateService;
    private ViewMenu viewMenu;

    public UpdateController(RegisterService registerService) {
        this.updateService = new UpdateService(registerService);
        this.viewMenu = new ViewMenu();
    }

    public void updateCustomer() {
        System.out.println("\n=== Update Customer Information ===");

        while (true) {
            viewMenu.UpdateMenu();
            int choice = getValidChoice(1, 2);

            try {
                while (true) {
                    if (choice == 1) {
                        processUpdate();
                        if (confirmBackToMain()) {
                            return;
                        }
                    }
                    if (choice == 2) {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error during update: " + e.getMessage());
            }
        }
    }

    private void processUpdate() {
        System.out.print("\nEnter Customer ID to update (Cxxxx/Gxxxx/Kxxxx): ");
        String customerId = scanner.nextLine().trim();

        if (!CustomerValidation.isValidCustomerId(customerId)) {
            return;
        }

        Customer customer = updateService.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("This customer does not exist.");
            return;
        }

        System.out.println("\nCurrent Customer Information:");
        System.out.println("Name: " + customer.getCustomerName());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("Email: " + customer.getEmail());

        updateCustomerFields(customer);
    }

    private void updateCustomerFields(Customer customer) {
        System.out.print("\nEnter new name (press Enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            if (CustomerValidation.isValidCustomerName(newName)) {
                customer.setCustomerName(newName);
            }
        }

        System.out.print("Enter new phone (press Enter to keep current): ");
        String newPhone = scanner.nextLine().trim();
        if (!newPhone.isEmpty()) {
            if (CustomerValidation.isValidPhone(newPhone)) {
                customer.setPhone(newPhone);
            }
        }

        System.out.print("Enter new email (press Enter to keep current): ");
        String newEmail = scanner.nextLine().trim();
        if (!newEmail.isEmpty()) {
            if (CustomerValidation.isValidEmail(newEmail)) {
                customer.setEmail(newEmail);
            }
        }

        if (updateService.updateCustomer(customer)) {
            System.out.println("Customer information updated successfully!");
            BaseController.hasUnsavedChanges = true;
        }
    }
}
package controller;

import model.Customer;
import service.UpdateService;
import validation.CustomerValidation;
import view.ViewMenu;
import service.RegisterService;
import controller.ReadFileController;
import java.util.List;

public class UpdateController extends BaseController {
    private UpdateService updateService;
    private ViewMenu viewMenu;
    private ReadFileController readFileController;

    public UpdateController(RegisterService registerService, ReadFileController readFileController) {
        this.updateService = new UpdateService(registerService, readFileController);
        this.viewMenu = new ViewMenu();
        this.readFileController = readFileController;
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
        // Đọc danh sách khách hàng từ file
        List<Customer> customers = readFileController.readCustomersFromFile();

        if (customers.isEmpty()) {
            System.out.println("No customers found in the system.");
            return;
        }

        System.out.print("\nEnter Customer ID to update: ");
        String customerId = scanner.nextLine().trim();

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
        // Lưu thông tin hiện tại để so sánh sau này
        String originalName = customer.getCustomerName();
        String originalPhone = customer.getPhone();
        String originalEmail = customer.getEmail();

        // Cập nhật tên
        String newName;
        boolean validName = false;
        do {
            System.out.print("Enter new name (press Enter to keep current): ");
            newName = scanner.nextLine().trim();

            // Nếu người dùng không nhập gì, giữ nguyên tên hiện tại
            if (newName.isEmpty()) {
                newName = originalName;
                validName = true;
            } else {
                // Kiểm tra tính hợp lệ của tên mới
                validName = CustomerValidation.isValidCustomerName(newName);
            }
        } while (!validName);
        customer.setCustomerName(newName);

        // Cập nhật số điện thoại
        String newPhone;
        boolean validPhone = false;
        do {
            System.out.print("Enter new phone (press Enter to keep current): ");
            newPhone = scanner.nextLine().trim();

            // Nếu người dùng không nhập gì, giữ nguyên số điện thoại hiện tại
            if (newPhone.isEmpty()) {
                newPhone = originalPhone;
                validPhone = true;
            } else {
                // Kiểm tra tính hợp lệ của số điện thoại mới
                validPhone = CustomerValidation.isValidPhone(newPhone);
            }
        } while (!validPhone);
        customer.setPhone(newPhone);

        // Cập nhật email
        String newEmail;
        boolean validEmail = false;
        do {
            System.out.print("Enter new email (press Enter to keep current): ");
            newEmail = scanner.nextLine().trim();

            // Nếu người dùng không nhập gì, giữ nguyên email hiện tại
            if (newEmail.isEmpty()) {
                newEmail = originalEmail;
                validEmail = true;
            } else {
                // Kiểm tra tính hợp lệ của email mới
                validEmail = CustomerValidation.isValidEmail(newEmail);
            }
        } while (!validEmail);
        customer.setEmail(newEmail);

        // Cập nhật thông tin khách hàng
        boolean updated = updateService.updateCustomer(customer);
        if (updated) {
            System.out.println("Customer information updated successfully!");
            BaseController.hasUnsavedChanges = true;
        } else {
            System.out.println("Failed to update customer information.");
        }
    }
}
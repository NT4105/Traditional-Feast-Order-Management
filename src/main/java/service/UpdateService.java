package service;

import java.util.List;
import java.util.ArrayList;
import model.Customer;
import controller.ReadFileController;
import service.SaveDataService;
import controller.BaseController;

public class UpdateService {
    private final RegisterService registerService;
    private final ReadFileController readFileController;
    private final SaveDataService saveDataService;

    public UpdateService(RegisterService registerService, ReadFileController readFileController) {
        this.registerService = registerService;
        this.readFileController = readFileController;
        this.saveDataService = new SaveDataService();
    }

    public Customer findCustomerById(String customerId) {
        // Lấy danh sách khách hàng từ RegisterService (đã bao gồm cả khách hàng trong
        // bộ nhớ và từ file)
        List<Customer> customers = registerService.getAllCustomers();

        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public boolean updateCustomer(Customer updatedCustomer) {
        try {
            // Lấy danh sách khách hàng từ RegisterService
            List<Customer> allCustomers = registerService.getAllCustomers();

            // Tạo danh sách mới để lưu trữ khách hàng đã cập nhật
            List<Customer> updatedCustomers = new ArrayList<>();

            // Tìm và cập nhật khách hàng
            boolean found = false;
            for (Customer customer : allCustomers) {
                if (customer.getCustomerId().equals(updatedCustomer.getCustomerId())) {
                    // Thêm khách hàng đã cập nhật vào danh sách mới
                    updatedCustomers.add(updatedCustomer);
                    found = true;
                } else {
                    // Thêm khách hàng không thay đổi vào danh sách mới
                    updatedCustomers.add(customer);
                }
            }

            if (!found) {
                return false;
            }

            // Cập nhật danh sách khách hàng trong RegisterService
            registerService.updateCustomerList(updatedCustomers);

            // Lưu danh sách đã cập nhật vào file
            saveDataService.saveCustomersToFile(updatedCustomers);

            // Đánh dấu có thay đổi chưa lưu
            controller.BaseController.hasUnsavedChanges = true;

            return true;
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
            return false;
        }
    }

    public void updateCustomerInfo(Customer customer) {
        List<Customer> existingCustomers = readFileController.readCustomersFromFile();

        // Find the customer to update
        for (int i = 0; i < existingCustomers.size(); i++) {
            Customer existingCustomer = existingCustomers.get(i);
            if (existingCustomer.getCustomerId().equals(customer.getCustomerId())) {
                // Update the customer's information
                existingCustomers.set(i, customer); // Replace with the updated customer
                // Optionally, save the updated list back to the file
                // saveDataService.saveCustomersToFile(existingCustomers);
                System.out.println("Customer information updated successfully.");
                return; // Exit after updating
            }
        }
        System.out.println("Customer not found for update.");
    }
}
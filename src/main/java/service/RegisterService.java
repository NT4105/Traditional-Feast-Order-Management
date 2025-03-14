package service;

import java.util.ArrayList;
import java.util.List;
import model.Customer;
import java.util.HashSet;
import java.util.Set;
import controller.ReadFileController;
import validation.CustomerValidation;

public class RegisterService {
    private final List<Customer> customers;
    private final Set<String> registeredCustomerIds;
    private final ReadFileController readFileController;

    public RegisterService() {
        this.customers = new ArrayList<>();
        this.registeredCustomerIds = new HashSet<>();
        this.readFileController = new ReadFileController();
        loadCustomersFromFile(); // Load existing customers from file
    }

    private void loadCustomersFromFile() {
        List<Customer> loadedCustomers = readFileController.readCustomersFromFile();
        customers.addAll(loadedCustomers);
        for (Customer customer : loadedCustomers) {
            registeredCustomerIds.add(customer.getCustomerId());
        }
    }

    public boolean registerCustomer(Customer customer) {
        // Đọc danh sách khách hàng hiện có từ file để kiểm tra trùng lặp
        List<Customer> existingCustomers = readFileController.readCustomersFromFile();

        // Kiểm tra trùng lặp ID
        boolean isDuplicate = false;
        for (Customer existingCustomer : existingCustomers) {
            if (existingCustomer.getCustomerId().equals(customer.getCustomerId())) {
                isDuplicate = true;
                break;
            }
        }

        if (isDuplicate) {
            System.out.println("Error: Customer ID already exists!");
            return false;
        }

        // Thêm vào danh sách nếu không trùng lặp
        registeredCustomerIds.add(customer.getCustomerId());
        customers.add(customer);
        return true;
    }

    public List<Customer> getAllCustomers() {
        // Kết hợp danh sách khách hàng trong bộ nhớ và từ file
        List<Customer> allCustomers = new ArrayList<>(customers); // Danh sách trong bộ nhớ

        // Thêm khách hàng từ file nếu chưa có trong danh sách bộ nhớ
        List<Customer> fileCustomers = readFileController.readCustomersFromFile();
        for (Customer fileCustomer : fileCustomers) {
            boolean exists = false;
            for (Customer memCustomer : customers) {
                if (memCustomer.getCustomerId().equals(fileCustomer.getCustomerId())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                allCustomers.add(fileCustomer);
            }
        }

        return allCustomers;
    }

    public boolean isCustomerIdRegistered(String customerId) {
        List<Customer> existingCustomers = readFileController.readCustomersFromFile();
        return existingCustomers.stream().anyMatch(c -> c.getCustomerId().equals(customerId));
    }

    public void refreshCustomerList(List<Customer> updatedCustomers) {
        if (updatedCustomers != null) {
            customers.clear();
            registeredCustomerIds.clear();

            customers.addAll(updatedCustomers);
            for (Customer customer : updatedCustomers) {
                registeredCustomerIds.add(customer.getCustomerId());
            }
        }
    }

    public void updateCustomerList(List<Customer> updatedCustomers) {
        // Xóa danh sách hiện tại
        customers.clear();
        registeredCustomerIds.clear();

        // Thêm khách hàng đã cập nhật vào danh sách
        customers.addAll(updatedCustomers);
        for (Customer customer : updatedCustomers) {
            registeredCustomerIds.add(customer.getCustomerId());
        }
    }
}

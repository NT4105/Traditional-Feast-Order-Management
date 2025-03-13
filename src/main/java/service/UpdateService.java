package service;

import java.util.List;
import model.Customer;

public class UpdateService {
    private final RegisterService registerService;

    public UpdateService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public Customer findCustomerById(String customerId) {
        List<Customer> customers = registerService.getAllCustomers();
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    public boolean updateCustomer(Customer customer) {
        try {
            // The actual update happens automatically since we're working with the
            // reference
            // Just verify the update was successful
            Customer updatedCustomer = findCustomerById(customer.getCustomerId());
            return updatedCustomer != null &&
                    updatedCustomer.getCustomerName().equals(customer.getCustomerName()) &&
                    updatedCustomer.getPhone().equals(customer.getPhone()) &&
                    updatedCustomer.getEmail().equals(customer.getEmail());
        } catch (Exception e) {
            System.out.println("❌ Error updating customer: " + e.getMessage());
            return false;
        }
    }
}
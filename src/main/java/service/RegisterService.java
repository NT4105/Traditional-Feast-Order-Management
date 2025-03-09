package service;

import java.util.ArrayList;
import java.util.List;
import model.Customer;
import java.util.HashSet;
import java.util.Set;

public class RegisterService {
    private List<Customer> customers;
    private Set<String> registeredCustomerIds;

    public RegisterService() {
        this.customers = new ArrayList<>();
        this.registeredCustomerIds = new HashSet<>();
    }

    public boolean registerCustomer(Customer customer) {
        try {
            // Check if the customer ID is unique
            if (registeredCustomerIds.contains(customer.getCustomerId())) {
                System.out.println("Error: Customer ID already exists!");
                return false;
            }

            // Add the customer
            registeredCustomerIds.add(customer.getCustomerId());
            customers.add(customer);
            return true;

        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public boolean isCustomerIdRegistered(String customerId) {
        return registeredCustomerIds.contains(customerId);
    }
}

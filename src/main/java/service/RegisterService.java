package service;

import java.util.ArrayList;
import java.util.List;
import model.Customer;
import validation.CustomerValidation;
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
        // Validate all fields
        if (!CustomerValidation.isValidCustomerId(customer.getCustomerId()) ||
                !CustomerValidation.isValidCustomerName(customer.getCustomerName()) ||
                !CustomerValidation.isValidPhone(customer.getPhone()) ||
                !CustomerValidation.isValidEmail(customer.getEmail())) {
            return false;
        }

        // Check if the customer ID is unique
        if (registeredCustomerIds.contains(customer.getCustomerId())) {
            System.out.println("Customer ID already exists!");
            return false;
        }

        // Add the customer ID to the set
        registeredCustomerIds.add(customer.getCustomerId());
        customers.add(customer);
        return true;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}

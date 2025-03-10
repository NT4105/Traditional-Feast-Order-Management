package service;

import model.Customer;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private RegisterService registerService;

    public SearchService() {
        this.registerService = new RegisterService();
    }

    public List<Customer> searchCustomersByName(String searchName) {
        List<Customer> allCustomers = registerService.getAllCustomers();

        return allCustomers.stream()
                .filter(customer -> customer.getCustomerName()
                        .toLowerCase()
                        .contains(searchName.toLowerCase()))
                .sorted((c1, c2) -> c1.getCustomerName()
                        .compareToIgnoreCase(c2.getCustomerName()))
                .collect(Collectors.toList());
    }
}
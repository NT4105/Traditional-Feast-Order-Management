package service;

import model.Customer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

public class SearchService {
    private final RegisterService registerService;

    public SearchService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public List<Customer> searchCustomersByName(String searchName) {
        List<Customer> allCustomers = registerService.getAllCustomers();
        String searchTerm = searchName.toLowerCase().trim();

        return allCustomers.stream()
                .filter(customer -> {
                    // Tách họ tên thành các từ riêng biệt
                    String[] nameParts = customer.getCustomerName()
                            .toLowerCase()
                            .split("\\s+|,\\s*");

                    // Kiểm tra xem searchTerm có xuất hiện trong bất kỳ phần nào của tên
                    return Arrays.stream(nameParts)
                            .anyMatch(part -> part.contains(searchTerm));
                })
                .sorted((c1, c2) -> c1.getCustomerName()
                        .compareToIgnoreCase(c2.getCustomerName()))
                .collect(Collectors.toList());
    }
}
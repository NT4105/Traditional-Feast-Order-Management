package view;

import model.Customer;
import java.util.List;

public class Display {
    public void displaySearchResults(List<Customer> customers, String searchName) {
        if (customers.isEmpty()) {
            System.out.println("No one matches the search criteria!");
            return;
        }

        System.out.println("Matching Customers: " + searchName);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-10s | %-25s\n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("------------------------------------------------------------------");

        for (Customer customer : customers) {
            System.out.printf("%-8s | %-20s | %-10s | %-25s\n",
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getPhone(),
                    customer.getEmail());
        }
        System.out.println("------------------------------------------------------------------");
    }
}
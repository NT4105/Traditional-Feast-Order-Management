package service;

import model.Customer;
import model.Order;
import java.util.List;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import controller.ReadFileController;
import service.RegisterService;
import service.PlaceOrderService;

public class DisplayListsService {
    private final RegisterService registerService;
    private final PlaceOrderService placeOrderService;

    private final ReadFileController readFileController;

    public DisplayListsService(RegisterService registerService) {
        this.registerService = registerService;
        this.placeOrderService = new PlaceOrderService(registerService);
        this.readFileController = new ReadFileController();
    }

    public List<Customer> getSortedCustomers() {
        List<Customer> customers = readFileController.readCustomersFromFile();

        // Sort by last name, then by rest of name
        customers.sort((c1, c2) -> {
            String[] name1Parts = c1.getCustomerName().split("\\s+");
            String[] name2Parts = c2.getCustomerName().split("\\s+");

            String lastName1 = name1Parts[name1Parts.length - 1];
            String lastName2 = name2Parts[name2Parts.length - 1];

            // First compare last names
            int lastNameCompare = lastName1.compareToIgnoreCase(lastName2);
            if (lastNameCompare != 0) {
                return lastNameCompare;
            }

            // If last names are same, compare full names
            return c1.getCustomerName().compareToIgnoreCase(c2.getCustomerName());
        });

        return customers;
    }

    public List<Order> getSortedOrders() {
        List<Order> orders = readFileController.readOrdersFromFile();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        orders.sort(Comparator.comparing(order -> LocalDate.parse(order.getEventDate(), formatter)));
        return orders;
    }

    public boolean hasCustomers() {
        return !readFileController.readCustomersFromFile().isEmpty();
    }

    public boolean hasOrders() {
        return !readFileController.readOrdersFromFile().isEmpty();
    }
}
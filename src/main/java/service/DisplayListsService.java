package service;

import model.Customer;
import model.Order;
import java.util.List;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import controller.ReadFileController;

public class DisplayListsService {
    private final RegisterService registerService;
    private final PlaceOrderService placeOrderService;
    private final ReadFileController readFileController;

    public DisplayListsService() {
        this.registerService = new RegisterService();
        this.placeOrderService = new PlaceOrderService();
        this.readFileController = new ReadFileController();
    }

    public List<Customer> getSortedCustomers() {
        List<Customer> customers = readFileController.readCustomersFromFile();
        customers.sort(Comparator.comparing(Customer::getCustomerName));
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
package service;

import model.Customer;
import model.Order;
import java.io.*;
import java.util.List;

public class SaveDataService {
    private static final String CUSTOMERS_FILE = "src/main/resources/customers.dat";
    private static final String ORDERS_FILE = "src/main/resources/feast_order_service.dat";

    public boolean saveCustomersToFile(List<Customer> customers) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(CUSTOMERS_FILE))) {
            oos.writeObject(customers);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
            return false;
        }
    }

    public boolean saveOrdersToFile(List<Order> orders) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(ORDERS_FILE))) {
            oos.writeObject(orders);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
            return false;
        }
    }
}
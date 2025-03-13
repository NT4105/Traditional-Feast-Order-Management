package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Order;
import model.Customer;
import model.Menu;

public class PlaceOrderService {
    private final List<Order> orders;
    private final RegisterService registerService;
    private final DisplayMenusService menuService;

    public PlaceOrderService() {
        this.orders = new ArrayList<>();
        this.registerService = new RegisterService();
        this.menuService = new DisplayMenusService();
    }

    public Customer findCustomerById(String customerId) {
        return registerService.getAllCustomers().stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);
    }

    public Menu findMenuById(String menuId) {
        return menuService.getAllMenus().stream()
                .filter(m -> m.getMenuId().equals(menuId))
                .findFirst()
                .orElse(null);
    }

    public boolean isDuplicateOrder(String customerId, String menuId, String eventDate) {
        return orders.stream().anyMatch(order -> order.getCustomerId().equals(customerId) &&
                order.getMenuId().equals(menuId) &&
                order.getEventDate().equals(eventDate));
    }

    public String generateOrderId() {
        Random random = new Random();
        while (true) {
            int id = random.nextInt(90) + 10; // Generates number between 10-99
            String orderId = String.valueOf(id);
            if (orders.stream().noneMatch(order -> order.getOrderId().equals(orderId))) {
                return orderId;
            }
        }
    }

    public Order createOrder(String customerId, String menuId, String eventDate, int numberOfTables) {
        String orderId = generateOrderId();
        Order order = new Order(orderId, customerId, menuId, eventDate, numberOfTables);
        Menu menu = findMenuById(menuId);
        order.setTotalCost(menu.getPrice() * numberOfTables);
        orders.add(order);
        return order;
    }

    public Order findOrderById(String orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
}
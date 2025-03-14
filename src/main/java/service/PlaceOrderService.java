package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Order;
import model.Customer;
import model.Menu;
import controller.ReadFileController;
import service.SaveDataService;

public class PlaceOrderService {
    private List<Order> orders;
    private final RegisterService registerService;
    private final DisplayMenusService menuService;
    private final ReadFileController readFileController;
    private final SaveDataService saveDataService;

    public PlaceOrderService(RegisterService registerService) {
        this.registerService = registerService;
        this.menuService = new DisplayMenusService();
        this.readFileController = new ReadFileController();
        this.saveDataService = new SaveDataService();
        this.orders = new ArrayList<>();
        loadOrdersFromFile(); // Load existing orders from file
    }

    private void loadOrdersFromFile() {
        List<Order> loadedOrders = readFileController.readOrdersFromFile();
        if (loadedOrders != null && !loadedOrders.isEmpty()) {
            orders.clear();
            orders.addAll(loadedOrders);
        }
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
        // Đọc danh sách đơn hàng từ file để kiểm tra trùng lặp
        List<Order> existingOrders = readFileController.readOrdersFromFile();
        return existingOrders.stream().anyMatch(order -> order.getCustomerId().equals(customerId) &&
                order.getMenuId().equals(menuId) &&
                order.getEventDate().equals(eventDate));
    }

    public String generateOrderId() {
        // Đọc danh sách đơn hàng từ file để kiểm tra ID
        List<Order> existingOrders = readFileController.readOrdersFromFile();
        Random random = new Random();
        while (true) {
            int id = random.nextInt(90) + 10; // Generates number between 10-99
            String orderId = String.valueOf(id);
            if (existingOrders.stream().noneMatch(order -> order.getOrderId().equals(orderId))) {
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

        // Lưu đơn hàng mới vào file
        List<Order> existingOrders = readFileController.readOrdersFromFile();
        existingOrders.add(order);
        saveDataService.saveOrdersToFile(existingOrders);

        return order;
    }

    public Order findOrderById(String orderId) {
        // Đọc từ file để có dữ liệu mới nhất
        List<Order> existingOrders = readFileController.readOrdersFromFile();
        return existingOrders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public List<Order> getAllOrders() {
        // Đảm bảo trả về danh sách mới nhất từ file
        loadOrdersFromFile();
        return new ArrayList<>(orders);
    }
}
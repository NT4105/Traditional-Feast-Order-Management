package service;

import model.Order;
import model.Menu;
import service.RegisterService;
import service.PlaceOrderService;
import service.DisplayMenusService;
import java.util.List;
import controller.ReadFileController;
import service.SaveDataService;

public class UpdateOrderService {
    private final PlaceOrderService placeOrderService;
    private final DisplayMenusService menuService;
    private final ReadFileController readFileController;
    private final SaveDataService saveDataService;

    public UpdateOrderService(PlaceOrderService placeOrderService, ReadFileController readFileController) {
        this.placeOrderService = placeOrderService;
        this.menuService = new DisplayMenusService();
        this.readFileController = readFileController;
        this.saveDataService = new SaveDataService();
    }

    public Order findOrderById(String orderId) {
        // Đọc trực tiếp từ file để có dữ liệu mới nhất
        List<Order> existingOrders = readFileController.readOrdersFromFile();
        return existingOrders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public Menu findMenuById(String menuId) {
        return menuService.getAllMenus().stream()
                .filter(m -> m.getMenuId().equals(menuId))
                .findFirst()
                .orElse(null);
    }

    public double calculateTotalCost(String menuId, int numberOfTables) {
        Menu menu = findMenuById(menuId);
        if (menu != null) {
            return menu.getPrice() * numberOfTables;
        }
        return 0;
    }

    public boolean updateOrder(Order updatedOrder) {
        try {
            // Đọc danh sách đơn hàng từ file
            List<Order> existingOrders = readFileController.readOrdersFromFile();

            // Tìm và cập nhật đơn hàng
            boolean updated = false;
            for (int i = 0; i < existingOrders.size(); i++) {
                if (existingOrders.get(i).getOrderId().equals(updatedOrder.getOrderId())) {
                    existingOrders.set(i, updatedOrder);
                    updated = true;
                    break;
                }
            }

            if (updated) {
                // Lưu danh sách đã cập nhật vào file
                saveDataService.saveOrdersToFile(existingOrders);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error updating order: " + e.getMessage());
            return false;
        }
    }
}
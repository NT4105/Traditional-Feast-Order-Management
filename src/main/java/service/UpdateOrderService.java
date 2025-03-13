package service;

import model.Order;
import model.Menu;

public class UpdateOrderService {
    private final PlaceOrderService placeOrderService;
    private final DisplayMenusService menuService;

    public UpdateOrderService() {
        this.placeOrderService = new PlaceOrderService();
        this.menuService = new DisplayMenusService();
    }

    public Order findOrderById(String orderId) {
        return placeOrderService.findOrderById(orderId);
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
}
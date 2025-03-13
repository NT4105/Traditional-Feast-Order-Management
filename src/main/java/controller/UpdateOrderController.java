package controller;

import model.Order;
import model.Customer;
import model.Menu;
import service.PlaceOrderService;
import validation.OrderValidation;
import view.Display;
import view.ViewMenu;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateOrderController extends BaseController {
    private PlaceOrderService placeOrderService;
    private Display display;
    private ViewMenu viewMenu;
    private DisplayMenusController displayMenusController;

    public UpdateOrderController() {
        this.placeOrderService = new PlaceOrderService();
        this.display = new Display();
        this.viewMenu = new ViewMenu();
        this.displayMenusController = new DisplayMenusController();
    }

    public void updateOrder() {
        System.out.println("\n=== Update Order Information ===");

        while (true) {
            viewMenu.UpdateOrderMenu();
            int choice = getValidChoice(1, 2);

            try {
                while (true) {
                    if (choice == 1) {
                        processOrderUpdate();

                        if (confirmBackToMain()) {
                            return;
                        }
                    }
                    if (choice == 2) {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error updating order: " + e.getMessage());
            }
        }
    }

    private void processOrderUpdate() {
        System.out.print("\nEnter Order ID to update: ");
        String orderId = scanner.nextLine().trim();

        Order order = placeOrderService.findOrderById(orderId);
        if (order == null) {
            System.out.println("This Order does not exist.");
            return;
        }

        // Check if event date is in the past
        LocalDate eventDate = LocalDate.parse(order.getEventDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (eventDate.isBefore(LocalDate.now())) {
            System.out.println("Cannot update orders with past event dates!");
            return;
        }

        // Display current order information
        Customer customer = placeOrderService.findCustomerById(order.getCustomerId());
        Menu currentMenu = placeOrderService.findMenuById(order.getMenuId());
        display.displayOrderDetails(order, customer, currentMenu);

        // Update menu if desired
        System.out.println("\nUpdating order information (press Enter to keep current values)");

        // Display available menus before asking for new menu ID
        System.out.println("\nAvailable menus:");
        displayMenusController.displayFeastMenus();

        System.out.print("Enter new Menu ID (current: " + order.getMenuId() + "): ");
        String newMenuId = scanner.nextLine().trim();
        if (!newMenuId.isEmpty()) {
            Menu newMenu = placeOrderService.findMenuById(newMenuId);
            if (newMenu != null) {
                order.setMenuId(newMenuId);
                order.setTotalCost(newMenu.getPrice() * order.getNumberOfTables());
            } else {
                System.out.println("Invalid menu ID! Keeping current menu.");
            }
        }

        // Update number of tables if desired
        System.out.print("Enter new number of tables (current: " + order.getNumberOfTables() + "): ");
        String tablesInput = scanner.nextLine().trim();
        if (!tablesInput.isEmpty()) {
            try {
                int newTables = Integer.parseInt(tablesInput);
                if (OrderValidation.isValidNumberOfTables(newTables)) {
                    order.setNumberOfTables(newTables);
                    Menu menu = placeOrderService.findMenuById(order.getMenuId());
                    order.setTotalCost(menu.getPrice() * newTables);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format! Keeping current number of tables.");
            }
        }

        // Update event date if desired
        System.out.print("Enter new event date (current: " + order.getEventDate() + "): ");
        String newDate = scanner.nextLine().trim();
        if (!newDate.isEmpty() && OrderValidation.isValidEventDate(newDate)) {
            order.setEventDate(newDate);
        }

        // Display updated order information
        System.out.println("\nUpdated order information:");
        Menu updatedMenu = placeOrderService.findMenuById(order.getMenuId());
        display.displayOrderDetails(order, customer, updatedMenu);

        BaseController.hasUnsavedChanges = true;
        System.out.println("Order updated successfully!");
    }
}
package controller;

import model.Customer;
import model.Order;
import view.Display;
import view.ViewMenu;
import service.DisplayListsService;
import java.util.List;
import service.RegisterService;
import service.PlaceOrderService;

public class DisplayListsController extends BaseController {
    private DisplayListsService displayListsService;
    private Display display;
    private ViewMenu viewMenu;
    private RegisterService registerService;

    public DisplayListsController(RegisterService registerService) {
        this.registerService = registerService;
        this.displayListsService = new DisplayListsService(registerService);
        this.display = new Display(new PlaceOrderService(registerService));
        this.viewMenu = new ViewMenu();
    }

    public void displayLists() {
        while (true) {
            System.out.println("\n=== Display Lists ===");
            viewMenu.DisplayListsMenu();
            int choice = getValidChoice(1, 2);

            try {
                while (true) {
                    if (choice == 1) {
                        displaySubMenu();
                        break;
                    }
                    if (choice == 2) {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error displaying lists: " + e.getMessage());
            }
        }
    }

    private void displaySubMenu() {
        System.out.println("\n[1] Display Customer List");
        System.out.println("[2] Display Order List");
        System.out.println("[3] Back");

        int choice = getValidChoice(1, 3);

        while (true) {
            if (choice == 1) {
                displayCustomerList();
                break;
            }
            if (choice == 2) {
                displayOrderList();
                break;
            }
            if (choice == 3) {
                return;
            }
        }

        if (confirmBackToMain()) {
            return;
        }
    }

    private void displayCustomerList() {
        if (!displayListsService.hasCustomers()) {
            System.out.println("Does not have any customer information.");
            return;
        }

        List<Customer> customers = displayListsService.getSortedCustomers();
        display.displayCustomerList(customers);
    }

    private void displayOrderList() {
        if (!displayListsService.hasOrders()) {
            System.out.println("No orders in the system.");
            return;
        }

        List<Order> orders = displayListsService.getSortedOrders();
        display.displayOrderList(orders);
    }
}
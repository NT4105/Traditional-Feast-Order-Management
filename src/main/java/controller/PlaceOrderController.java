package controller;

import model.Order;
import model.Customer;
import model.Menu;
import service.PlaceOrderService;
import validation.OrderValidation;
import validation.CustomerValidation;
import view.Display;
import view.ViewMenu;
import service.RegisterService;
import service.DisplayMenusService;
import java.util.List;
import controller.ReadFileController;

public class PlaceOrderController extends BaseController {
    private PlaceOrderService placeOrderService;
    private Display display;
    private DisplayMenusController displayMenusController;
    private ViewMenu viewMenu;
    private RegisterService registerService;
    private DisplayMenusService displayMenusService;
    private ReadFileController readFileController;

    public PlaceOrderController(RegisterService registerService, ReadFileController readFileController) {
        this.registerService = registerService;
        this.placeOrderService = new PlaceOrderService(registerService);
        this.display = new Display(placeOrderService);
        this.displayMenusController = new DisplayMenusController(registerService);
        this.viewMenu = new ViewMenu();
        this.displayMenusService = new DisplayMenusService();
    }

    public void placeOrder() {
        System.out.println("\n=== Place Feast Order ===");
        viewMenu.PlaceOrderMenu();
        int choice = getValidChoice(1, 2);

        try {
            while (true) {
                if (choice == 1) {
                    // Get and validate customer ID
                    String customerId;
                    Customer customer = null;
                    do {
                        System.out.print("Enter Customer ID (Cxxxx/Gxxxx/Kxxxx): ");
                        customerId = scanner.nextLine().trim();
                        if (!CustomerValidation.isValidCustomerId(customerId)) {
                            continue;
                        }
                        customer = placeOrderService.findCustomerById(customerId);
                        if (customer == null) {
                            System.out.println("Customer not found!");
                        }
                    } while (customer == null);

                    // Display available menus before asking for menu ID
                    List<Menu> menuList = displayMenusService.getAllMenus();
                    display.displayMenuList(menuList);

                    // Get and validate menu ID
                    String menuId;
                    Menu menu;
                    do {
                        System.out.print("Enter Menu ID from the list above: ");
                        menuId = scanner.nextLine().trim();
                        menu = placeOrderService.findMenuById(menuId);
                        if (menu == null) {
                            System.out.println("Invalid menu ID! Please choose from the displayed list.");
                        }
                    } while (menu == null);

                    // Get and validate number of tables
                    int numberOfTables;
                    do {
                        System.out.print("Enter number of tables: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Please enter a valid number!");
                            scanner.next();
                        }
                        numberOfTables = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                    } while (!OrderValidation.isValidNumberOfTables(numberOfTables));

                    // Get and validate event date
                    String eventDate;
                    do {
                        System.out.print("Enter event date (dd/MM/yyyy): ");
                        eventDate = scanner.nextLine().trim();
                    } while (!OrderValidation.isValidEventDate(eventDate));

                    // Check for duplicate order
                    if (placeOrderService.isDuplicateOrder(customerId, menuId, eventDate)) {
                        System.out.println("Duplicate data!");
                        return;
                    }

                    // Create order and save
                    Order order = placeOrderService.createOrder(customerId, menuId, eventDate, numberOfTables);
                    System.out.println("Order successfully!");

                    display.displayOrderDetails(order, customer, menu);

                    if (confirmBackToMain()) {
                        return;
                    }
                }
                if (choice == 2) {
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Error placing order: " + e.getMessage());
        }

        UpdateOrderController updateOrderController = new UpdateOrderController(registerService, placeOrderService,
                readFileController);
        updateOrderController.updateOrder();
    }

    public PlaceOrderService getPlaceOrderService() {
        return placeOrderService;
    }
}
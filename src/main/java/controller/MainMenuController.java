package controller;

import view.ViewMenu;
import java.util.Scanner;

public class MainMenuController extends BaseController {
    private ViewMenu viewMenu;
    private RegisterController registerController;
    private UpdateController updateController;
    private SearchController searchController;
    private DisplayMenusController displayMenusController;
    private PlaceOrderController placeOrderController;
    private UpdateOrderController updateOrderController;
    private SaveDataController saveDataController;
    private DisplayListsController displayListsController;
    private Scanner scanner;

    public MainMenuController() {
        this.viewMenu = new ViewMenu();
        this.registerController = new RegisterController();
        this.updateController = new UpdateController(registerController.getRegisterService());
        this.searchController = new SearchController(registerController.getRegisterService());
        this.displayMenusController = new DisplayMenusController();
        this.placeOrderController = new PlaceOrderController();
        this.updateOrderController = new UpdateOrderController();
        this.saveDataController = new SaveDataController();
        this.displayListsController = new DisplayListsController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() {
        while (true) {
            viewMenu.MainMenu();
            int choice = getValidChoice(1, 9);

            try {
                switch (choice) {
                    case 1:
                        registerController.registerCustomer();
                        break;
                    case 2:
                        updateController.updateCustomer();
                        break;
                    case 3:
                        searchController.searchCustomerByName();
                        break;
                    case 4:
                        displayMenusController.displayFeastMenus();
                        break;
                    case 5:
                        placeOrderController.placeOrder();
                        break;
                    case 6:
                        updateOrderController.updateOrder();
                        break;
                    case 7:
                        saveDataController.saveData();
                        break;
                    case 8:
                        displayListsController.displayLists();
                        break;
                    case 9:
                        if (BaseController.hasUnsavedChanges) {
                            System.out.println("\nWarning: You have unsaved changes!");
                            System.out.println("Do you want to save before exiting? (Y/N): ");
                            String response = scanner.nextLine().trim().toUpperCase();
                            if (response.equals("Y")) {
                                saveDataController.saveData();
                            }
                        }
                        System.out.println("Thank you for using Traditional Feast Order Management System!");
                        return;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
}

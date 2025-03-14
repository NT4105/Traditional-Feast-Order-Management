package controller;

import service.SaveDataService;
import service.RegisterService;
import service.PlaceOrderService;
import view.ViewMenu;

public class SaveDataController extends BaseController {
    private SaveDataService saveDataService;
    private RegisterService registerService;
    private PlaceOrderService placeOrderService;
    private ViewMenu viewMenu;

    public SaveDataController(RegisterService registerService) {
        this.saveDataService = new SaveDataService();
        this.registerService = registerService;
        this.placeOrderService = new PlaceOrderService(registerService);
        this.viewMenu = new ViewMenu();
    }

    public void saveData() {
        System.out.println("\n=== Save Data to File ===");

        while (true) {
            viewMenu.SaveDataMenu();
            int choice = getValidChoice(1, 4);

            try {
                switch (choice) {
                    case 1:
                        saveCustomerData();
                        break;
                    case 2:
                        saveOrderData();
                        break;
                    case 3:
                        saveAllData();
                        break;
                    case 4:
                        return; // Exit to the main menu
                }
                // After saving, ask if the user wants to continue
                if (confirmBackToMain()) {
                    return; // Exit if the user does not want to continue
                }
            } catch (Exception e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        }
    }

    private void saveCustomerData() {
        boolean success = saveDataService.saveCustomersToFile(
                registerService.getAllCustomers());
        if (success) {
            System.out.println("Customer data has been successfully saved to \"customers.dat\"");
            BaseController.hasUnsavedChanges = false;
        }
    }

    private void saveOrderData() {
        boolean success = saveDataService.saveOrdersToFile(
                placeOrderService.getAllOrders());
        if (success) {
            System.out.println("Order data has been successfully saved to \"feast_order_service.dat\"");
            BaseController.hasUnsavedChanges = false;
        }
    }

    private void saveAllData() {
        saveCustomerData();
        saveOrderData();
    }
}
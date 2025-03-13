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

    public SaveDataController() {
        this.saveDataService = new SaveDataService();
        this.registerService = new RegisterService();
        this.placeOrderService = new PlaceOrderService();
        this.viewMenu = new ViewMenu();
    }

    public void saveData() {
        System.out.println("\n=== Save Data to File ===");

        while (true) {
            viewMenu.SaveDataMenu();
            int choice = getValidChoice(1, 4);

            try {
                while (true) {
                    if (choice == 1) {
                        saveCustomerData();
                        if (confirmBackToMain())
                            return;
                    }
                    if (choice == 2) {
                        saveOrderData();
                        if (confirmBackToMain())
                            return;
                    }
                    if (choice == 3) {
                        saveAllData();
                        if (confirmBackToMain())
                            return;
                    }
                    if (choice == 4) {
                        if (BaseController.hasUnsavedChanges) {
                            System.out.println("Warning: You have unsaved changes!");
                            System.out.println("Are you sure you want to exit without saving?");

                            if (!confirmBackToMain()) {
                                continue;
                            }
                        }
                        return;
                    }
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
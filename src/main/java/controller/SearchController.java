package controller;

import service.SearchService;
import view.Display;
import view.ViewMenu;
import java.util.List;
import model.Customer;
import validation.CustomerValidation;

public class SearchController extends BaseController {
    private SearchService searchService;
    private Display display;
    private ViewMenu viewMenu;

    public SearchController() {
        this.searchService = new SearchService();
        this.display = new Display();
        this.viewMenu = new ViewMenu();
    }

    public void searchCustomerByName() {
        System.out.println("\n=== Search Customer by Name ===");

        while (true) {
            viewMenu.SearchMenu();
            int choice = getValidChoice(1, 2);

            try {
                switch (choice) {
                    case 1:
                        processSearch();
                        if (confirmBackToMain()) {
                            return;
                        }
                        break;
                    case 2:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error during search: " + e.getMessage());
            }
        }
    }

    private void processSearch() {
        String searchName;
        do {
            System.out.print("\nEnter customer name to search: ");
            searchName = scanner.nextLine().trim();
        } while (!CustomerValidation.isValidSearchName(searchName));

        List<Customer> matchingCustomers = searchService.searchCustomersByName(searchName);
        display.displaySearchResults(matchingCustomers, searchName);
    }
}
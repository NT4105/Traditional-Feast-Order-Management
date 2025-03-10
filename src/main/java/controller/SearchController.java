package controller;

import service.SearchService;
import view.Display;
import view.Menu;
import java.util.List;
import model.Customer;

public class SearchController extends BaseController {
    private SearchService searchService;
    private Display display;
    private Menu menu;

    public SearchController() {
        this.searchService = new SearchService();
        this.display = new Display();
        this.menu = new Menu();
    }

    public void searchCustomerByName() {
        System.out.println("\n=== Search Customer by Name ===");

        while (true) {
            menu.SearchMenu();
            int choice = getValidChoice(1, 2);

            try {
                switch (choice) {
                    case 1:
                        processSearch();
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
        System.out.print("\nEnter customer name to search: ");
        String searchName = scanner.nextLine().trim();

        if (searchName.isEmpty()) {
            System.out.println("Search name cannot be empty!");
            return;
        }

        List<Customer> matchingCustomers = searchService.searchCustomersByName(searchName);
        display.displaySearchResults(matchingCustomers, searchName);
    }
}
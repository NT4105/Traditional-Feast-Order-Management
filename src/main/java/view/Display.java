package view;

import model.Customer;
import java.util.List;
import model.Menu;
import model.Order;

public class Display {
    public void displaySearchResults(List<Customer> customers, String searchName) {
        if (customers.isEmpty()) {
            System.out.println("No one matches the search criteria!");
            return;
        }

        System.out.println("Matching Customers: " + searchName);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-10s | %-25s\n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("------------------------------------------------------------------");

        for (Customer customer : customers) {
            System.out.printf("%-8s | %-20s | %-10s | %-25s\n",
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getPhone(),
                    customer.getEmail());
        }
        System.out.println("------------------------------------------------------------------");
    }

    public void displayMenuList(List<Menu> menuList) {
        if (menuList == null || menuList.isEmpty()) {
            System.out.println("Cannot read data from \"feastMenu.csv\". Please check it.");
            return;
        }

        System.out.println("----------------------------------------");
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("----------------------------------------");

        for (Menu menu : menuList) {
            System.out.println("Code        :" + menu.getMenuId());
            System.out.println("Name        :" + menu.getMenuName());
            System.out.printf("Price       : %,d Vnd%n", menu.getPrice());
            System.out.println("Ingredients:");

            // Split ingredients by semicolon and display with bullet points
            String[] ingredientsList = menu.getIngredients().split(";");
            for (String ingredient : ingredientsList) {
                System.out.println("+ " + ingredient.trim());
            }
            System.out.println("----------------------------------------");
        }
    }

    public void displayOrderDetails(Order order, Customer customer, Menu menu) {
        System.out.println("\n----------------------------------------------------------------");
        System.out.println("Customer order information [Order ID: " + order.getOrderId() + "]");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("Code          : %s\n", customer.getCustomerId());
        System.out.printf("Customer name : %s\n", customer.getCustomerName());
        System.out.printf("Phone number  : %s\n", customer.getPhone());
        System.out.printf("Email         : %s\n", customer.getEmail());
        System.out.println("----------------------------------------------------------------");
        System.out.printf("Code of Set Menu: %s\n", menu.getMenuId());
        System.out.printf("Set menu name   : %s\n", menu.getMenuName());
        System.out.printf("Event date      : %s\n", order.getEventDate());
        System.out.printf("Number of tables: %d\n", order.getNumberOfTables());
        System.out.printf("Price           : %,d Vnd\n", menu.getPrice());
        System.out.println("Ingredients:");

        String[] ingredients = menu.getIngredients().split(";");
        for (String ingredient : ingredients) {
            System.out.println("+ " + ingredient.trim());
        }

        System.out.println("----------------------------------------------------------------");
        System.out.printf("Total cost      : %,d Vnd\n", (int) order.getTotalCost());
        System.out.println("----------------------------------------------------------------");
    }
}
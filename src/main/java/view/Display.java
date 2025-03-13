package view;

import model.Customer;
import java.util.List;
import model.Menu;
import model.Order;
import service.PlaceOrderService;
import java.util.Arrays;

public class Display {
    private PlaceOrderService placeOrderService;

    public Display() {
        this.placeOrderService = new PlaceOrderService();
    }

    public void displaySearchResults(List<Customer> customers, String searchName) {
        if (customers.isEmpty()) {
            System.out.println("No one matches the search criteria!");
            return;
        }

        System.out.println("Matching Customers: " + searchName);
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-10s | %-25s\n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("---------------------------------------------------------------------");

        for (Customer customer : customers) {
            // Split the name into parts and reformat
            String[] nameParts = customer.getCustomerName().split("\\s+");
            String lastName = nameParts[nameParts.length - 1];
            String restOfName = String.join(" ",
                    Arrays.copyOfRange(nameParts, 0, nameParts.length - 1));
            String formattedName = lastName + ", " + restOfName;

            System.out.printf("%-8s | %-20s | %-10s | %-25s\n",
                    customer.getCustomerId(),
                    formattedName,
                    customer.getPhone(),
                    customer.getEmail());
        }
        System.out.println("---------------------------------------------------------------------");
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
            System.out.printf("Code        :%s%n", menu.getMenuId());
            System.out.printf("Name        :%s%n", menu.getMenuName());
            System.out.printf("Price       : %,d Vnd%n", menu.getPrice());
            System.out.println("Ingredients:");

            // Split ingredients by # to separate major sections
            String[] sections = menu.getIngredients().split("#");
            for (String section : sections) {
                // Remove any leading/trailing whitespace
                section = section.trim();
                if (!section.isEmpty()) {
                    // Print the section as is, maintaining the + prefix
                    System.out.println(section);
                }
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

    public void displayCustomerList(List<Customer> customers) {
        System.out.println("Customers information:");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-10s | %-25s\n", "Code", "Customer Name", "Phone", "Email");
        System.out.println("----------------------------------------------------------------");

        for (Customer customer : customers) {
            System.out.printf("%-8s | %-20s | %-10s | %-25s\n",
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getPhone(),
                    customer.getEmail());
        }
        System.out.println("----------------------------------------------------------------");
    }

    public void displayOrderList(List<Order> orders) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-6s | %-10s | %-10s | %-7s | %-9s | %-6s | %-10s\n",
                "ID", "Event date", "Customer ID", "Set Menu", "Price", "Tables", "Cost");
        System.out.println("-------------------------------------------------------------------------");

        for (Order order : orders) {
            Menu menu = placeOrderService.findMenuById(order.getMenuId());
            System.out.printf("%-6s | %-10s | %-10s | %-7s | %,9d | %6d | %,10.0f\n",
                    order.getOrderId(),
                    order.getEventDate(),
                    order.getCustomerId(),
                    order.getMenuId(),
                    menu.getPrice(),
                    order.getNumberOfTables(),
                    order.getTotalCost());
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}
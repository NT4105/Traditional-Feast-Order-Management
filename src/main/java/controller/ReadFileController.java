package controller;

import model.Customer;
import model.Order;
import model.Menu;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFileController {
    private static final String MENU_FILE = "src/main/resources/feastMenu.csv";
    private static final String CUSTOMERS_FILE = "src/main/resources/customers.dat";
    private static final String ORDERS_FILE = "src/main/resources/feast_order_service.dat";

    @SuppressWarnings("unchecked")
    public List<Customer> readCustomersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CUSTOMERS_FILE))) {
            return (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Order> readOrdersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDERS_FILE))) {
            return (List<Order>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public List<Menu> readMenuFromFile() {
        List<Menu> menuList = new ArrayList<>();
        Path path = Paths.get(MENU_FILE);

        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            // Skip header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (parts.length >= 4) {
                    String menuId = parts[0];
                    String menuName = parts[1];
                    int price = Integer.parseInt(parts[2]);
                    String ingredients = parts[3].replace("\"", ""); // Remove quotes

                    Menu menu = new Menu(menuId, menuName, price, ingredients);
                    menuList.add(menu);
                }
            }

            // Sort menu list by price in ascending order
            menuList.sort(Comparator.comparingInt(Menu::getPrice));

            return menuList;
        } catch (IOException e) {
            return null;
        }
    }
}
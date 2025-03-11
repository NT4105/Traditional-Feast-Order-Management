package controller;

import model.Menu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFileController {
    private static final String FILE_PATH = "src/main/resources/feastMenu.csv";

    public List<Menu> readMenuFromFile() {
        List<Menu> menuList = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            // Skip header line if it exists
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String menuId = parts[0].trim();
                    String menuName = parts[1].trim();
                    int price = Integer.parseInt(parts[2].trim().replace(",", ""));
                    String ingredients = parts[3].trim();

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
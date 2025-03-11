package controller;

import model.Menu;
import view.Display;
import view.ViewMenu;
import service.DisplayMenusService;
import java.util.List;

public class DisplayMenusController extends BaseController {
    private DisplayMenusService displayMenusService;
    private Display display;
    private ViewMenu viewMenu;

    public DisplayMenusController() {
        this.displayMenusService = new DisplayMenusService();
        this.display = new Display();
        this.viewMenu = new ViewMenu();
    }

    public void displayFeastMenus() {
        while (true) {
            viewMenu.DisplayFeastMenus();
            int choice = getValidChoice(1, 2);

            try {
                switch (choice) {
                    case 1:
                        List<Menu> menuList = displayMenusService.getAllMenus();
                        display.displayMenuList(menuList);
                        if (confirmBackToMain()) {
                            return;
                        }
                        break;
                    case 2:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error displaying menus: " + e.getMessage());
            }
        }
    }
}

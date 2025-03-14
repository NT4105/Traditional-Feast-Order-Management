package controller;

import model.Menu;
import view.Display;
import view.ViewMenu;
import service.DisplayMenusService;
import java.util.List;
import service.PlaceOrderService;
import service.RegisterService;

public class DisplayMenusController extends BaseController {
    private DisplayMenusService displayMenusService;
    private Display display;
    private ViewMenu viewMenu;
    private RegisterService registerService;

    public DisplayMenusController(RegisterService registerService) {
        this.registerService = registerService;
        this.displayMenusService = new DisplayMenusService();
        this.display = new Display(new PlaceOrderService(registerService));
        this.viewMenu = new ViewMenu();
    }

    public DisplayMenusController() {
        this.displayMenusService = new DisplayMenusService();
        this.display = new Display(new PlaceOrderService(registerService));
        this.viewMenu = new ViewMenu();
    }

    public void displayFeastMenus() {
        while (true) {
            viewMenu.DisplayFeastMenus();
            int choice = getValidChoice(1, 2);

            try {
                while (true) {
                    if (choice == 1) {
                        List<Menu> menuList = displayMenusService.getAllMenus();
                        display.displayMenuList(menuList);
                        if (confirmBackToMain()) {
                            return;
                        }
                    }
                    if (choice == 2) {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error displaying menus: " + e.getMessage());
            }
        }
    }
}

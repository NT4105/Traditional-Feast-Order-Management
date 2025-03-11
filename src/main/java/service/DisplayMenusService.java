package service;

import model.Menu;
import java.util.List;
import controller.ReadFileController;

public class DisplayMenusService {
    private final ReadFileController readFileController;

    public DisplayMenusService() {
        this.readFileController = new ReadFileController();
    }

    public List<Menu> getAllMenus() {
        return readFileController.readMenuFromFile();
    }
}
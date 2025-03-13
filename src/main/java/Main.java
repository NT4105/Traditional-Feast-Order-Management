import controller.MainMenuController;

public class Main {
    public static void main(String[] args) {
        try {
            MainMenuController mainMenu = new MainMenuController();
            mainMenu.displayMainMenu();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
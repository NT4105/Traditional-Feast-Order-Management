package view;

public class ViewMenu {
    public void MainMenu() {
        System.out.println("*********************************************************");
        System.out.println("*                                                       *");
        System.out.println("*        Traditional Feast Order Management             *");
        System.out.println("*                    Main Menu                          *");
        System.out.println("*                                                       *");
        System.out.println("*********************************************************");
        System.out.println("* 1. Register customer                                  *");
        System.out.println("* 2. Update customer information                        *");
        System.out.println("* 3. Search for customer information by name            *");
        System.out.println("* 4. Display feast menus                                *");
        System.out.println("* 5. Place a feast order                                *");
        System.out.println("* 6. Update order information                           *");
        System.out.println("* 7. Save Data to File                                  *");
        System.out.println("* 8. Display Customers or Order lists                   *");
        System.out.println("* 9. Exit Program                                       *");
        System.out.println("*********************************************************");
        System.out.println("* Please enter your choice (1-9):                       *");
        System.out.println("*********************************************************");
    }

    public void RegisterMenu() {
        System.out.println("*********************************************************");
        System.out.println("* [1] Register customer                                  *");
        System.out.println("* [2] Back to Main Menu                                  *");
        System.out.println("*********************************************************");
    }

    public void UpdateMenu() {
        System.out.println("*********************************************************");
        System.out.println("* [1] Update customer information                        *");
        System.out.println("* [2] Back to Main Menu                                  *");
        System.out.println("*********************************************************");
    }

    public void SearchMenu() {
        System.out.println("*********************************************************");
        System.out.println("* [1] Search for customer information by name            *");
        System.out.println("* [2] Back to Main Menu                                  *");
        System.out.println("*********************************************************");
    }

    public void DisplayFeastMenus() {
        System.out.println("*********************************************************");
        System.out.println("* 1. Display feast menus                                *");
        System.out.println("* 2. Back to Main Menu                                  *");
        System.out.println("*********************************************************");
    }

    public void PlaceOrderMenu() {
        System.out.println("*********************************************************");
        System.out.println("* [1] Place a feast order                                *");
        System.out.println("* [2] Back to Main Menu                                  *");
        System.out.println("*********************************************************");
    }

    public void UpdateOrderMenu() {
        System.out.println("*********************************************************");
        System.out.println("* [1] Update order information                           *");
        System.out.println("* [2] Back to Main Menu                                  *");
        System.out.println("*********************************************************");
    }

    public void SaveDataMenu() {
        System.out.println("*********************************************************");
        System.out.println("* Save Data to File                                     *");
        System.out.println("* [1] Save Customer Data                                *");
        System.out.println("* [2] Save Order Data                                   *");
        System.out.println("* [3] Save Both                                         *");
        System.out.println("* [4] Back to Main Menu                                 *");
        System.out.println("*********************************************************");
    }

    public void DisplayListsMenu() {
        System.out.println("*********************************************************");
        System.out.println("* [1] Display Customers or Order lists                  *");
        System.out.println("* [2] Back to Main Menu                                 *");
        System.out.println("*********************************************************");
    }
}

package controller;

import java.util.Scanner;
import java.util.List;
import model.Customer;

public abstract class BaseController {
    protected Scanner scanner;
    protected List<Customer> customers;
    protected static boolean hasUnsavedChanges = false; //

    public BaseController() {
        this.scanner = new Scanner(System.in);
    }

    protected int getValidChoice(int min, int max) {
        int choice;
        do {
            System.out.print("Please enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number. Please try again.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < min || choice > max) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        } while (choice < min || choice > max);
        return choice;
    }

    protected boolean confirmBackToMain() {
        while (true) {
            System.out.print("Do you want to continue? (Y/N): ");
            String choice = scanner.nextLine().trim().toUpperCase();
            if (choice.equals("Y")) {
                return false;
            } else if (choice.equals("N")) {
                return true;
            }
            System.out.println("Please enter Y or N!");
        }
    }
}

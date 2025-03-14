package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.Order;

public class OrderValidation {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean isValidEventDate(String date) {
        try {
            LocalDate eventDate = LocalDate.parse(date, DATE_FORMATTER);
            LocalDate today = LocalDate.now();

            if (eventDate.isBefore(today)) {
                System.out.println("Event date must be in the future!");
                return false;
            }
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use dd/MM/yyyy");
            return false;
        }
    }

    public static boolean isValidNumberOfTables(int tables) {
        if (tables <= 0) {
            System.out.println("Number of tables must be greater than 0!");
            return false;
        }

        if (tables == 0) {
            System.out.println("Number of tables cannot be 0!");
            return false;
        }

        return true;
    }

    public static boolean isOrderIdRegistered(String orderId, List<Order> orders) {
        return orders.stream().anyMatch(o -> o.getOrderId().equals(orderId));
    }
}
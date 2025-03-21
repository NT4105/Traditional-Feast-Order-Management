package validation;

import java.util.regex.Pattern;
import model.Customer;
import java.util.List;

public class CustomerValidation {
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("^(C|G|K)\\d{4}$");
    private static final Pattern PHONE_PATTERN = Pattern
            .compile(
                    "^(086|096|097|098|032|033|034|035|036|037|038|039|088|091|094|083|084|085|081|082|089|090|093|070|079|077|076|078|092|056|058|099|059)\\d{7}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static boolean isValidCustomerId(String customerId) {
        if (customerId.isEmpty()) {
            System.out.println("Customer Id cannot be empty!");
            return false;
        }
        if (!CUSTOMER_ID_PATTERN.matcher(customerId).matches()) {
            System.out.println("Invalid ID format. Must start with C, G, K followed by 4 digits.");
            return false;
        }
        return true;
    }

    public static boolean isValidCustomerName(String customerName) {
        if (customerName.isEmpty()) {
            System.out.println("Customer name cannot be empty!");
            return false;
        }
        if (customerName.length() < 2 || customerName.length() > 25) {
            System.out.println("Customer name must be between 2 and 25 characters!");
            return false;
        }
        return true;
    }

    public static boolean isValidPhone(String phone) {
        if (phone.isEmpty()) {
            System.out.println("Phone number cannot be empty!");
            return false;
        }
        if (phone.length() != 10) {
            System.out.println("Phone number must be 10 digits!");
            return false;
        }
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            System.out.println("Invalid phone number format.");
            return false;
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        if (email.isEmpty()) {
            System.out.println("Email cannot be empty!");
            return false;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            System.out.println("Invalid email format!");
            return false;
        }
        if (email.length() > 30) {
            System.out.println("Email cannot be longer than 30 characters!");
            return false;
        }
        return true;
    }

    public static boolean isValidSearchName(String searchName) {
        if (searchName.isEmpty()) {
            System.out.println("Search name cannot be empty!");
            return false;
        }

        if (searchName.length() < 2) {
            System.out.println("Search name must be at least 2 characters!");
            return false;
        }

        if (!searchName.matches("^[a-zA-Z]+$")) {
            System.out.println("Search name can only contain letters!");
            return false;
        }

        return true;
    }

    public static boolean isCustomerIdRegistered(String customerId, List<Customer> customers) {
        if (customers == null || customers.isEmpty()) {
            return false;
        }

        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return true;
            }
        }
        return false;
    }
}
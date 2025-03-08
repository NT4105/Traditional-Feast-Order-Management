package validation;

import java.util.regex.Pattern;
import model.Customer;

public class CustomerValidation {
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("^(C|G|K)\\d{4}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^(032|033|034|034|035|036|037|038|039|081|082|083|084|085|070|076|077|078|079|56|058|059)\\d{7}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static boolean isValidCustomerId(String customerId) {
        if (!customerId.isEmpty()) {
            System.out.println("Customer Id cannot be empty!");
            return false;
        }

        if (!CUSTOMER_ID_PATTERN.matcher(customerId).matches()) {
            System.out.println("Invalid ID fomat. Must start with C, G, K followed by 4 digits.");
            return false;

        }
        return true;
    }

    public static boolean isValidCustomerName(String customerName) {

        if (!customerName.isEmpty()) {
            System.out.println("Customer name cannot empty!");
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
            System.out.println("Phone number must be 10!");
            return false;
        }

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            System.out.println(
                    "Invalid phone number format. Must start with 09, 08, 07, 03, or 05 followed by 8 digits.");
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
}
package model;

public class Order {
    private String orderId;
    private String customerId;
    private String menuId;
    private String eventDate;
    private int numberOfTables;
    private double totalCost;

    public Order() {
    }

    public Order(String orderId, String customerId, String menuId, String eventDate, int numberOfTables) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.menuId = menuId;
        this.eventDate = eventDate;
        this.numberOfTables = numberOfTables;
    }

    // Getters and setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
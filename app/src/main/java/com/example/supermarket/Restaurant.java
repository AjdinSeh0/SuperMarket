package com.example.supermarket;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String address;
    private static int lastInsertedRestaurantId = -1;

    public Restaurant(){
        restaurantId = -1;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public static int getLastInsertedRestaurantId() {
        return lastInsertedRestaurantId;
    }

    public static void setLastInsertedRestaurantId(int lastInsertedRestaurantId) {
        Restaurant.lastInsertedRestaurantId = lastInsertedRestaurantId;
    }
}

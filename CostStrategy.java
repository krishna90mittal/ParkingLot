package com.parkingsystem;

import java.util.HashMap;
import java.util.Map;

public class CostStrategy {
    private Map<String, Integer> costPerHour;

    public CostStrategy() {
        costPerHour = new HashMap<>();
        costPerHour.put("Bike", 10);
        costPerHour.put("Car", 20);
        costPerHour.put("Jeep", 20);
        costPerHour.put("Bus", 30);
        costPerHour.put("Truck", 30);
    }

    public int getCost(String vehicleType, int hours) {
        System.out.println("Vehicle Type="+vehicleType);
        return costPerHour.get(vehicleType) * hours;
    }
}

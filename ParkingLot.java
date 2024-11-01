package com.parkingsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private List<Floor> floors;
    private CostStrategy costStrategy;
    private Map<String, Vehicle> parkedVehicles; // Map to track parked vehicles by tokenId

    public ParkingLot(int numberOfFloors, int spacesPerFloor) {
        floors = new ArrayList<>();
        parkedVehicles = new HashMap<>(); // Initialize the map here
        for (int i = 0; i < numberOfFloors; i++) {
            floors.add(new Floor(i + 1, spacesPerFloor));
        }
        this.costStrategy = new CostStrategy();
    }

    public boolean addVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            if (floor.parkVehicle(vehicle)) {
                parkedVehicles.put(vehicle.getTokenId(), vehicle); // Store vehicle by token
                return true;
            }
        }
        return false;
    }

    public boolean isVehicleAvailable(String tokenId) {
        Vehicle vehicle = parkedVehicles.get(tokenId);
        return vehicle != null;
    }

    public Vehicle removeVehicle(Vehicle vehicle) {

        parkedVehicles.remove(vehicle.getTokenId());
        for (Floor floor : floors) {
            Vehicle removed = floor.removeVehicle(vehicle.getRegistrationNumber());
            if (removed != null) {
//                    int hours = (int) (java.time.Duration.between(vehicle.getTimestamp(), exitTime).toHours());
                return removed;
            }
        }
        return null;
    }

    public int calculateParkingFee(String tokenId, LocalDateTime exitTime) {
        Vehicle vehicle = parkedVehicles.get(tokenId);
        if (vehicle != null) {
            int hours = (int) (java.time.Duration.between(vehicle.getTimestamp(), exitTime).toHours());
            return hours == 0 ? costStrategy.getCost(vehicle.getVehicleType(), 1) : costStrategy.getCost(vehicle.getVehicleType(), hours);
        }
        return -1;
    }

    public void displayStatus() {
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + ": " + floor.getStatus());
        }
    }
}

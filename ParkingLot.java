package com.parkingsystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private List<Floor> floors;
    private CostStrategy costStrategy;
    private Map<String, Vehicle> parkedVehicles; // Track parked vehicles by tokenId

    public ParkingLot(int numberOfFloors, List<VehicleTypePerFloor> vehicleTypeSpacePerFloor) {
        floors = new ArrayList<>();
        parkedVehicles = new HashMap<>(); // Initialize the map

        // Create floors and initialize vehicle spaces
        for (int i = 0; i < numberOfFloors; i++) {
            int finalI = i;
            Map<String, Integer> spaceMap = vehicleTypeSpacePerFloor.stream()
                    .filter(v -> v.getFloorNumber() == finalI)
                    .findFirst()
                    .map(VehicleTypePerFloor::getVehicleSpace)
                    .orElse(new HashMap<>());
            floors.add(new Floor(i + 1, spaceMap));
        }
        this.costStrategy = new CostStrategy();
    }

    public String addVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            if (floor.parkVehicle(vehicle)) {
                parkedVehicles.put(vehicle.getTokenId(), vehicle); // Store vehicle by token
                return "Vehicle parked successfully with Token ID: " + vehicle.getTokenId();
            }
        }
        return "No space available for vehicle type: " + vehicle.getVehicleType();
    }

    public Vehicle removeVehicle(Vehicle vehicle) {
        parkedVehicles.remove(vehicle.getTokenId());
        for (Floor floor : floors) {
            Vehicle removed = floor.removeVehicle(vehicle.getRegistrationNumber());
            if (removed != null) {
                return removed; // Return removed vehicle
            }
        }
        return null; // Vehicle not found
    }

    public int calculateParkingFee(String tokenId, LocalDateTime exitTime) {
        Vehicle vehicle = parkedVehicles.get(tokenId);
        if (vehicle != null) {
            int hours = (int) (java.time.Duration.between(vehicle.getTimestamp(), exitTime).toHours());
            return costStrategy.getCost(vehicle.getVehicleType(), hours);
        }
        return -1; // Indicate vehicle not found
    }

    public Vehicle isVehicleAvailable(String tokenId) {
        return parkedVehicles.get(tokenId);
    }

    public void displayStatus() {
        System.out.println("Current Status of Parking Lot:");
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + ":");
            Map<String, Integer> status = floor.getStatus();
            for (Map.Entry<String, Integer> entry : status.entrySet()) {
                String vehicleType = entry.getKey();
                int occupied = entry.getValue();
                int totalSpaces = floor.getTotalSpacesForType(vehicleType);
                int available = totalSpaces - occupied;
                System.out.println("  Vehicle Type: " + vehicleType + " | Occupied: " + occupied + " | Available: " + available);
            }
        }
    }
}

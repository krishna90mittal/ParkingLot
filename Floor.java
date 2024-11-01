package com.parkingsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Floor {
    private int floorNumber;
    private Map<String, List<VehicleSpace>> vehicleSpaces;

    public Floor(int floorNumber, Map<String, Integer> vehicleSpaceMap) {
        this.floorNumber = floorNumber;
        vehicleSpaces = new HashMap<>();

        // Initialize vehicle spaces based on vehicle type
        for (String vehicleType : vehicleSpaceMap.keySet()) {
            List<VehicleSpace> spaces = new ArrayList<>();
            int capacity = vehicleSpaceMap.get(vehicleType);
            for (int i = 0; i < capacity; i++) {
                spaces.add(new VehicleSpace(i + 1)); // Space numbers are 1-based
            }
            vehicleSpaces.put(vehicleType, spaces);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        List<VehicleSpace> spaces = vehicleSpaces.get(vehicle.getVehicleType());
        if (spaces != null) {
            for (VehicleSpace space : spaces) {
                if (space.isAvailable()) {
                    space.parkVehicle(vehicle);
                    return true;
                }
            }
        }
        return false; // No space available
    }

    public Vehicle removeVehicle(String registrationNumber) {
        for (List<VehicleSpace> spaces : vehicleSpaces.values()) {
            for (VehicleSpace space : spaces) {
                if (!space.isAvailable() && space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space.removeVehicle();
                }
            }
        }
        return null; // Vehicle not found
    }

    public Map<String, Integer> getStatus() {
        Map<String, Integer> status = new HashMap<>();
        for (String type : vehicleSpaces.keySet()) {
            int occupied = 0;
            for (VehicleSpace space : vehicleSpaces.get(type)) {
                if (!space.isAvailable()) {
                    occupied++;
                }
            }
            status.put(type, occupied);
        }
        return status;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
    public int getTotalSpacesForType(String vehicleType) {
        List<VehicleSpace> spaces = vehicleSpaces.get(vehicleType);
        return (spaces != null) ? spaces.size() : 0;
    }
}

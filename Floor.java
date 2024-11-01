package com.parkingsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Floor {
    private int floorNumber;
    private Map<String, List<VehicleSpace>> vehicleSpaces;

    public Floor(int floorNumber, int capacity) {
        this.floorNumber = floorNumber;
        vehicleSpaces = new HashMap<>();
        vehicleSpaces.put("Bike", new ArrayList<>());
        vehicleSpaces.put("Car", new ArrayList<>());
        vehicleSpaces.put("Jeep", new ArrayList<>());
        vehicleSpaces.put("Bus", new ArrayList<>());
        vehicleSpaces.put("Truck", new ArrayList<>());

        for (int i = 0; i < capacity; i++) {
            vehicleSpaces.get("Bike").add(new VehicleSpace(i));
            vehicleSpaces.get("Car").add(new VehicleSpace(i));
            vehicleSpaces.get("Jeep").add(new VehicleSpace(i));
            vehicleSpaces.get("Bus").add(new VehicleSpace(i));
            vehicleSpaces.get("Truck").add(new VehicleSpace(i));

        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        List<VehicleSpace> spaces = vehicleSpaces.get(vehicle.getVehicleType());
        for (VehicleSpace space : spaces) {
            if (space.isAvailable()) {
                space.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public Vehicle removeVehicle(String registrationNumber) {
        for (List<VehicleSpace> spaces : vehicleSpaces.values()) {
            for (VehicleSpace space : spaces) {
                if (!space.isAvailable() && space.getVehicle().getRegistrationNumber().equals(registrationNumber)) {
                    return space.removeVehicle();
                }
            }
        }
        return null;
    }

    public int checkAvailability(String vehicleType) {
        List<VehicleSpace> spaces = vehicleSpaces.get(vehicleType);
        int count = 0;
        for (VehicleSpace space : spaces) {
            if (space.isAvailable()) {
                count++;
            }
        }
        return count;
    }

    public int getFloorNumber() {
        return floorNumber;
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
}

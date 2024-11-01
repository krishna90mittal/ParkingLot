package com.parkingsystem;

import java.util.Map;

public class VehicleTypePerFloor {
    private int floorNumber;
    private Map<String, Integer> vehicleSpace;

    public VehicleTypePerFloor(int floorNumber, Map<String, Integer> vehicleSpace) {
        this.floorNumber = floorNumber;
        this.vehicleSpace = vehicleSpace;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Map<String, Integer> getVehicleSpace() {
        return vehicleSpace;
    }

    public void setVehicleSpace(Map<String, Integer> vehicleSpace) {
        this.vehicleSpace = vehicleSpace;
    }
}

package com.parkingsystem;

public class VehicleSpace {
    private int spaceNumber;
    private boolean available;
    private Vehicle vehicle;

    public VehicleSpace(int spaceNumber) {
        this.spaceNumber = spaceNumber;
        this.available = true;
        this.vehicle = null;
    }

    public boolean isAvailable() {
        return available;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.available = false;
    }

    public Vehicle removeVehicle() {
        Vehicle temp = this.vehicle;
        this.vehicle = null;
        this.available = true;
        return temp;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

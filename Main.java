package com.parkingsystem;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(2, 2); // 2 Floors, 2 spaces each

        // Add Vehicles
        Vehicle vehicle1 = new Vehicle("ABC123", "Red", "Car");
        parkingLot.addVehicle(vehicle1);

        Vehicle vehicle2 = new Vehicle("XYZ456", "Blue", "Car");
        parkingLot.addVehicle(vehicle2);

        // Try to add another vehicle
        boolean added = parkingLot.addVehicle(new Vehicle("LMN789", "Green", "Car"));
        if (!added) {
            System.out.println("Parking lot is full for Cars.");
        }

        // Display Status
        parkingLot.displayStatus();

        // Remove Vehicle
        String tokenToRemove = vehicle1.getTokenId();
        LocalDateTime exitTime = LocalDateTime.now();
        boolean isVehicleAvailable = parkingLot.isVehicleAvailable(tokenToRemove);
        if (isVehicleAvailable) {
            int parkingFee = parkingLot.calculateParkingFee(tokenToRemove, exitTime);
            Vehicle removedVehicle = parkingLot.removeVehicle(vehicle1);
            System.out.println("Removed vehicle: " + removedVehicle.getRegistrationNumber());
            System.out.println("Total Parking Fee: ₹" + parkingFee);
        } else {
            System.out.println("Vehicle not found.");
        }

        // Display Status after removal
        parkingLot.displayStatus();

        scanner.close();
    }
}

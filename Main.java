package com.parkingsystem;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> vehicleTypes = List.of("Bike", "Car", "Jeep", "Bus", "Truck");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number of Floor=");
        int floor = scanner.nextInt();
        System.out.println("Enter Vehicle Type Space Per Floor");
        List<VehicleTypePerFloor> vehicleTypeSpacePerFloor = new ArrayList<>();
        for (int i = 0; i < floor; i++) {
            for (String vehicleType : vehicleTypes) {
                System.out.println("Enter Space in " + i + " floor for " + vehicleType + "=");
                scanner.nextLine();
                int vehicleSpace = scanner.nextInt();
                Map<String, Integer> map = new HashMap<>();
                map.put(vehicleType, vehicleSpace);
                vehicleTypeSpacePerFloor.add(new VehicleTypePerFloor(i, map));
            }
        }
        ParkingLot parkingLot = new ParkingLot(floor, vehicleTypeSpacePerFloor);
        boolean flag = true;
        do {
            System.out.println("1. Add Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. Check Vehicle available or not");
            System.out.println("4. Display The Current Status");
            System.out.println("5. Exit");
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            if (choice == 5) {
                flag = false;
                scanner.close();
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter The Vehicle Registration Number");
                    scanner.nextLine();
                    String registrationNumber = scanner.next();
                    System.out.println("Enter Colour of Vehicle");
                    scanner.nextLine();
                    String colour = scanner.next();
                    System.out.println("Enter Vehicle Type");
                    scanner.nextLine();
                    String vehicleType = scanner.next();
                    String result = parkingLot.addVehicle(new Vehicle(registrationNumber, colour, vehicleType));
                    System.out.println(result);
                }
                case 2 -> {
                    System.out.println("Enter Token Id");
                    scanner.nextLine();
                    String tokenId = scanner.next();
                    LocalDateTime exitTime = LocalDateTime.now();
                    Vehicle vehicle = parkingLot.isVehicleAvailable(tokenId);
                    if (vehicle != null) {
                        int parkingFee = parkingLot.calculateParkingFee(tokenId, exitTime);
                        Vehicle removedVehicle = parkingLot.removeVehicle(vehicle);
                        System.out.println("Removed vehicle: " + removedVehicle.getRegistrationNumber());
                        System.out.println("Total Parking Fee: ₹" + parkingFee);
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                }
                case 3 -> {
                    System.out.println("Enter Token Id");
                    scanner.nextLine();
                    String tokenId = scanner.next();
                    Vehicle vehicle = parkingLot.isVehicleAvailable(tokenId);
                    if (vehicle != null) {
                        System.out.println("Vehicle is Present in the Parking Lot");
                    } else {
                        System.out.println("Vehicle is Not Present in the Parking Lot");
                    }
                }
                case 4 -> parkingLot.displayStatus();
                default -> System.out.println("Please choose Valid Option");
            }

        } while (flag);
        scanner.close();
        System.exit(0);
    }
}

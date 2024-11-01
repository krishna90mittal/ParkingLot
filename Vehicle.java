package com.parkingsystem;

import java.time.LocalDateTime;

public class Vehicle {
    private String registrationNumber;
    private String color;
    private String vehicleType;
    private String tokenId;
    private LocalDateTime timestamp;

    public Vehicle(String registrationNumber, String color, String vehicleType) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.vehicleType = vehicleType;
        this.tokenId = generateToken();
        this.timestamp = LocalDateTime.now();
    }

    private String generateToken() {
        return registrationNumber + "-" + System.currentTimeMillis(); // Simple token generation
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getTokenId() {
        return tokenId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

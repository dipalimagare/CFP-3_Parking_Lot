package com.parkingLot;

public class AirportSecurity implements IParkingObserver {
    private String message;

    @Override
    public void updateMessage(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
    }
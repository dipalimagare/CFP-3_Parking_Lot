package com.parkingLot;

public class Owner implements IParkingMonitor{
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
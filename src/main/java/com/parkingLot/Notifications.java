package com.parkingLot;

public enum Notifications {

    PARKING_LOT_IS_FULL("Parking Lot is Full"),
    HAVE_SPACE_TO_PARK("Have Space To Park");

    public String message;

    Notifications(String message) {
        this.message = message;
    }
}
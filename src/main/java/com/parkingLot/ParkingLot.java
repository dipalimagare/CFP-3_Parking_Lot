package com.parkingLot;

public class ParkingLot {
    Object vehicle = null;

    public boolean parkVehicle(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }


}

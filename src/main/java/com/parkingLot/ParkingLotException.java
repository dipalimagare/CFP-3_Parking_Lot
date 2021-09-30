package com.parkingLot;

public class ParkingLotException extends Exception {

    public ExceptionType type;

    public ParkingLotException(ExceptionType type) {
        super(type.message);
        this.type = type;
    }

    public enum ExceptionType {
        NO_SUCH_VEHICLE("No Such Vehicle"), VEHICLE_MISMATCH("Vehicle Mismatch"),
        LOT_FULL("Parking Lot Is Full");

        String message;

        ExceptionType(String message){
            this.message = message;
        }
    }
}

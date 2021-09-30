package com.parkingLot;

public interface IParkingLot {
        void parkVehicle(Car car) throws ParkingLotException;

        void unParkVehicle(String key) throws ParkingLotException;

        boolean isParked(Car car);

        void notifyToObserver(String message);

        void addObserver(IParkingObserver monitor);

        String getVehicle(Car car);
}

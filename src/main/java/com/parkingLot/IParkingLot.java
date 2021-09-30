package com.parkingLot;

public interface IParkingLot {

        void parkVehicle(Car car) throws ParkingLotException;

        void unParkVehicle(Car car) throws ParkingLotException;

        boolean isParked(Car car);

//        boolean isUnParked(Car car);
//
//        void notifyToMonitor();
//
//        void addMonitor(IParkingObserver monitor);

        void notifyToObserver(String message);

        void addObserver(IParkingObserver monitor);
}

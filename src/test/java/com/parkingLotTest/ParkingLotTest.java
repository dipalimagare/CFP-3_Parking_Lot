package com.parkingLotTest;

import com.parkingLot.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    Owner owner = null;
    AirportSecurity security = null;

    @BeforeEach
    public void setUp() {
        parkingLot = new ParkingLot(2);
        owner = new Owner();
        security = new AirportSecurity();
    }

    //    UC1
    @Test
    public void givenVehicle_WhenPark_ShouldReturnTrue() throws ParkingLotException {
        Car car = new Car("1", "KA-48-S-8055");
        parkingLot.parkVehicle(car);
        boolean isParked = parkingLot.isParked(car);
        Assertions.assertTrue(isParked);
    }

    //    UC2
    @Test
    public void givenVehicleIfParked_WhenUnParked_ShouldReturnFalse() throws ParkingLotException {
        Car car = new Car("1", "KA-48-S-8055");
        parkingLot.parkVehicle(car);
        String key = parkingLot.getVehicle(car);
        parkingLot.unParkVehicle(key);
        boolean isParked = parkingLot.isParked(car);
        Assertions.assertFalse(isParked);
    }

    @Test
    public void givenVehicleToUnPark_WhenNull_ShouldThrowException() {
        try {
            Car car = new Car("1", "KA-48-S-8055");
            parkingLot.parkVehicle(car);
            parkingLot.unParkVehicle(null);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    //    UC3
    @Test
    public void givenVehicleToPark_WhenOwner_ShouldInformFull() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Car firstCar = new Car("1", "KA-48-S-8055");
        Car secondCar = new Car("2", "KA-01-S-1234");
        parkingLot.parkVehicle(firstCar);
        parkingLot.parkVehicle(secondCar);
        Assertions.assertEquals(Notifications.PARKING_LOT_IS_FULL.message, owner.getMessage());
    }

    //    UC4
    @Test
    public void givenVehicleToPark_WhenOwnerAndSecurity_ShouldInformLotFull() throws ParkingLotException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(security);
        Car firstCar = new Car("1", "KA-48-S-8055");
        Car secondCar = new Car("2", "KA-01-S-1234");
        parkingLot.parkVehicle(firstCar);
        parkingLot.parkVehicle(secondCar);
        Assertions.assertEquals(Notifications.PARKING_LOT_IS_FULL.message, owner.getMessage());
        Assertions.assertEquals(Notifications.PARKING_LOT_IS_FULL.message, security.getMessage());
    }

    @Test
    public void givenVehicleToPark_WhenMoreNumberOfVehicles_ShouldThrowException() {
        try {
            parkingLot.addObserver(owner);
            Car firstCar = new Car("1", "KA-48-S-8055");
            Car secondCar = new Car("2", "KA-01-S-1234");
            Car thirdCar = new Car("3", "KA-02-S-1234");
            parkingLot.parkVehicle(firstCar);
            parkingLot.parkVehicle(secondCar);
            parkingLot.parkVehicle(thirdCar);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.LOT_FULL, e.type);
        }
    }

    @Test
    public void givenVehicleToUnPark_WhenWrongVehicle_ShouldThrowException() {
        try {
            Car firstCar = new Car("1", "KA-48-S-8055");
            Car secondCar = new Car("2", "KA-01-S-8055");
            parkingLot.parkVehicle(firstCar);
            String key = parkingLot.getVehicle(secondCar);
            parkingLot.unParkVehicle(key);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    //    UC5
    @Test
    public void givenVehicleToPark_WhenHavingSpaceAfterUnPark_ShouldInformHaveSpaceToPark() throws ParkingLotException {
        parkingLot.addObserver(owner);
        parkingLot.addObserver(security);
        Car car = new Car("1", "KA-48-S-8055");
        Car car2 = new Car("2", "KA-01-S-1234");
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car2);
        Assertions.assertEquals(Notifications.PARKING_LOT_IS_FULL.message, owner.getMessage());
        Assertions.assertEquals(Notifications.PARKING_LOT_IS_FULL.message, security.getMessage());
        String key = parkingLot.getVehicle(car2);
        parkingLot.unParkVehicle(key);
        Assertions.assertEquals(Notifications.HAVE_SPACE_TO_PARK.message, owner.getMessage());
    }

    @Test
    public void givenVehicleToUnPark_WhenParkingLotIsEmpty_ShouldThrowException() {
        try {
            Car car = new Car("1", "KA-48-S-8055");
            String key = parkingLot.getVehicle(car);
            parkingLot.unParkVehicle(key);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    //    UC7
    @Test
    public void givenVehicleToUnPark_WhenFindVehicle_ShouldReturnKey() throws ParkingLotException {
        parkingLot.addObserver(owner);
        Car firstCar = new Car("1", "KA-48-S-8055");
        Car secondCar = new Car("2", "KA-01-S-1234");
        parkingLot.parkVehicle(firstCar);
        parkingLot.parkVehicle(secondCar);
        String key = parkingLot.getVehicle(secondCar);
        parkingLot.unParkVehicle(key);
        Assertions.assertFalse(parkingLot.isParked(secondCar));
    }
}

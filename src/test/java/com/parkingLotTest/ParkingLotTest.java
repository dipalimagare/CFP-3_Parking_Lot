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
        parkingLot = new ParkingLot();
        owner = new Owner();
        security = new AirportSecurity();
    }

    @Test
    public void givenVehicle_WhenPark_ShouldReturnTrue() throws ParkingLotException {
        Car car = new Car("1", "KA-48-S-8055");
        parkingLot.parkVehicle(car);
        boolean isParked = parkingLot.isParked(car);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenVehicleIfParked_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        Car car = new Car("1", "KA-48-S-8055");
        parkingLot.parkVehicle(car);
        parkingLot.unParkVehicle(car);
        boolean isUnParked = parkingLot.isUnParked(car);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenVehicleToUnPark_WhenNull_ShouldThrowException() {
        try {
            Car car = new Car("1", "KA-48-S-8055");
            parkingLot.parkVehicle(car);
            parkingLot.unParkVehicle(null);
        } catch (ParkingLotException e) {
            System.out.println(e.type);
            Assertions.assertEquals(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, e.type);
        }
    }

    @Test
    public void givenVehicleToPark_WhenOwner_ShouldInformInformLotFull() throws ParkingLotException {
        parkingLot.addMonitor(owner);
        Car car = new Car("1", "KA-48-S-8055");
        Car car2 = new Car("2", "KA-01-S-1234");
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car2);
        Assertions.assertEquals("Parking Lot Is Full", owner.getMessage());
    }

    @Test
    public void givenVehicleToPark_WhenOwnerAndSecurity_ShouldInformInformLotFull() throws ParkingLotException {
        parkingLot.addMonitor(owner);
        parkingLot.addMonitor(security);
        Car car = new Car("1", "KA-48-S-8055");
        Car car2 = new Car("2", "KA-01-S-1234");
        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(car2);
        Assertions.assertEquals("Parking Lot Is Full", owner.getMessage());
        Assertions.assertEquals("Parking Lot Is Full", security.getMessage());
    }

    @Test
    public void givenVehicleToPark_WhenMoreNumberOfVehicles_ShouldThrowException() {
        try {
            parkingLot.addMonitor(owner);
            Car car = new Car("1", "KA-48-S-8055");
            Car car2 = new Car("2", "KA-01-S-1234");
            Car car3 = new Car("3", "KA-02-S-1234");
            parkingLot.parkVehicle(car);
            parkingLot.parkVehicle(car2);
            parkingLot.parkVehicle(car3);
        } catch (ParkingLotException e) {
            Assertions.assertEquals(ParkingLotException.ExceptionType.LOT_FULL, e.type);
        }
    }

    @Test
    public void givenVehicleToUnPark_WhenWrongVehicle_ShouldThrowException() {
        try {
            Car car = new Car("1", "KA-48-S-8055");
            Car car2 = new Car("2", "KA-01-S-8055");
            parkingLot.parkVehicle(car);
            parkingLot.unParkVehicle(car2);
        } catch (ParkingLotException e) {
            System.out.println(e.type);
            Assertions.assertEquals(ParkingLotException.ExceptionType.VEHICLE_MISMATCH, e.type);
        }
    }
}

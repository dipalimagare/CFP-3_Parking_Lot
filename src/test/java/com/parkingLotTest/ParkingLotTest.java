package com.parkingLotTest;

import com.parkingLot.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    ParkingLot parkingLot = null;
    Object vehicle = null;
    Object vehicle2 = null;

    @BeforeEach
    public void setUp() {
        parkingLot = new ParkingLot();
        vehicle = new Object();
        vehicle2 = new Object();
    }

    @Test
    public void givenVehicle_WhenPark_ShouldReturnTrue() {
        boolean parkingStatus = parkingLot.parkVehicle(vehicle);
        Assertions.assertTrue(parkingStatus);
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingLot.parkVehicle(vehicle);
        boolean parkingStatus = parkingLot.parkVehicle(vehicle);
        Assertions.assertFalse(parkingStatus);
    }

    @Test
    public void givenVehicleIfParked_WhenUnParked_ShouldReturnTrue() {
        parkingLot.parkVehicle(vehicle);
        boolean parkingStatus = parkingLot.unParkVehicle(vehicle);
        Assertions.assertTrue(parkingStatus);
    }

    @Test
    public void givenVehicleIfParked_AndGivenDifferentVehicleToUnPark_ShouldReturnFalse() {
        parkingLot.parkVehicle(vehicle);
        boolean parkingStatus = parkingLot.unParkVehicle(vehicle2);
        Assertions.assertFalse(parkingStatus);
    }
}

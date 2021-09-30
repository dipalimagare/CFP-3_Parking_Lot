package com.parkingLotTest;

import com.parkingLot.*;
import org.junit.jupiter.api.*;

public class ParkingLotTest {
    @Test
    public void givenVehicle_WhenPark_ShouldReturnTrue() {
        ParkingLot parkingLot = new ParkingLot();
        boolean parkingStatus = parkingLot.parkVehicle(new Object());
        Assertions.assertTrue(parkingStatus);
    }
}

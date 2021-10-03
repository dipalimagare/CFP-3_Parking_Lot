package com.parkingLot;

import java.util.*;

public class Attendant {
    Owner owner = new Owner();
    public String parkVehicle(Map<String, Car> parkingMap) {
        return owner.generateKeyForLot(parkingMap);
    }
}

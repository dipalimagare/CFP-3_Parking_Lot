package com.parkingLot;

import java.util.*;

public class Owner implements IParkingObserver {
    String key;
    int lotNumber = 1;
    private String message;

    @Override
    public void updateMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String generateKeyForLot(Map<String, Car> parkingMap) {
        if (parkingMap.size() == 0)
            this.key = String.valueOf(lotNumber);
        for (String key : parkingMap.keySet()) {
            this.key = key;
            if (!parkingMap.get(key).equals(key))
                this.key = String.valueOf(lotNumber);
        }
        lotNumber++;
        return key;
    }
}
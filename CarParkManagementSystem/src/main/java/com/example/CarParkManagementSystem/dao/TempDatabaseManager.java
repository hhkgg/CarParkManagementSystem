package com.example.CarParkManagementSystem.dao;

import com.example.CarParkManagementSystem.entity.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TempDatabaseManager implements IDatabaseManager {
    private final ArrayList<ParkingLot> lots = new ArrayList<>();

    public TempDatabaseManager()
    {
        User user = new User(
                "Smatt",
                "Mithieson",
                "", "",
                "",
                "","",
                0);
        ParkingLot lot = new ParkingLot();
        lot.addParkingStall().parkCar("123456", 5);
        lot.addParkingStall().parkCar("EWA 705", 2);
        lot.addParkingStall();
        addParkingLot(lot);
    }

    @Override
    public ParkingPass getParkingPass(int passID) {
        for (var lot : lots)
            for (var stall : lot.getParkingStalls()) {
                ParkingPass pass = stall.getParkingPass(passID);
                if (pass != null)
                    return pass;
            }
        return null;
    }

    @Override
    public ParkingLot getParkingLot(int parkingLotID) {
        for (var lot : lots)
            if (lot.getLotNumber() == parkingLotID)
                return lot;
        return null;
    }

    @Override
    public List<ParkingLot> getParkingLots() {
        return lots;
    }

    @Override
    public Invoice getInvoice(int passId) {
        ParkingPass pass = getParkingPass(passId);
        if (pass != null)
            return pass.getInvoice();
        return null;
    }

    @Override
    public void addParkingPass(ParkingPass pass) {

    }

    @Override
    public void addParkingStall(ParkingStall stall) {

    }

    @Override
    public void addParkingLot(ParkingLot lot) {
        lots.add(lot);
    }

    @Override
    public void saveParkingLot(ParkingLot lot) {

    }

    @Override
    public void addInvoice(Invoice invoice) {
        // Don't feel like it
    }
}

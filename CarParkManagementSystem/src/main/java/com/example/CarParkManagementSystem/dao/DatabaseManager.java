package com.example.CarParkManagementSystem.dao;

import com.example.CarParkManagementSystem.entity.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

public class DatabaseManager implements IDatabaseManager {
	MongoClient mc = MongoClients.create();
	MongoTemplate mt = new MongoTemplate(mc, "CarParking");
	MongoOperations mongoOperations = mt;

	@Override
	public ParkingPass getParkingPass(int passID) {
		try {
			return mongoOperations.findOne(new Query(Criteria.where("passId").is(passID)), ParkingPass.class);

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ParkingLot getParkingLot(int parkingLotID) {
		try {
			return mongoOperations.findOne(new Query(Criteria.where("lotId").is(parkingLotID)), ParkingLot.class);

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<ParkingLot> getParkingLots() {
		return mongoOperations.findAll(ParkingLot.class);

	}

	@Override
	public Invoice getInvoice(int invoiceID) {
		// return mongoOperations.findOne(new
		// Query(Criteria.where("invoiceId").is(invoiceID)), Invoice.class);
		return null;
	}

	public List<ParkingStall> getStalls() {
		try {
			return mongoOperations.findAll(ParkingStall.class);
		} catch (Exception e) {
			return null;
		}
	}

	public void addParkingPass(ParkingPass pass) {
		try {
			mongoOperations.save(pass);

		} catch (Exception e) {
		}
	}

	@Override
	public void addParkingStall(ParkingStall stall) {
		try {
			mongoOperations.save(stall);
		} catch (Exception e) {
		}
	}

	@Override
	public void addParkingLot(ParkingLot lot) {
		try {
			mongoOperations.save(lot);
		} catch (Exception e) {
		}
	}

	@Override
	public void addInvoice(Invoice invoice) {
		try {
			mongoOperations.save(invoice);
		} catch (Exception e) {
		}
	}

	public void initializeDatabase() {
		mongoOperations.dropCollection("lots");
		if(!mongoOperations.collectionExists("lots")) {
			ParkingLot pl = new ParkingLot();
			for (int x = 0; x < 10; x++)
				pl.addParkingStall();
			mongoOperations.save(pl);
		}
	}
}

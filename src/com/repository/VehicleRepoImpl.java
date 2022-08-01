package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.database.InMemoryDB;
import com.exceptions.BaseException;
import com.models.ParkingSlotEntity;

public class VehicleRepoImpl implements VehicleRepoI{

	InMemoryDB memDb;
	
	public VehicleRepoImpl(InMemoryDB pMemDb) {
		memDb = pMemDb; 
	}
	
	@Override
	public List<String> fetchVehicleRegNumsOfDriverOfGivenAge(int age) throws BaseException {
		if(age <=0) throw new BaseException("Age is invalid");
		List<ParkingSlotEntity> data = memDb.fetchDataForAge(age);
		List<String> res = new ArrayList<>();
		for(ParkingSlotEntity se : data) {
			res.add(se.getVehicleRegNum());
		}
		return res;
	}

}

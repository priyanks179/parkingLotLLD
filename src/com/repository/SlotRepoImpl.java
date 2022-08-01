package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.database.InMemoryDB;
import com.exceptions.BaseException;
import com.models.ParkingSlotEntity;
import com.models.ParkingSlotVO;

public class SlotRepoImpl implements SlotRepoI{

	InMemoryDB memDb;
	
	public SlotRepoImpl(InMemoryDB pMemDb) {
		memDb = pMemDb; 
	}
	
	@Override
	public List<Integer> fetchSlotIdsForVehicleRegNum(String vehicleRegNum) throws BaseException {
		if(vehicleRegNum== null) throw new BaseException("Vehicle registration number is invalid");
		List<ParkingSlotEntity> data = memDb.fetchDataForVRN(vehicleRegNum);
		List<Integer> res = new ArrayList<>();
		for(ParkingSlotEntity se : data) {
			res.add(se.getSlotId());
		}
		return res;
	}

	@Override
	public List<Integer> fetchSlotIdsForDriversOfGivenAge(int age) throws BaseException {
		if(age <=0) throw new BaseException("Driver age is invalid");
		List<ParkingSlotEntity> data = memDb.fetchDataForAge(age);
		List<Integer> res = new ArrayList<>();
		for(ParkingSlotEntity se : data) {
			res.add(se.getSlotId());
		}
		return res;
	}

	@Override
	public void insertSlotInfo(ParkingSlotVO slotVO) throws BaseException {
		if(slotVO==null) throw new BaseException("SlotVO is invalid");
		int slotId = slotVO.getSlotId();
		String vehicleRegNum = slotVO.getVehicle().getVehicleRegNo();
		int age = slotVO.getVehicle().getDriver().getAge();
		ParkingSlotEntity se = new ParkingSlotEntity(slotId, vehicleRegNum, age);
		memDb.insertSlotData(se);
	}

}

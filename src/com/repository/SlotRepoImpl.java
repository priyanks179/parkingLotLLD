package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.database.InMemoryDB;
import com.exceptions.BaseException;
import com.models.SlotEntity;
import com.models.SlotVO;

public class SlotRepoImpl implements SlotRepoI{

	InMemoryDB memDb;
	
	public SlotRepoImpl(InMemoryDB pMemDb) {
		memDb = pMemDb; 
	}
	
	@Override
	public List<Integer> fetchSlotIdsForVehicleRegNum(String vehicleRegNum) throws BaseException {
		if(vehicleRegNum== null) throw new BaseException("Vehicle registration number is invalid");
		List<SlotEntity> data = memDb.fetchDataForVRN(vehicleRegNum);
		List<Integer> res = new ArrayList<>();
		for(SlotEntity se : data) {
			res.add(se.getSlotId());
		}
		return res;
	}

	@Override
	public List<Integer> fetchSlotIdsForDriversOfGivenAge(int age) throws BaseException {
		if(age <=0) throw new BaseException("Driver age is invalid");
		List<SlotEntity> data = memDb.fetchDataForAge(age);
		List<Integer> res = new ArrayList<>();
		for(SlotEntity se : data) {
			res.add(se.getSlotId());
		}
		return res;
	}

	@Override
	public void insertSlotInfo(SlotVO slotVO) throws BaseException {
		if(slotVO==null) throw new BaseException("SlotVO is invalid");
		int slotId = slotVO.getSlotId();
		String vehicleRegNum = slotVO.getVehicle().getVehicleRegNo();
		int age = slotVO.getVehicle().getDriver().getAge();
		SlotEntity se = new SlotEntity(slotId, vehicleRegNum, age);
		memDb.insertSlotData(se);
	}

}

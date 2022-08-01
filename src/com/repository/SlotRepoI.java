package com.repository;

import java.util.List;

import com.exceptions.BaseException;
import com.models.ParkingSlotVO;

public interface SlotRepoI {

	/*
	 * fetches slotIds for vehicle registration number
	 * If number is not valid then will throw BaseException 
	 * saying invalid vehicle registration number
	 */
	public List<Integer> fetchSlotIdsForVehicleRegNum(String vehicleRegNum) throws BaseException;
	
	/*
	 * fetches slotIds for drivers of given age
	 * If age is not valid then will throw BaseException 
	 * saying invalid age
	 */
	public List<Integer> fetchSlotIdsForDriversOfGivenAge(int age) throws BaseException;
	
	/*
	 * After allocating a slot to vehicle will save the data to db
	 */
	public void insertSlotInfo(ParkingSlotVO slotVO) throws BaseException;
	
}

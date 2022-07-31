package com.parkingHandler;

import com.exceptions.BaseException;
import com.exceptions.SlotNotAvailableException;
import com.exceptions.SlotNotFilledException;
import com.models.VehicleVO;

public interface ParkingLotHandlerI {
	
	/*will allocate slot using vehicleVo, 
	 *if no slot available then throws SlotNotAvailableException;
	 *else give allocated slot id
	 *Will also store the data in DB and throws BaseException if there is error in doing so
	*/
	public Integer allocateSlot(VehicleVO vehicle) throws SlotNotAvailableException, BaseException;	
	/*
	 *for given slotId will set isOccupied in SlotVO to false
	 *If slot already empty then will throw exception 
	 *If slotId is invalid then will throw BaseEXception 
	 */
	public String emptySlot(int slotId) throws SlotNotFilledException, BaseException;
}

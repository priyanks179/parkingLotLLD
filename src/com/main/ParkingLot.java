package com.main;

import com.exceptions.BaseException;
import com.exceptions.SlotNotAvailableException;
import com.exceptions.SlotNotFilledException;
import com.models.VehicleVO;
import com.parkingHandler.ParkingLotHandlerI;
import com.parkingHandler.ParkingLotHandlerImpl;
import com.repository.RepoFactory;
import com.repository.SlotRepoI;

public class ParkingLot {
	
	private ParkingLotHandlerI parkingLotHandler;
	private SlotRepoI slotRepo;
	private static ParkingLot parkingLot;
	
	public ParkingLot(int pParkingLotSize){
		slotRepo = RepoFactory.getSlotRepoInstance();
		parkingLotHandler = ParkingLotHandlerImpl.createParkingLotInstance(pParkingLotSize, slotRepo);
	}
	
	
	public String allocateSlot(VehicleVO vehicle) {
		String res = "";
		try {
			int slotId = parkingLotHandler.allocateSlot(vehicle);
			res = "Car with vehicle registration number \""+vehicle.getVehicleRegNo()+"\" has been parked at slot number ";
			res += String.valueOf(slotId);
		}catch(SlotNotAvailableException e) {
			res = e.getMessage();
		}catch(BaseException e) {
			//handle for some other exception
			res = e.getMessage();
		}
		return res;
	}
	
	public String freeSlot(int slotID) {
		String res = "";
		try {
			res = parkingLotHandler.emptySlot(slotID);
		}catch(SlotNotFilledException e) {
			res = e.getMessage();
		}catch(BaseException e) {
			res = e.getMessage();
		}
		return res;
	}
	
	public static ParkingLot getInstance(int parkingLotSize) throws BaseException{
		if(parkingLotSize<=0) throw new BaseException("Parking lot size is invalid");
		if(parkingLot==null) {
			synchronized (ParkingLot.class) {
				if(parkingLot==null) {
					parkingLot = new ParkingLot(parkingLotSize);
				}
				
			}
		}
		return parkingLot;
	}
	
}

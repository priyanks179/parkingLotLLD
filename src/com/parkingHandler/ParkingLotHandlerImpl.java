package com.parkingHandler;

import java.util.TreeSet;

import com.exceptions.BaseException;
import com.exceptions.SlotNotAvailableException;
import com.exceptions.SlotNotFilledException;
import com.models.ParkingSlotVO;
import com.models.VehicleVO;
import com.repository.SlotRepoI;

public class ParkingLotHandlerImpl implements ParkingLotHandlerI{

	ParkingSlotVO[] slotArray;
	TreeSet<Integer> availableSlots;
	int parkingLotSize;
	SlotRepoI slotRepo;
	private static ParkingLotHandlerImpl parkingLotHandlerImpl=null;
	
	
	private ParkingLotHandlerImpl(int pParkingLotSize, SlotRepoI pSlotRepo) {
		parkingLotSize = pParkingLotSize;
		slotArray = new ParkingSlotVO[pParkingLotSize];
		availableSlots = new TreeSet<>();
		for(int ind=0; ind<pParkingLotSize; ind++) {
			slotArray[ind] = new ParkingSlotVO(ind+1);
			availableSlots.add(ind+1);
		}
		slotRepo = pSlotRepo;
	}
	
	@Override
	public Integer allocateSlot(VehicleVO vehicle) throws SlotNotAvailableException, BaseException {
		if(availableSlots.size()==0) throw new SlotNotAvailableException("No parking slot is available.");
		int slotId = availableSlots.iterator().next();
		availableSlots.remove(slotId);
		ParkingSlotVO slotVO = slotArray[slotId-1];
		slotVO.setIsOccupied(true);
		slotVO.setVehicle(vehicle);
		slotRepo.insertSlotInfo(slotVO);
		return slotId;
	}

	@Override
	public String emptySlot(int slotId) throws SlotNotFilledException, BaseException {
		if(slotId<1 || slotId>parkingLotSize) throw new BaseException("Invalid slotId is passed.");
		ParkingSlotVO slotVO = slotArray[slotId-1];
		if(!slotVO.getIsOccupied()) throw new SlotNotFilledException("Slot is already empty.");
		slotVO.setIsOccupied(false);
		availableSlots.add(slotId);
		String vehicleRegNumber = slotVO.getVehicle().getVehicleRegNo();
		String driverAge = String.valueOf(slotVO.getVehicle().getDriver().getAge());
		String res = "Slot number "+String.valueOf(slotId)+" vacated, ";
		res += "the car with vehicle registration number \""+vehicleRegNumber+"\" left the space, ";
		res += "the driver of the car was of age "+driverAge;
		return res;
	}

	public static ParkingLotHandlerImpl createParkingLotInstance(int slotCount, SlotRepoI pSlotRepo){
		if(parkingLotHandlerImpl!=null) return parkingLotHandlerImpl;
		synchronized(ParkingLotHandlerImpl.class) {//for mulitThread safety
			if(parkingLotHandlerImpl==null) {
				parkingLotHandlerImpl = new ParkingLotHandlerImpl(slotCount, pSlotRepo);
			}
		}
		return parkingLotHandlerImpl;
	}

}

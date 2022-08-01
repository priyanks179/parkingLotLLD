package com.models;

public class ParkingSlotVO {
	int slotId;
	VehicleVO vehicle;
	Boolean isOccupied;
	public int getSlotId() {
		return slotId;
	}
	
	public ParkingSlotVO(int pSlotId) {
		slotId = pSlotId;
		isOccupied = false;
	}
	
	public void fillSlot(VehicleVO pVehicle) {
		vehicle = pVehicle;
		isOccupied = true;
	}
	
	public void freeSlot() {
		isOccupied = false;
	}
	
	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}
	public VehicleVO getVehicle() {
		return vehicle;
	}
	public void setVehicle(VehicleVO vehicle) {
		this.vehicle = vehicle;
	}
	public Boolean getIsOccupied() {
		return isOccupied;
	}
	public void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
}

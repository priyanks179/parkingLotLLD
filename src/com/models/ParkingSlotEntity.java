package com.models;


//Db table to entity mapping
public class ParkingSlotEntity {
	
	int slotId;
	String vehicleRegNum;
	int age;
	
	public ParkingSlotEntity(int pSlotId, String pVRN, int pAge ){
		slotId = pSlotId;
		vehicleRegNum = pVRN;
		age = pAge;
	}
	
	public ParkingSlotEntity(ParkingSlotEntity pSlotEntity){
		slotId = pSlotEntity.getSlotId();
		vehicleRegNum = pSlotEntity.getVehicleRegNum();
		age = pSlotEntity.getAge();
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public String getVehicleRegNum() {
		return vehicleRegNum;
	}

	public void setVehicleRegNum(String vehicleRegNum) {
		this.vehicleRegNum = vehicleRegNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}

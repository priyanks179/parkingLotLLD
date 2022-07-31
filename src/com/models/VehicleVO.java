package com.models;

public class VehicleVO {
	String vehicleRegNo;
	DriverVO driver;
	public VehicleVO(String pVehicleRegNo, DriverVO pDriver){
		vehicleRegNo = pVehicleRegNo;
		driver = pDriver;
	}
	public String getVehicleRegNo() {
		return vehicleRegNo;
	}
	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}
	public DriverVO getDriver() {
		return driver;
	}
	public void setDriver(DriverVO driver) {
		this.driver = driver;
	}
}

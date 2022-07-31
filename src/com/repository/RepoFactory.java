package com.repository;

import com.database.InMemoryDB;

public class RepoFactory {

	private static VehicleRepoI vehicleRepo;
	private static SlotRepoI slotRepo;
	private static InMemoryDB memDB;
	
	public static VehicleRepoI getVehicleRepoInstance() {
		if(vehicleRepo==null) {
			synchronized(RepoFactory.class) {
				if(vehicleRepo==null) {
					memDB = memDB.getInstance();
					vehicleRepo = new VehicleRepoImpl(memDB);
				}
			}
		}
		return vehicleRepo;
	}
	
	public static SlotRepoI getSlotRepoInstance() {
		if(slotRepo==null) {
			synchronized(RepoFactory.class) {
				if(slotRepo==null) {
					memDB = memDB.getInstance();
					slotRepo = new SlotRepoImpl(memDB);
				}
			}
		}
		return slotRepo;
	}
	
}

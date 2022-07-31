package com.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.models.SlotEntity;

/*
 * This will store list of 
 */
public class InMemoryDB {

	HashMap<Integer, List<SlotEntity>> ageColumnIndexingMap; 
	HashMap<String, List<SlotEntity>> vehicleRegNumColumnIndexingMap;
	
	
	private static InMemoryDB memDB;
	
	public InMemoryDB() {
		vehicleRegNumColumnIndexingMap = new HashMap<>();
		ageColumnIndexingMap = new HashMap<>();
	}
	
	public static InMemoryDB getInstance() {
		if(memDB==null) {
			synchronized(InMemoryDB.class) {
				if(memDB==null) {
					memDB = new InMemoryDB();
				}
			}
		}
		return memDB;
	}
	
	public void insertSlotData(SlotEntity slotEntity){
		List<SlotEntity> lst;
		lst = ageColumnIndexingMap.getOrDefault(slotEntity.getAge(), new ArrayList<>());
		lst.add(slotEntity);
		ageColumnIndexingMap.put(slotEntity.getAge(), lst);
		lst = vehicleRegNumColumnIndexingMap.getOrDefault(slotEntity.getVehicleRegNum(), new ArrayList<>());
		lst.add(slotEntity);
		vehicleRegNumColumnIndexingMap.put(slotEntity.getVehicleRegNum(), lst);
	}
	
	public List<SlotEntity> fetchDataForAge(Integer age){
		if(age==null) return null;
		List<SlotEntity> dataForAge = ageColumnIndexingMap.getOrDefault(age, new ArrayList<>());
		return dataForAge;
	} 
	
	/*
	 * Fetches data for particular 
	 * vehicle registration number (vrn)
	 */
	public List<SlotEntity> fetchDataForVRN(String vehicleRegNum){
		if(vehicleRegNum==null) return null;
		List<SlotEntity> dataForVrn = vehicleRegNumColumnIndexingMap.getOrDefault(vehicleRegNum, new ArrayList<>());
		return dataForVrn;
	}
}

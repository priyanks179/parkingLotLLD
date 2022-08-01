package com.applicationDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.exceptions.BaseException;
import com.main.ParkingLot;
import com.models.DriverVO;
import com.models.VehicleVO;
import com.repository.RepoFactory;
import com.repository.SlotRepoI;
import com.repository.VehicleRepoI;

public class Driver {

	private static ParkingLot parkingLot;
	private static VehicleRepoI vehicleRepo;
	private static SlotRepoI slotRepo;
	private static boolean isParkingLotCreated = false;
	
	public static void main(String[] args) throws IOException {
		File directory = new File("");
		String absPath = directory.getAbsolutePath();
		String fileName = "input.txt";
		String path = absPath + "/"+fileName;
		readAndProcessFile(path);
    }
	
	public static void readAndProcessFile(String path) {
		File file = new File(path);
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine())
	            process(sc.nextLine());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}
       return;
	}
	
	public static void process(String cmdAndParam) {
		
		String cmd = cmdAndParam.split(" ")[0];
		if(!cmd.equals("Create_parking_lot") && !isParkingLotCreated) {
			System.out.println("Parking lot wasn't created");
			return;
		}
		switch(cmd) {
			case "Create_parking_lot":
				createParkingLot(cmdAndParam);
				break;
			case "Park":
				allocateSlot(cmdAndParam);
				break;
			case "Slot_numbers_for_driver_of_age":
				fetchSlotIdsForDriverAge(cmdAndParam);
				break;
			case "Slot_number_for_car_with_number":
				fetchSlotIdsForVehicleRegNum(cmdAndParam);
				break;
			case "Vehicle_registration_number_for_driver_of_age":
				fetchVehicleRegNumsForParticularDriverAge(cmdAndParam);
				break;
			case "Leave":
				emptyParkingSlot(cmdAndParam);
				break;
			default:
				handleIncorrectCommand();
		}
		
	}
	
	private static void handleIncorrectCommand() {
		System.out.println("Please enter correct command");
	}

	private static void emptyParkingSlot(String cmdAndParam) {
		int slotId = Integer.parseInt(cmdAndParam.split(" ")[1]);
		String output = parkingLot.freeSlot(slotId);
		System.out.println(output);
	}

	private static void fetchVehicleRegNumsForParticularDriverAge(String cmdAndParam) {
		int driverAge = Integer.parseInt(cmdAndParam.split(" ")[1]);
		try {
			List<String> vehicleRegNums = vehicleRepo.fetchVehicleRegNumsOfDriverOfGivenAge(driverAge);
			String output = vehicleRegNums.stream().collect(Collectors.joining(","));
			System.out.println(output);
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void fetchSlotIdsForVehicleRegNum(String cmdAndParam) {
		String vehicleRegNum = cmdAndParam.split(" ")[1];
		try {
			List<Integer> slotIdList = slotRepo.fetchSlotIdsForVehicleRegNum(vehicleRegNum);
			String output = slotIdList.stream().map(e->String.valueOf(e)).collect(Collectors.joining(","));
			System.out.println(output);
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void fetchSlotIdsForDriverAge(String cmdAndParam) {
		int driverAge = Integer.parseInt(cmdAndParam.split(" ")[1]);
		try {
			List<Integer> slotIdList = slotRepo.fetchSlotIdsForDriversOfGivenAge(driverAge);
			String output = slotIdList.stream().map(e->String.valueOf(e)).collect(Collectors.joining(","));
			System.out.println(output);
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void allocateSlot(String cmdAndParam) {
		int driverAge = Integer.parseInt(cmdAndParam.split(" ")[3]);
		String vehicleRegNum = cmdAndParam.split(" ")[1];
		DriverVO driverVO = new DriverVO(driverAge);
		VehicleVO vehicleVo = new VehicleVO(vehicleRegNum, driverVO);
		String output = parkingLot.allocateSlot(vehicleVo);
		System.out.println(output);
	}

	public static void createParkingLot(String cmdAndParam) {
		try {
			Integer parkingLotSize = Integer.parseInt(cmdAndParam.split(" ")[1]);
			parkingLot = ParkingLot.getInstance(parkingLotSize);
			slotRepo = RepoFactory.getSlotRepoInstance();
			vehicleRepo = RepoFactory.getVehicleRepoInstance();
			System.out.println("Created parking of "+cmdAndParam.split(" ")[1]+" slots");
			isParkingLotCreated = true;
		} catch (BaseException e) {
			isParkingLotCreated = false;
			System.out.println(e.getMessage());
		}	
	}
	
	
}

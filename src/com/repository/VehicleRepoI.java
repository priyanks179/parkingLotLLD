package com.repository;

import java.util.List;

import com.exceptions.BaseException;

public interface VehicleRepoI {

	/*
	 * Will fetch vehicle registration numbers of 
	 * vehicle having driver of particular age
	 * If age is invalid then throw generic BaseException saying invalid age
	 */
	List<String> fetchVehicleRegNumsOfDriverOfGivenAge(int age) throws BaseException;
	
}

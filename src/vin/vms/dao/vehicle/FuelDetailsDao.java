package vin.vms.dao.vehicle;


import java.util.Date;
import java.util.List;

import vin.vms.dao.entities.FuelDetails;




/**
 * The Interface MockTestListDao.
 */
public interface FuelDetailsDao {


	
	FuelDetails saveFuelDetails(FuelDetails fuelDetails);
	String deleteFuelDetails(FuelDetails fuelDetails);
	List<FuelDetails> readFuelDetails(Long fuelId);
	List<FuelDetails> searchFuelDetails(String vehicleId, Date fromDate, Date toDate);
	
}


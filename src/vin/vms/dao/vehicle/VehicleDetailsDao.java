package vin.vms.dao.vehicle;


import java.util.Date;
import java.util.List;

import vin.vms.dao.entities.BookingDetails;
import vin.vms.dao.entities.VehicleDetails;
import vin.vms.dao.entities.VehicleDetails;




/**
 * The Interface MockTestListDao.
 */
public interface VehicleDetailsDao {


	
	VehicleDetails saveVehicleDetails(VehicleDetails vehicleDetails);
	String deleteVehicleDetails(VehicleDetails vehicleDetails);
	List<VehicleDetails> readVehicleDetails(String vehicleId);
	List<VehicleDetails> searchAvailableVehicleDetails(Date fromDate, Date toDate);
	
}


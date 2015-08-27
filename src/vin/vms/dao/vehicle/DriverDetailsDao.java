package vin.vms.dao.vehicle;


import java.util.Date;
import java.util.List;

import vin.vms.dao.entities.DriverDetails;
import vin.vms.dao.entities.DriverDetails;
import vin.vms.dao.entities.VehicleDetails;




/**
 * The Interface MockTestListDao.
 */
public interface DriverDetailsDao {


	
	DriverDetails saveDriverDetails(DriverDetails driverDetails);
	String deleteDriverDetails(DriverDetails driverDetails);
	List<DriverDetails> readDriverDetails(String driverId);
	List<DriverDetails> searchAvailableDriverDetails(Date fromDate, Date toDate);
	
}


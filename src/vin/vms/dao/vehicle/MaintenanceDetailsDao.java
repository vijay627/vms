package vin.vms.dao.vehicle;


import java.util.Date;
import java.util.List;

import vin.vms.dao.entities.MaintenanceDetails;




/**
 * The Interface MockTestListDao.
 */
public interface MaintenanceDetailsDao {


	
	MaintenanceDetails saveMaintenanceDetails(MaintenanceDetails maintenanceDetails);
	String deleteMaintenanceDetails(MaintenanceDetails maintenanceDetails);
	List<MaintenanceDetails> readMaintenanceDetails(Long objId);
	List<MaintenanceDetails> searchMaintenanceDetails(String vehicleId, Date fromDate, Date toDate);
	
}


package vin.vms.web.dwr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import vin.vms.dao.entities.BookingDetails;
import vin.vms.dao.entities.DriverDetails;
import vin.vms.dao.entities.FuelDetails;
import vin.vms.dao.entities.MaintenanceDetails;
import vin.vms.dao.entities.VehicleDetails;
import vin.vms.dao.vehicle.BookingDetailsDao;
import vin.vms.dao.vehicle.DriverDetailsDao;
import vin.vms.dao.vehicle.FuelDetailsDao;
import vin.vms.dao.vehicle.MaintenanceDetailsDao;
import vin.vms.dao.vehicle.VehicleDetailsDao;

public class VmsDwrUtilities {

	
	private BookingDetailsDao bookingDetailsDao;
		
		
	private VehicleDetailsDao vehicleDetailsDao;
	
	private DriverDetailsDao driverDetailsDao;
	
	public VehicleDetailsDao getVehicleDetailsDao() {
		return vehicleDetailsDao;
	}
	
	public void setVehicleDetailsDao(VehicleDetailsDao vehicleDetailsDao) {
		this.vehicleDetailsDao = vehicleDetailsDao;
	}
	public BookingDetailsDao getBookingDetailsDao() {
		return bookingDetailsDao;
	}

	public void setBookingDetailsDao(BookingDetailsDao bookingDetailsDao) {
		this.bookingDetailsDao = bookingDetailsDao;
	}
	
	
	private FuelDetailsDao fuelDetailsDao;
	
	public FuelDetailsDao getFuelDetailsDao() {
		return fuelDetailsDao;
	}

	public void setFuelDetailsDao(FuelDetailsDao fuelDetailsDao) {
		this.fuelDetailsDao = fuelDetailsDao;
	}
	
	
	private MaintenanceDetailsDao maintenanceDetailsDao;
	
	public MaintenanceDetailsDao getMaintenanceDetailsDao() {
		return maintenanceDetailsDao;
	}

	public void setMaintenanceDetailsDao(MaintenanceDetailsDao maintenanceDetailsDao) {
		this.maintenanceDetailsDao = maintenanceDetailsDao;
	}

	
	public VmsDwrUtilities()
	{
		
	}
	
	public BookingDetails[] getBookingDetails(String vehicleId,String fromDate, String toDate)
	{
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date convetedFromDate = StringUtils.isNotEmpty(fromDate)?sdf.parse(fromDate):null;
			Date convetedToDate = StringUtils.isNotEmpty(toDate)?sdf.parse(toDate):null;
			
			List<BookingDetails> resultList = (List<BookingDetails>)bookingDetailsDao.searchBookingDetails(vehicleId, convetedFromDate, convetedToDate);
			
			if(resultList !=null)
			{
				BookingDetails[] bookingDetailsArray = new BookingDetails[resultList.size()];
				
				for (int i = 0; i < resultList.size(); i++) {
					bookingDetailsArray[i] = resultList.get(i);					
					
				}
				return bookingDetailsArray;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public FuelDetails[] getFuelDetails(String vehicleId,String fromDate, String toDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date convetedFromDate = StringUtils.isNotEmpty(fromDate)?sdf.parse(fromDate):null;
			Date convetedToDate = StringUtils.isNotEmpty(toDate)?sdf.parse(toDate):null;
			
			List<FuelDetails> resultList = (List<FuelDetails>)fuelDetailsDao.searchFuelDetails(vehicleId, convetedFromDate, convetedToDate);
			
			if(resultList !=null)
			{
				FuelDetails[] fuelDetailsArray = new FuelDetails[resultList.size()];
				
				for (int i = 0; i < resultList.size(); i++) {
					fuelDetailsArray[i] = resultList.get(i);
					
				}
				return fuelDetailsArray;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public MaintenanceDetails[] getMaintenanceDetails(String vehicleId,String fromDate, String toDate)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date convetedFromDate = StringUtils.isNotEmpty(fromDate)?sdf.parse(fromDate):null;
			Date convetedToDate = StringUtils.isNotEmpty(toDate)?sdf.parse(toDate):null;
			
			List<MaintenanceDetails> resultList = (List<MaintenanceDetails>)maintenanceDetailsDao.searchMaintenanceDetails(vehicleId, convetedFromDate, convetedToDate);
			
			if(resultList !=null)
			{
				MaintenanceDetails[] maintenanceDetailsArray = new MaintenanceDetails[resultList.size()];
				
				for (int i = 0; i < resultList.size(); i++) {
					maintenanceDetailsArray[i] = resultList.get(i);
					
				}
				return maintenanceDetailsArray;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public VehicleDetails[] getVehicleDetails(String fromDate, String toDate)
	{
		
		System.out.println("fromDate :: "+fromDate);
		System.out.println("toDate :: "+toDate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date convetedFromDate = StringUtils.isNotEmpty(fromDate)?sdf.parse(fromDate):null;
			Date convetedToDate = StringUtils.isNotEmpty(toDate)?sdf.parse(toDate):null;
			
			List<VehicleDetails> resultList = (List<VehicleDetails>)vehicleDetailsDao.searchAvailableVehicleDetails(convetedFromDate, convetedToDate);
			
			System.out.println("Size :: "+resultList.size());
			if(resultList !=null)
			{
				VehicleDetails[] vehicleDetailsArray = new VehicleDetails[resultList.size()];
				
				for (int i = 0; i < resultList.size(); i++) {
					vehicleDetailsArray[i] = resultList.get(i);
					
				}
				return vehicleDetailsArray;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public DriverDetails[] getDriverDetails(String fromDate, String toDate)
	{
		
		System.out.println("fromDate :: "+fromDate);
		System.out.println("toDate :: "+toDate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date convetedFromDate = StringUtils.isNotEmpty(fromDate)?sdf.parse(fromDate):null;
			Date convetedToDate = StringUtils.isNotEmpty(toDate)?sdf.parse(toDate):null;
			
			List<DriverDetails> resultList = (List<DriverDetails>)driverDetailsDao.searchAvailableDriverDetails(convetedFromDate, convetedToDate);
			
			System.out.println("Size :: "+resultList.size());
			if(resultList !=null)
			{
				DriverDetails[] vehicleDetailsArray = new DriverDetails[resultList.size()];
				
				for (int i = 0; i < resultList.size(); i++) {
					vehicleDetailsArray[i] = resultList.get(i);
					
				}
				return vehicleDetailsArray;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public String signOutFromApplication(HttpServletRequest req)
	{
		
		req.getSession().invalidate();
		
		return null;
	}

	public DriverDetailsDao getDriverDetailsDao() {
		return driverDetailsDao;
	}

	public void setDriverDetailsDao(DriverDetailsDao driverDetailsDao) {
		this.driverDetailsDao = driverDetailsDao;
	}
	
	

	
	

	
}

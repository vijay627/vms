package vin.vms.web.command.vehicle;

import java.util.Date;




public class MaintenanceCommand {
	
	private String function;
	private Long objId;
	
	private String vehicleIdCrit;
	private String fromDateCrit;
	private String toDateCrit;
	
	private String vehicleId;
	private String vehicleName;
	
	private String driverId;
	private String driverName;
	
	private String companyName;
	
	private String repairDate;
	private String totalCost;
	
	private String description;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}

	public String getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getFromDateCrit() {
		return fromDateCrit;
	}

	public void setFromDateCrit(String fromDateCrit) {
		this.fromDateCrit = fromDateCrit;
	}

	public String getToDateCrit() {
		return toDateCrit;
	}

	public void setToDateCrit(String toDateCrit) {
		this.toDateCrit = toDateCrit;
	}

	public String getVehicleIdCrit() {
		return vehicleIdCrit;
	}

	public void setVehicleIdCrit(String vehicleIdCrit) {
		this.vehicleIdCrit = vehicleIdCrit;
	}
	
	
	
	
	
	
	
	
}

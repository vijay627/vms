package vin.vms.web.command.vehicle;

import java.util.Date;




public class FuelCommand {
	
	private String function;
	private Long fuelId;
	
	private String vehicleIdCrit;
	private String fromDateCrit;
	private String toDateCrit;
	
	private String vehicleId;
	private String vehicleName;
	
	private String driverId;
	private String driverName;
	
	private String fuelDate;
	private String fuelType;
	private String fuelQuantity;
	private String totalCost;
	
	
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getFuelQuantity() {
		return fuelQuantity;
	}
	public void setFuelQuantity(String fuelQuantity) {
		this.fuelQuantity = fuelQuantity;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
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
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getFuelDate() {
		return fuelDate;
	}
	public void setFuelDate(String fuelDate) {
		this.fuelDate = fuelDate;
	}
	public Long getFuelId() {
		return fuelId;
	}
	public void setFuelId(Long fuelId) {
		this.fuelId = fuelId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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

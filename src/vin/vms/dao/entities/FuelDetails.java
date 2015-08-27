package vin.vms.dao.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class FuelDetails{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long fuelId;
	private String function;
	private String vehicleId;
	private String vehicleName;
	
	private String driverId;
	private String driverName;
	
	@Temporal(TemporalType.DATE)
	private Date fuelDate;
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
	
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
	public Date getFuelDate() {
		return fuelDate;
	}
	public void setFuelDate(Date fuelDate) {
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
	
	
	
	
	
	
	
	
	
	
}

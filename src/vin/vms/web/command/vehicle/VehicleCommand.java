package vin.vms.web.command.vehicle;






public class VehicleCommand {
	
	
	private Long objId;
	
	private String function;
	
	private String vehicleIdCrit;
	private String vehicleId;
	private String vehicleName;
	
	private String registrationNumber;
	private String chassisNumber;
	private String engineNumber;
	
	private String makerName;
	private String ownerName;
	
	private String fuelType;
	private String vehicleType;
	
	private Integer vehicleCost;
	private Double costPerKM;
	
	private String insurerName;
	private String companyName;
	private String dateOfInsurance;
	private String dateOfExpiry;
	private Integer insuranceCost;
	
	
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Double getCostPerKM() {
		return costPerKM;
	}
	public void setCostPerKM(Double costPerKM) {
		this.costPerKM = costPerKM;
	}
	
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public String getDateOfInsurance() {
		return dateOfInsurance;
	}
	public void setDateOfInsurance(String dateOfInsurance) {
		this.dateOfInsurance = dateOfInsurance;
	}
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleIdCrit() {
		return vehicleIdCrit;
	}
	public void setVehicleIdCrit(String vehicleIdCrit) {
		this.vehicleIdCrit = vehicleIdCrit;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public Integer getInsuranceCost() {
		return insuranceCost;
	}
	public void setInsuranceCost(Integer insuranceCost) {
		this.insuranceCost = insuranceCost;
	}
	public String getInsurerName() {
		return insurerName;
	}
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public Integer getVehicleCost() {
		return vehicleCost;
	}
	public void setVehicleCost(Integer vehicleCost) {
		this.vehicleCost = vehicleCost;
	}
	
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}
	
	
	
	
	
	
	
	
}

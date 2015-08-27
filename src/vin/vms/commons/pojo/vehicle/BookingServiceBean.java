package vin.vms.commons.pojo.vehicle;

import java.util.Date;

import vin.vms.commons.pojo.Bean;




public class BookingServiceBean implements Bean{
	
	private String function;
	private Long bookingId;
	
	private String vehicleIdCrit;
	private String fromDateCrit;
	private String toDateCrit;
	
	
	private String customerName;
	private String customerContactNumber;
	private String customerAddress;
	
	private String bookingDate;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	
	private String fromLocation;
	private String toLocation;
	
	private String vehicleId;
	private String vehicleName;
	
	private String driverId;
	private String driverName;
	
	private Double costPerKm;
	private String totalKms;
	private Double totalAmount;
	private Double advanceAmount;
	private Double remianingAmount;
	
	private String status;
	
	private String description;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(Double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	
	public Double getCostPerKm() {
		return costPerKm;
	}
	public void setCostPerKm(Double costPerKm) {
		this.costPerKm = costPerKm;
	}
	public String getCustomerContactNumber() {
		return customerContactNumber;
	}
	public void setCustomerContactNumber(String customerContactNumber) {
		this.customerContactNumber = customerContactNumber;
	}
	
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Double getRemianingAmount() {
		return remianingAmount;
	}
	public void setRemianingAmount(Double remianingAmount) {
		this.remianingAmount = remianingAmount;
	}
	
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getTotalKms() {
		return totalKms;
	}
	public void setTotalKms(String totalKms) {
		this.totalKms = totalKms;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
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
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	
	
	
	
	
	
	
	
	
}

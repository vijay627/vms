package vin.vms.dao.vehicle;


import java.util.Date;
import java.util.List;

import vin.vms.dao.entities.BookingDetails;




/**
 * The Interface MockTestListDao.
 */
public interface BookingDetailsDao {


	
	BookingDetails saveBookingDetails(BookingDetails bookingDetails);
	String deleteBookingDetails(BookingDetails bookingDetails);
	List<BookingDetails> readBookingDetails(Long bookingId);
	List<BookingDetails> searchBookingDetails(String vehicleId, Date fromDate, Date toDate);
	
}


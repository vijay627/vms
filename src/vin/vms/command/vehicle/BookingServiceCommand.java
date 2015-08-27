package vin.vms.command.vehicle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vin.vms.commons.CommandInterface;
import vin.vms.commons.pojo.Bean;
import vin.vms.commons.pojo.vehicle.BookingServiceBean;
import vin.vms.dao.entities.BookingDetails;
import vin.vms.dao.entities.BookingDetails;
import vin.vms.dao.vehicle.BookingDetailsDao;

// TODO: Auto-generated Javadoc
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookingServiceCommand implements CommandInterface {

	
	private BookingDetailsDao bookingDetailsDao;
	
	public BookingDetailsDao getBookingDetailsDao() {
		return bookingDetailsDao;
	}

	public void setBookingDetailsDao(BookingDetailsDao bookingDetailsDao) {
		this.bookingDetailsDao = bookingDetailsDao;
	}

	/**
	 * (non-Javadoc).
	 * 
	 * @param data the data
	 * 
	 * @return the serializable
	 * 
	 * @throws Throwable the throwable
	 * 
	 * @see bgc.gen.commons.CommandInterface#execute(bgc.gen.commons.fdt.Bean)
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Serializable execute(Bean data) throws Throwable {
		BookingServiceBean bean = (BookingServiceBean)data;
		
		String function = bean.getFunction();
		
		BookingDetails bookingDetails = new BookingDetails();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			//PropertyUtils.copyProperties(bookingDetails, bean);
			
			BeanUtils.copyProperties(bean,bookingDetails, new String[]{"bookingDate","startDate","endDate"});
			
			
			if(StringUtils.isNotEmpty(bean.getBookingDate()))
			{
				bookingDetails.setBookingDate(formatter.parse(bean.getBookingDate()));
			}
			
			if(StringUtils.isNotEmpty(bean.getStartDate()))
			{
				bookingDetails.setStartDate(formatter.parse(bean.getStartDate()));
			}
			
			if(StringUtils.isNotEmpty(bean.getEndDate()))
			{
				bookingDetails.setEndDate(formatter.parse(bean.getEndDate()));
			}
			
			
			if("A".equals(function) || "U".equals(function))
			{
				bookingDetails = (BookingDetails)bookingDetailsDao.saveBookingDetails(bookingDetails);
				
				//PropertyUtils.copyProperties(bean, bookingDetails);
				
				BeanUtils.copyProperties(bookingDetails,bean, new String[]{"function","bookingDate","startDate","endDate"});
				
			}else if("D".equals(function))
			{
				bookingDetailsDao.deleteBookingDetails(bookingDetails);
				
			}else if("R".equals(function))
			{
				List<BookingDetails> resut= (List<BookingDetails>)bookingDetailsDao.readBookingDetails(bean.getBookingId());
				
				if(resut !=null && resut.size()>0)
				{
					bookingDetails = resut.get(0);
					
					BeanUtils.copyProperties(bookingDetails,bean, new String[]{"function","bookingDate","startDate","endDate"});
					
					if(bookingDetails.getBookingDate() !=null)
					{
						bean.setBookingDate(String.valueOf(formatter.format(bookingDetails.getBookingDate())));
					}
					
					if(bookingDetails.getStartDate() !=null)
					{
						bean.setStartDate(String.valueOf(formatter.format(bookingDetails.getStartDate())));
					}
					
					if(bookingDetails.getEndDate() !=null)
					{
						bean.setEndDate(String.valueOf(formatter.format(bookingDetails.getEndDate())));
					}
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return bean;
	}

	

	
	

	
}

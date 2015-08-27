package vin.vms.dao.vehicle;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vin.vms.dao.VmsDao;
import vin.vms.dao.entities.BookingDetails;



@Repository("bookingDetailsDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class BookingDetailsDaoImpl extends VmsDao implements
BookingDetailsDao {


	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public BookingDetails saveBookingDetails(BookingDetails bookingDetails)
	{
		
		try {
			
			save(bookingDetails);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return bookingDetails;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public String deleteBookingDetails(BookingDetails bookingDetails)
	{
		
		try {
			
			remove(bookingDetails);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public List<BookingDetails> readBookingDetails(Long bookingId)
	{
		
		try {
			
			if(bookingId !=null)
			{
				StringBuilder query = new StringBuilder();
				query.append("select bookingDetails from ").append(BookingDetails.class.getName()).append(" bookingDetails");
				query.append(" where bookingDetails.bookingId = :bookingId");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setLong("bookingId", bookingId);
				
				List<BookingDetails> list = q.list();
				
				return list;
			}
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public List<BookingDetails> searchBookingDetails(String vehicleId, Date fromDate, Date toDate)
	{
		
		System.out.println("vehicleId :: "+vehicleId);
		System.out.println("fromDate :: "+fromDate);
		System.out.println(" toDate :: "+ toDate);
		
		try {
			
			
			StringBuilder query = new StringBuilder();
			query.append("select bookingDetails from ").append(BookingDetails.class.getName()).append(" bookingDetails");
			
			if(StringUtils.isNotEmpty(vehicleId) && fromDate == null && toDate == null)
			{
				query.append(" where bookingDetails.vehicleId = :vehicleId");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setString("vehicleId", vehicleId);
				
				List<BookingDetails> list = q.list();
				
				return list;
				
			}else if(StringUtils.isEmpty(vehicleId) && fromDate != null && toDate != null)
			{
				query.append(" where bookingDetails.startDate between :fromDate and :toDate ");
				query.append(" or bookingDetails.endDate between :fromDate and :toDate ");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setDate("fromDate", fromDate);
				q.setDate("toDate", toDate);
				
				List<BookingDetails> list = q.list();
				
				return list;
			}else if(StringUtils.isNotEmpty(vehicleId) && fromDate != null && toDate != null)
			{
				query.append(" where bookingDetails.vehicleId = :vehicleId");
				query.append(" and (bookingDetails.startDate between :fromDate and :toDate ");
				query.append(" or bookingDetails.endDate between :fromDate and :toDate )");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setString("vehicleId", vehicleId);
				q.setDate("fromDate", fromDate);
				q.setDate("toDate", toDate);
				
				List<BookingDetails> list = q.list();
				
				System.out.println("Size :: "+list.size());
				
				return list;
			}
			
			
			
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}


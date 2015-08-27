package vin.vms.dao.vehicle;


import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vin.vms.dao.VmsDao;
import vin.vms.dao.entities.BookingDetails;
import vin.vms.dao.entities.DriverDetails;
import vin.vms.dao.entities.VehicleDetails;



@Repository("driverDetailsDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class DriverDetailsDaoImpl extends VmsDao implements
DriverDetailsDao {


	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public DriverDetails saveDriverDetails(DriverDetails driverDetails)
	{
		
		try {
			
			save(driverDetails);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return driverDetails;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=false)
	public String deleteDriverDetails(DriverDetails driverDetails)
	{
		
		try {
			
			remove(driverDetails);
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
	public List<DriverDetails> readDriverDetails(String driverId)
	{
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("select maintenanceDetails from ").append(DriverDetails.class.getName()).append(" maintenanceDetails");
			query.append(" where maintenanceDetails.driverId = :driverId");
			
			Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
			q.setString("driverId", driverId);
			
			List<DriverDetails> list = q.list();
			
			return list;
			
			
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
	public List<DriverDetails> searchAvailableDriverDetails(Date fromDate, Date toDate)
	{
		
		try {
			
			
			StringBuilder query = new StringBuilder();
			query.append("select driverDetails from ").append(DriverDetails.class.getName()).append(" driverDetails");
			
			if(fromDate != null && toDate != null)
			{
				System.out.println("going inside");
				query.append(" where driverDetails.driverId not in( ");
				
				query.append("select bookingDetails.driverId from ").append(BookingDetails.class.getName()).append(" bookingDetails");
				
				query.append(" where bookingDetails.startDate <= :fromDate and bookingDetails.startDate >= :fromDate ");
				
				query.append(" and bookingDetails.endDate <= :toDate and bookingDetails.endDate >= :toDate )");
				
				
				
			}
			
			
			Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
			if(fromDate != null && toDate != null)
			{
				q.setDate("fromDate", fromDate);
				q.setDate("toDate", toDate);
			}
			List<DriverDetails> list = q.list();
			
			System.out.println("result size: "+list.size());
			return list;
			
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


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
import vin.vms.dao.entities.VehicleDetails;
import vin.vms.dao.entities.VehicleDetails;



@Repository("vehicleDetailsDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class VehicleDetailsDaoImpl extends VmsDao implements
VehicleDetailsDao {


	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public VehicleDetails saveVehicleDetails(VehicleDetails vehicleDetails)
	{
		
		try {
			
			System.out.println("Hitting DAO");
			save(vehicleDetails);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return vehicleDetails;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public String deleteVehicleDetails(VehicleDetails vehicleDetails)
	{
		
		try {
			
			remove(vehicleDetails);
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
	public List<VehicleDetails> readVehicleDetails(String vehicleId)
	{
		
		try {
			
			StringBuilder query = new StringBuilder();
			query.append("select vehicleDetails from ").append(VehicleDetails.class.getName()).append(" vehicleDetails");
			query.append(" where vehicleDetails.vehicleId = :vehicleId");
			
			Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
			q.setString("vehicleId", vehicleId);
			
			List<VehicleDetails> list = q.list();
			
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
	public List<VehicleDetails> searchAvailableVehicleDetails(Date fromDate, Date toDate)
	{
		
		try {
			
			
			StringBuilder query = new StringBuilder();
			query.append("select vehicleDetails from ").append(VehicleDetails.class.getName()).append(" vehicleDetails");
			
			if(fromDate != null && toDate != null)
			{
				System.out.println("going inside");
				query.append(" where vehicleDetails.vehicleId not in( ");
				
				query.append("select bookingDetails.vehicleId from ").append(BookingDetails.class.getName()).append(" bookingDetails");
				
				query.append(" where bookingDetails.startDate <= :fromDate and bookingDetails.startDate >= :fromDate ");
				
				query.append(" and bookingDetails.endDate <= :toDate and bookingDetails.endDate >= :toDate )");
				
				
				
			}
			
			
			Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
			if(fromDate != null && toDate != null)
			{
				q.setDate("fromDate", fromDate);
				q.setDate("toDate", toDate);
			}
			List<VehicleDetails> list = q.list();
			
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


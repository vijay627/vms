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
import vin.vms.dao.entities.FuelDetails;
import vin.vms.dao.entities.FuelDetails;



@Repository("fuelDetailsDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class FuelDetailsDaoImpl extends VmsDao implements
FuelDetailsDao {


	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public FuelDetails saveFuelDetails(FuelDetails fuelDetails)
	{
		
		try {
			
			save(fuelDetails);
			
			System.out.println("Fuel Id ::"+fuelDetails.getFuelId());
			if(fuelDetails.getFuelId() == null)
			{
				StringBuilder query = new StringBuilder();
				query.append("select max(fuelId) from ").append(FuelDetails.class.getName()).append(" fuelDetails");

				Long  nextObjId = (Long) getSession().createQuery("SELECT  max(fuelId) FROM "+FuelDetails.class.getName()+" fuelDetails")
			    .setMaxResults(1).uniqueResult();
				
				//System.out.println("nextObjId :: "+nextObjId);
				
				fuelDetails.setFuelId(nextObjId);
				
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return fuelDetails;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public String deleteFuelDetails(FuelDetails fuelDetails)
	{
		
		try {
			
			remove(fuelDetails);
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
	public List<FuelDetails> readFuelDetails(Long fuelId)
	{
		
		try {
			
			if(fuelId !=null)
			{
				StringBuilder query = new StringBuilder();
				query.append("select fuelDetails from ").append(FuelDetails.class.getName()).append(" fuelDetails");
				query.append(" where fuelDetails.fuelId = :fuelId");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setLong("fuelId", fuelId);
				
				List<FuelDetails> list = q.list();
				
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
	public List<FuelDetails> searchFuelDetails(String vehicleId, Date fromDate, Date toDate)
	{
		
		try {
			
			
			StringBuilder query = new StringBuilder();
			query.append("select fuelDetails from ").append(FuelDetails.class.getName()).append(" fuelDetails");
			
			if(StringUtils.isNotEmpty(vehicleId) && fromDate == null && toDate == null)
			{
				query.append(" where fuelDetails.vehicleId = :vehicleId");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setString("vehicleId", vehicleId);
				
				List<FuelDetails> list = q.list();
				
				return list;
				
			}else if(StringUtils.isEmpty(vehicleId) && fromDate != null && toDate != null)
			{
				query.append(" where fuelDetails.fuelDate between :fromDate and :toDate ");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setDate("fromDate", fromDate);
				q.setDate("toDate", toDate);
				
				List<FuelDetails> list = q.list();
				
				return list;
			}else if(StringUtils.isNotEmpty(vehicleId) && fromDate != null && toDate != null)
			{
				query.append(" where fuelDetails.vehicleId = :vehicleId");
				query.append(" and fuelDetails.fuelDate between :fromDate and :toDate ");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setString("vehicleId", vehicleId);
				q.setDate("fromDate", fromDate);
				q.setDate("toDate", toDate);
				
				List<FuelDetails> list = q.list();
				
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


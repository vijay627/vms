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
import vin.vms.dao.entities.MaintenanceDetails;
import vin.vms.dao.entities.MaintenanceDetails;



@Repository("maintenanceDetailsDao")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
public class MaintenanceDetailsDaoImpl extends VmsDao implements
MaintenanceDetailsDao {


	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public MaintenanceDetails saveMaintenanceDetails(MaintenanceDetails maintenanceDetails)
	{
		
		try {
			
			save(maintenanceDetails);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return maintenanceDetails;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
	public String deleteMaintenanceDetails(MaintenanceDetails maintenanceDetails)
	{
		
		try {
			
			remove(maintenanceDetails);
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
	public List<MaintenanceDetails> readMaintenanceDetails(Long objId)
	{
		
		try {
			
			if(objId !=null)
			{
				StringBuilder query = new StringBuilder();
				query.append("select maintenanceDetails from ").append(MaintenanceDetails.class.getName()).append(" maintenanceDetails");
				query.append(" where maintenanceDetails.objId = :objId");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setLong("objId", objId);
				
				List<MaintenanceDetails> list = q.list();
				
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
	public List<MaintenanceDetails> searchMaintenanceDetails(String vehicleId, Date fromDate, Date toDate)
	{
		
		try {
			
			
			StringBuilder query = new StringBuilder();
			query.append("select maintenanceDetails from ").append(MaintenanceDetails.class.getName()).append(" maintenanceDetails");
			
			if(StringUtils.isNotEmpty(vehicleId) && fromDate == null && toDate == null)
			{
				query.append(" where maintenanceDetails.vehicleId = :vehicleId");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setString("vehicleId", vehicleId);
				
				List<MaintenanceDetails> list = q.list();
				
				return list;
				
			}else if(StringUtils.isEmpty(vehicleId) && fromDate != null && toDate != null)
			{
				query.append(" where maintenanceDetails.maintenanceDate between :fromDate and :toDate ");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setDate("fromDate", fromDate);
				q.setDate("toDate", toDate);
				
				List<MaintenanceDetails> list = q.list();
				
				return list;
			}else if(StringUtils.isNotEmpty(vehicleId) && fromDate != null && toDate != null)
			{
				query.append(" where maintenanceDetails.vehicleId = :vehicleId");
				query.append(" and maintenanceDetails.repairDate between :fromDate and :toDate ");
				
				Query q = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(query.toString());
				q.setString("vehicleId", vehicleId);
				q.setDate("fromDate", fromDate);
				q.setDate("toDate", toDate);
				
				List<MaintenanceDetails> list = q.list();
				
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


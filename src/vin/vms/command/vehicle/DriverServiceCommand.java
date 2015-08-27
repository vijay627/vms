package vin.vms.command.vehicle;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vin.vms.commons.CommandInterface;
import vin.vms.commons.pojo.Bean;
import vin.vms.commons.pojo.vehicle.DriverServiceBean;
import vin.vms.dao.entities.DriverDetails;
import vin.vms.dao.entities.DriverDetails;
import vin.vms.dao.vehicle.DriverDetailsDao;

// TODO: Auto-generated Javadoc
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DriverServiceCommand implements CommandInterface {

	
	private DriverDetailsDao driverDetailsDao;
	
	public DriverDetailsDao getDriverDetailsDao() {
		return driverDetailsDao;
	}

	public void setDriverDetailsDao(DriverDetailsDao driverDetailsDao) {
		this.driverDetailsDao = driverDetailsDao;
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
		DriverServiceBean bean = (DriverServiceBean)data;
		
		String function = bean.getFunction();
		
		DriverDetails driverDetails = new DriverDetails();
		
		try {
			
			
			BeanUtils.copyProperties(bean,driverDetails, new String[]{"expiryDate"});
			//PropertyUtils.copyProperties(driverDetails, bean);
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			if(StringUtils.isNotEmpty(bean.getExpiryDate()))
			{
				driverDetails.setExpiryDate(formatter.parse(bean.getExpiryDate()));
			}
			
			if("A".equals(function) || "U".equals(function))
			{
				driverDetails = (DriverDetails)driverDetailsDao.saveDriverDetails(driverDetails);
				
				//PropertyUtils.copyProperties(bean, driverDetails);
				
			}else if("D".equals(function))
			{
				driverDetailsDao.deleteDriverDetails(driverDetails);
				
				bean = new DriverServiceBean();
				
			}else if("R".equals(function))
			{
				System.out.println("Crit Id :: "+bean.getDriverIdCrit());
				List<DriverDetails> resut= (List<DriverDetails>)driverDetailsDao.readDriverDetails(bean.getDriverIdCrit());
				
				System.out.println("result :: "+resut.size());
				if(resut !=null && resut.size()>0)
				{
					driverDetails = resut.get(0);
					
					BeanUtils.copyProperties(driverDetails,bean, new String[]{"function","expiryDate"});
					
					if(driverDetails.getExpiryDate() !=null)
					{
						bean.setExpiryDate(String.valueOf(formatter.format(driverDetails.getExpiryDate())));
					}
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return bean;
	}

	

	
	

	
}

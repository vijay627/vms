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
import vin.vms.commons.pojo.vehicle.MaintenanceServiceBean;
import vin.vms.dao.entities.MaintenanceDetails;
import vin.vms.dao.vehicle.MaintenanceDetailsDao;

// TODO: Auto-generated Javadoc
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MaintenanceServiceCommand implements CommandInterface {

	
	private MaintenanceDetailsDao maintenanceDetailsDao;
	
	public MaintenanceDetailsDao getMaintenanceDetailsDao() {
		return maintenanceDetailsDao;
	}

	public void setMaintenanceDetailsDao(MaintenanceDetailsDao maintenanceDetailsDao) {
		this.maintenanceDetailsDao = maintenanceDetailsDao;
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
		MaintenanceServiceBean bean = (MaintenanceServiceBean)data;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String function = bean.getFunction();
		
		MaintenanceDetails maintenanceDetails = new MaintenanceDetails();
		
		try {
			
			//PropertyUtils.copyProperties(maintenanceDetails, bean);
			
			BeanUtils.copyProperties(bean,maintenanceDetails, new String[]{"repairDate"});
			
			
			if(StringUtils.isNotEmpty(bean.getRepairDate()))
			{
				maintenanceDetails.setRepairDate(formatter.parse(bean.getRepairDate()));
			}
			
			if("A".equals(function) || "U".equals(function))
			{
				maintenanceDetails = (MaintenanceDetails)maintenanceDetailsDao.saveMaintenanceDetails(maintenanceDetails);
				//PropertyUtils.copyProperties(bean, maintenanceDetails);
				
				
				BeanUtils.copyProperties(maintenanceDetails,bean, new String[]{"function","repairDate"});
				
				
				
				bean.setObjId(maintenanceDetails.getObjId());
				
				System.out.println("Obj Id ::"+bean.getObjId());
				
			}else if("D".equals(function))
			{
				maintenanceDetailsDao.deleteMaintenanceDetails(maintenanceDetails);
				
			}else if("R".equals(function))
			{
				List<MaintenanceDetails> resut= (List<MaintenanceDetails>)maintenanceDetailsDao.readMaintenanceDetails(bean.getObjId());
				
				if(resut !=null && resut.size()>0)
				{
					maintenanceDetails = resut.get(0);
					
					BeanUtils.copyProperties(maintenanceDetails,bean, new String[]{"function","repairDate"});
					
					if(maintenanceDetails.getRepairDate() !=null)
					{
						bean.setRepairDate(String.valueOf(formatter.format(maintenanceDetails.getRepairDate())));
					}
					
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return bean;
	}

	

	
	

	
}

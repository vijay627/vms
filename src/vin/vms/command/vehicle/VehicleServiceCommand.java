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
import vin.vms.commons.pojo.vehicle.VehicleServiceBean;
import vin.vms.dao.entities.VehicleDetails;
import vin.vms.dao.entities.VehicleDetails;
import vin.vms.dao.vehicle.VehicleDetailsDao;

// TODO: Auto-generated Javadoc
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VehicleServiceCommand implements CommandInterface {

	
	private VehicleDetailsDao vehicleDetailsDao;
	
	public VehicleDetailsDao getVehicleDetailsDao() {
		return vehicleDetailsDao;
	}

	public void setVehicleDetailsDao(VehicleDetailsDao vehicleDetailsDao) {
		this.vehicleDetailsDao = vehicleDetailsDao;
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
		VehicleServiceBean bean = (VehicleServiceBean)data;
		
		String function = bean.getFunction();
		
		VehicleDetails vehicleDetails = new VehicleDetails();
		
		try {
			
		
			
			
			BeanUtils.copyProperties(bean,vehicleDetails , new String[]{"dateOfInsurance","dateOfExpiry"});
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			if(StringUtils.isNotEmpty(bean.getDateOfInsurance()))
			{
				vehicleDetails.setDateOfInsurance(formatter.parse(bean.getDateOfInsurance()));
			}
			
			if(StringUtils.isNotEmpty(bean.getDateOfExpiry()))
			{
				vehicleDetails.setDateOfExpiry(formatter.parse(bean.getDateOfExpiry()));
			}
			
			if("A".equals(function) || "U".equals(function))
			{
				System.out.println("Hitting Command");
				System.out.println("Vehicle Id :: "+vehicleDetails.getVehicleId());
				System.out.println("Vehicle Name :: "+vehicleDetails.getVehicleName());
				vehicleDetails = (VehicleDetails)vehicleDetailsDao.saveVehicleDetails(vehicleDetails);
				
			}else if("D".equals(function))
			{
				vehicleDetailsDao.deleteVehicleDetails(vehicleDetails);
				
			}else if("R".equals(function))
			{
				
				List<VehicleDetails> resut= (List<VehicleDetails>)vehicleDetailsDao.readVehicleDetails(bean.getVehicleIdCrit());
				
				
				if(resut !=null && resut.size()>0)
				{
					vehicleDetails = resut.get(0);
					
					
					
					BeanUtils.copyProperties(vehicleDetails, bean, new String[]{"function","dateOfInsurance","dateOfExpiry"});
					
					if(vehicleDetails.getDateOfInsurance() !=null)
					{
						bean.setDateOfInsurance(String.valueOf(formatter.format(vehicleDetails.getDateOfInsurance())));
					}
					
					if(vehicleDetails.getDateOfExpiry() !=null)
					{
						bean.setDateOfExpiry(String.valueOf(formatter.format(vehicleDetails.getDateOfExpiry())));
					}
					
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return bean;
	}

	

	
	

	
}

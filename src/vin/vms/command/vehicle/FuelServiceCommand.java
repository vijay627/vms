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
import vin.vms.commons.pojo.vehicle.FuelServiceBean;
import vin.vms.dao.entities.FuelDetails;
import vin.vms.dao.vehicle.FuelDetailsDao;

// TODO: Auto-generated Javadoc
@SuppressWarnings("unchecked")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FuelServiceCommand implements CommandInterface {

	
	private FuelDetailsDao fuelDetailsDao;
	
	public FuelDetailsDao getFuelDetailsDao() {
		return fuelDetailsDao;
	}

	public void setFuelDetailsDao(FuelDetailsDao fuelDetailsDao) {
		this.fuelDetailsDao = fuelDetailsDao;
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
		FuelServiceBean bean = (FuelServiceBean)data;
		
		String function = bean.getFunction();
		
		FuelDetails fuelDetails = new FuelDetails();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			
			//PropertyUtils.copyProperties(fuelDetails, bean);
			
			BeanUtils.copyProperties(bean,fuelDetails, new String[]{"fuelDate"});
			
			
			if(StringUtils.isNotEmpty(bean.getFuelDate()))
			{
				fuelDetails.setFuelDate(formatter.parse(bean.getFuelDate()));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if("A".equals(function) || "U".equals(function))
		{
			fuelDetails = (FuelDetails)fuelDetailsDao.saveFuelDetails(fuelDetails);
			
			//PropertyUtils.copyProperties(bean, fuelDetails);
			
			BeanUtils.copyProperties(fuelDetails,bean, new String[]{"function","fuelDate"});
			
		}else if("D".equals(function))
		{
			fuelDetailsDao.deleteFuelDetails(fuelDetails);
			
		}else if("R".equals(function))
		{
			List<FuelDetails> resut= (List<FuelDetails>)fuelDetailsDao.readFuelDetails(bean.getFuelId());
			
			if(resut !=null && resut.size()>0)
			{
				fuelDetails = resut.get(0);
				
				BeanUtils.copyProperties(fuelDetails,bean, new String[]{"function","fuelDate"});
				
				if(fuelDetails.getFuelDate() !=null)
				{
					bean.setFuelDate(String.valueOf(formatter.format(fuelDetails.getFuelDate())));
				}
				
				
				
			}
			
		}
		
		return bean;
	}

	

	
	

	
}

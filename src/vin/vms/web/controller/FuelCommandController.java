package vin.vms.web.controller;


import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import vin.vms.commons.exception.VmsException;
import vin.vms.commons.pojo.vehicle.FuelServiceBean;
import vin.vms.web.command.vehicle.FuelCommand;



// TODO: Auto-generated Javadoc
/**
 * The Class PrintManagementController.
 */
@SuppressWarnings("unchecked")
public class FuelCommandController extends AbstractVmsCommandController {
 	
	 

	public ModelAndView handleInit(HttpServletRequest request,
			HttpServletResponse response, Object inputCommand, BindException be)
			throws Throwable {
		FuelCommand fuelCommand = (FuelCommand) inputCommand;
		
		
		
		return  doNormal(fuelCommand, request, be, response);
		
		
	}
	
	
	/**
	 * Do normal.
	 * 
	 * @param printManagementCommand the Print Management command
	 * @param req the req
	 * @param drawing the drawing
	 * @param be the be
	 * @param response the response
	 * 
	 * @return the model and view
	 * 
	 */
	private ModelAndView doNormal(
			FuelCommand fuelCommand,
			HttpServletRequest req, BindException be, HttpServletResponse response) throws VmsException,  InvocationTargetException, Throwable  {
		ModelAndView modelAndView = new ModelAndView(this.getInputFormView());
		
		String strBtnPressed = (String)req.getParameter("btnPressed");
		String function = fuelCommand.getFunction();
		
		req.setAttribute("fuelFunction", function);
		
		if(StringUtils.isNotEmpty(strBtnPressed))
		{
			if("C".equals(strBtnPressed))
			{
				fuelCommand.setFunction("R");
			}
			FuelServiceBean fuelServiceBean = new FuelServiceBean();
			
			try {
				PropertyUtils.copyProperties(fuelServiceBean, fuelCommand);
				
				fuelServiceBean = (FuelServiceBean) getCommandExecutor().execute(fuelServiceBean);
				
				
				PropertyUtils.copyProperties(fuelCommand, fuelServiceBean);
				
				//System.out.println("from service : "+fuelServiceBean.getFuelId());
				//System.out.println("from command : "+fuelCommand.getFuelId());
				if("C".equals(strBtnPressed))
				{
					fuelCommand.setFunction(function);
				}else if("D".equals(strBtnPressed))
				{
					String operation = "";
					if("A".equals(function))
					{
						operation = "added";
					}else if("U".equals(function))
					{
						operation = "updated";
					}else if("D".equals(function))
					{
						operation = "deleted";
					}
					
					
					String successMsg = "Fuel details has been successfully "+operation+" for Vehicle ID : "+fuelCommand.getVehicleId()+" with Fuel Id : "+fuelServiceBean.getFuelId();
					String nextPage  = "fuel.spr";
					
					req.setAttribute("successMsg", successMsg);
					req.setAttribute("nextPage", nextPage);
					
					//System.out.println("view :: "+this.getSuccessView());
					modelAndView = new ModelAndView(this.getSuccessView());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
			
		}	
		
		
		modelAndView.addObject("fuelCommand",	fuelCommand);	
		
		return modelAndView;
	}

	
	
	public ModelAndView handleValidForm(HttpServletRequest req,
			HttpServletResponse res, Object inputCommand, BindException be)
			throws Throwable {
		return handleInit(req, res, inputCommand, be);
	}
	

	
}

package vin.vms.web.controller;


import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import vin.vms.commons.exception.VmsException;
import vin.vms.commons.pojo.vehicle.VehicleServiceBean;
import vin.vms.web.command.vehicle.VehicleCommand;



// TODO: Auto-generated Javadoc
/**
 * The Class PrintManagementController.
 */

public class VehicleCommandController extends AbstractVmsCommandController {
 	
	 

	public ModelAndView handleInit(HttpServletRequest request,
			HttpServletResponse response, Object inputCommand, BindException be)
			throws Throwable {
		VehicleCommand vehicleCommand = (VehicleCommand) inputCommand;
		
		
		
		
		return  doNormal(vehicleCommand, request, be, response);
		
		
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
			VehicleCommand vehicleCommand,
			HttpServletRequest req, BindException be, HttpServletResponse response) throws VmsException,  InvocationTargetException, Throwable  {
		ModelAndView modelAndView = null;
		
		modelAndView = new ModelAndView(this.getInputFormView());
		String strBtnPressed = (String)req.getParameter("btnPressed");
		String function = vehicleCommand.getFunction();
		
		req.setAttribute("vehicleFunction", function);
		
		if(StringUtils.isNotEmpty(strBtnPressed) )
		{
			if("C".equals(strBtnPressed))
			{
				vehicleCommand.setFunction("R");
			}
			
			VehicleServiceBean vehicleServiceBean = new VehicleServiceBean();
			
			try {
				PropertyUtils.copyProperties(vehicleServiceBean, vehicleCommand);
				
				//System.out.println("Hitting Controller");
				
				System.out.println("Id "+vehicleServiceBean.getVehicleIdCrit());
				
				
				vehicleServiceBean = (VehicleServiceBean) getCommandExecutor().execute(vehicleServiceBean);
				
				PropertyUtils.copyProperties(vehicleCommand, vehicleServiceBean);
				
				if("C".equals(strBtnPressed))
				{
					vehicleCommand.setFunction(function);
				}else if("D".equals(strBtnPressed))
				{
					String operation = "";
					if("A".equals(function))
					{
						operation = "created";
					}else if("U".equals(function))
					{
						operation = "updated";
					}else if("D".equals(function))
					{
						operation = "deleted";
					}
					
					
					String successMsg = "Vehicle details has been successfully "+operation+" for Vehicle ID : "+vehicleCommand.getVehicleId();
					String nextPage  = "vehicle.spr";
					
					req.setAttribute("successMsg", successMsg);
					req.setAttribute("nextPage", nextPage);
					modelAndView = new ModelAndView(this.getSuccessView());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		modelAndView.addObject("vehicleCommand",	vehicleCommand);	
		
		
		return modelAndView;
	}

	
	
	public ModelAndView handleValidForm(HttpServletRequest req,
			HttpServletResponse res, Object inputCommand, BindException be)
			throws Throwable {
		return handleInit(req, res, inputCommand, be);
	}
	

	
}

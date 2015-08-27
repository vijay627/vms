package vin.vms.web.controller;


import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import vin.vms.commons.exception.VmsException;
import vin.vms.commons.pojo.vehicle.DriverServiceBean;
import vin.vms.web.command.vehicle.DriverCommand;
import vin.vms.web.controller.AbstractVmsCommandController;



// TODO: Auto-generated Javadoc
/**
 * The Class PrintManagementController.
 */
@SuppressWarnings("unchecked")
public class DriverCommandController extends AbstractVmsCommandController {
 	
	 

	public ModelAndView handleInit(HttpServletRequest request,
			HttpServletResponse response, Object inputCommand, BindException be)
			throws Throwable {
		DriverCommand driverCommand = (DriverCommand) inputCommand;
		
		
		
		return  doNormal(driverCommand, request, be, response);
		
		
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
			DriverCommand driverCommand,
			HttpServletRequest req, BindException be, HttpServletResponse response) throws VmsException,  InvocationTargetException, Throwable  {
		ModelAndView modelAndView =  new ModelAndView(this.getInputFormView());;
		
		String strBtnPressed = (String)req.getParameter("btnPressed");
		String function = driverCommand.getFunction();
		
		
		req.setAttribute("driverFunction", function);
		
		if(StringUtils.isNotEmpty(strBtnPressed))
		{
			if("C".equals(strBtnPressed))
			{
				driverCommand.setFunction("R");
			}
			DriverServiceBean driverServiceBean = new DriverServiceBean();
			
			try {
				PropertyUtils.copyProperties(driverServiceBean, driverCommand);
				
				
				
				driverServiceBean = (DriverServiceBean) getCommandExecutor().execute(driverServiceBean);
				
				PropertyUtils.copyProperties(driverCommand, driverServiceBean);
				
				if("C".equals(strBtnPressed))
				{
					driverCommand.setFunction(function);
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
					
					
					String successMsg = "Driver details has been successfully "+operation+" for Driver ID : "+driverCommand.getDriverId();
					String nextPage  = "driver.spr";
					
					req.setAttribute("successMsg", successMsg);
					req.setAttribute("nextPage", nextPage);
					modelAndView = new ModelAndView(this.getSuccessView());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		}	
		
		
		
		modelAndView.addObject("driverCommand",	driverCommand);
		
		
		return modelAndView;
	}

	
	
	public ModelAndView handleValidForm(HttpServletRequest req,
			HttpServletResponse res, Object inputCommand, BindException be)
			throws Throwable {
		return handleInit(req, res, inputCommand, be);
	}
	

	
}

package vin.vms.web.controller;


import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import vin.vms.commons.exception.VmsException;
import vin.vms.commons.pojo.vehicle.MaintenanceServiceBean;
import vin.vms.commons.pojo.vehicle.MaintenanceServiceBean;
import vin.vms.web.command.vehicle.MaintenanceCommand;
import vin.vms.web.controller.AbstractVmsCommandController;



// TODO: Auto-generated Javadoc
/**
 * The Class PrintManagementController.
 */
@SuppressWarnings("unchecked")
public class MaintenanceCommandController extends AbstractVmsCommandController {
 	
	 

	public ModelAndView handleInit(HttpServletRequest request,
			HttpServletResponse response, Object inputCommand, BindException be)
			throws Throwable {
		MaintenanceCommand maintenanceCommand = (MaintenanceCommand) inputCommand;
		
		
		
		return  doNormal(maintenanceCommand, request, be, response);
		
		
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
			MaintenanceCommand maintenanceCommand,
			HttpServletRequest req, BindException be, HttpServletResponse response) throws VmsException,  InvocationTargetException, Throwable  {
		
		ModelAndView modelAndView = new ModelAndView(this.getInputFormView());
		
		String strBtnPressed = (String)req.getParameter("btnPressed");
		String function = maintenanceCommand.getFunction();
		
		req.setAttribute("maintainFunction", function);
		
		if(StringUtils.isNotEmpty(strBtnPressed))
		{
			if("C".equals(strBtnPressed))
			{
				maintenanceCommand.setFunction("R");
			}
			MaintenanceServiceBean maintenanceServiceBean = new MaintenanceServiceBean();
			
			try {
				PropertyUtils.copyProperties(maintenanceServiceBean, maintenanceCommand);
				
				maintenanceServiceBean = (MaintenanceServiceBean) getCommandExecutor().execute(maintenanceServiceBean);
				
				//PropertyUtils.copyProperties(maintenanceCommand,maintenanceServiceBean);
				
				System.out.println("obj id on contr :: "+maintenanceServiceBean.getObjId());
				
				if("C".equals(strBtnPressed))
				{
					maintenanceCommand.setFunction(function);
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
					
					
					String successMsg = "Fuel details has been successfully "+operation+" for Vehicle ID : "+maintenanceServiceBean.getVehicleId()+" with Repair Id : "+maintenanceServiceBean.getObjId();
					String nextPage  = "maintenance.spr";
					
					req.setAttribute("successMsg", successMsg);
					req.setAttribute("nextPage", nextPage);
					
					//System.out.println("view :: "+this.getSuccessView());
					modelAndView = new ModelAndView(this.getSuccessView());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			
		}	
		
		
		modelAndView.addObject("maintenanceCommand",	maintenanceCommand);	
		return modelAndView;
	}

	
	
	public ModelAndView handleValidForm(HttpServletRequest req,
			HttpServletResponse res, Object inputCommand, BindException be)
			throws Throwable {
		return handleInit(req, res, inputCommand, be);
	}
	

	
}

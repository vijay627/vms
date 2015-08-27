package vin.vms.web.controller;


import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import vin.vms.commons.exception.VmsException;
import vin.vms.commons.pojo.vehicle.BookingServiceBean;
import vin.vms.web.command.vehicle.BookingCommand;
import vin.vms.web.controller.AbstractVmsCommandController;



// TODO: Auto-generated Javadoc
/**
 * The Class PrintManagementController.
 */
@SuppressWarnings("unchecked")
public class BookingCommandController extends AbstractVmsCommandController {
 	
	 

	public ModelAndView handleInit(HttpServletRequest request,
			HttpServletResponse response, Object inputCommand, BindException be)
			throws Throwable {
		BookingCommand bookingCommand = (BookingCommand) inputCommand;
		
		
		
		return  doNormal(bookingCommand, request, be, response);
		
		
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
			BookingCommand bookingCommand,
			HttpServletRequest req, BindException be, HttpServletResponse response) throws VmsException,  InvocationTargetException, Throwable  {
		ModelAndView modelAndView = new ModelAndView(this.getInputFormView());
		
		
		String strBtnPressed = (String)req.getParameter("btnPressed");
		String function = bookingCommand.getFunction();
		
		req.setAttribute("bookingFunction", function);
		
		if(StringUtils.isNotEmpty(strBtnPressed))
		{
			if("C".equals(strBtnPressed))
			{
				bookingCommand.setFunction("R");
			}
			BookingServiceBean bookingServiceBean = new BookingServiceBean();
			
			try {
				PropertyUtils.copyProperties(bookingServiceBean, bookingCommand);
				
				bookingServiceBean = (BookingServiceBean) getCommandExecutor().execute(bookingServiceBean);
				
				PropertyUtils.copyProperties(bookingCommand, bookingServiceBean);
				
				if("C".equals(strBtnPressed))
				{
					bookingCommand.setFunction(function);
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
					
					
					String successMsg = "Booking details has been successfully "+operation+" for Vehicle ID : "+bookingCommand.getVehicleId()+" with Booking Id : "+bookingCommand.getBookingId();
					String nextPage  = "booking.spr";
					
					req.setAttribute("successMsg", successMsg);
					req.setAttribute("nextPage", nextPage);
					
					//System.out.println("view :: "+this.getSuccessView());
					modelAndView = new ModelAndView(this.getSuccessView());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
				
		}	
		
		
		modelAndView.addObject("bookingCommand",	bookingCommand);
		
		return modelAndView;
	}

	
	
	public ModelAndView handleValidForm(HttpServletRequest req,
			HttpServletResponse res, Object inputCommand, BindException be)
			throws Throwable {
		return handleInit(req, res, inputCommand, be);
	}
	

	
}

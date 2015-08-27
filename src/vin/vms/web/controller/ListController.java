package vin.vms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// TODO: Auto-generated Javadoc
/**
 * Delegates handling to the first controller in a list that returns a not null modelAndView.
 */
public class ListController implements Controller {
	
	/** The controllers. */
	private List<Controller> controllers;
	
	/** The Constant log. */
	private static final Logger log = Logger.getLogger(ListController.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = null;
		for (Controller controller : controllers) {	
			modelAndView = controller.handleRequest(request, response);
			if(modelAndView != null){
				break;
			}
		}
		
		if(modelAndView == null){
			log.warn("None of the provided controllers was able to return a ModelAndView.");
		}
		
		return modelAndView;
	}

	/**
	 * Gets the controllers.
	 * 
	 * @return the controllers
	 */
	public List<Controller> getControllers() {
		return controllers;
	}

	/**
	 * Sets the controllers.
	 * 
	 * @param controllers the new controllers
	 */
	@Required
	public void setControllers(List<Controller> controllers) {
		this.controllers = controllers;
	}

}


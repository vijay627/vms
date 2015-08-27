package vin.vms.commons;


import java.io.Serializable;

import vin.vms.commons.exception.VmsException;



// TODO: Auto-generated Javadoc
/**
 * Interface for the command executor.
 * 
 * @author id813872
 */
public interface CommandExecutorInterface {
	
	/**
	 * Execute.
	 * 
	 * @param commandData the command data
	 * 
	 * @return the serializable
	 * 
	 * @throws GenException the gen exception
	 */
	Serializable execute(Serializable commandData) throws VmsException;
}

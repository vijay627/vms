package vin.vms.commons;

import java.io.Serializable;

import vin.vms.commons.pojo.Bean;

// TODO: Auto-generated Javadoc
/**
 * The Interface CommandInterface.
 */
public interface CommandInterface {

	 /**
 	 * Execute.
 	 * 
 	 * @param data the data
 	 * 
 	 * @return the serializable
 	 * 
 	 * @throws Throwable the throwable
 	 */
 	Serializable execute(Bean data) throws Throwable;
}

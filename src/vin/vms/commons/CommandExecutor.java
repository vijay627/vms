package vin.vms.commons;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import vin.vms.commons.exception.VmsException;
import vin.vms.commons.exception.VmsSystemException;
import vin.vms.commons.pojo.Bean;




/**
 * Simple but complete local implementation of the command pattern.
 * It executes the command in the same environment of the caller.<br />
 * <b>NOTE:</b> The caller should provide the bean of its spring context
 * before calling this executor.
 * 
 * @author id813872
 *
 */
public class CommandExecutor implements CommandExecutorInterface, BeanFactoryAware {
	
	private static String FDT_PACKAGE = "vin.vms.commons.pojo.";
	private static String BEAN_SUFFIX = "Bean";
	private static String COMMAND_SUFFIX = "Command";
	private static String EXECUTE_METHOD = "execute";
	
	private BeanFactory factory;
	
	private static final Logger LOGGER = Logger.getLogger(CommandExecutor.class.getName());
	
	/**
	 * Actually executes the received command
	 * @throws VmsException 
	 */
	public Serializable execute(Serializable commandData) throws VmsException {
		String fdtFQN = commandData.getClass().getName();

		String commandId = getCommandId(fdtFQN);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("commandId: " + commandId);
		}
		
		Serializable returnValue = null;
		
		Method executeMethod = null;
		try {
			System.out.println("commandId :: "+commandId);
			CommandInterface command = (CommandInterface)factory.getBean(commandId);
			
			if(command == null){
				throw new VmsException(
						"The look up of a bean called [" + commandId + "] has failed. Please try to configure it inside Spring configuration files.", 
						"nocommandavailable");
			}
			
			executeMethod = command.getClass().getMethod(EXECUTE_METHOD, Bean.class);
			returnValue = (Serializable)executeMethod.invoke(command, commandData);
		} catch (InvocationTargetException e) {
			/* Every Exception fired by the invoked method, checked or unchecked, is wrapped
			 * into this exception. As such, any runtime exception fired by the Hibernate classes is
			 * wrapped inside this exception and stops at this level if it isn't thrown to the above layer  
			 */
			LOGGER.error(e.getTargetException().getMessage(), e.getTargetException());
			if (e.getTargetException() instanceof VmsException) {
				throw (VmsException) e.getTargetException();
			}
			throw new VmsSystemException(e.getTargetException(), "system.generic");
			
		} catch (Throwable e) {
			LOGGER.error(e.getMessage(), e);
			throw new VmsSystemException(e, "system.generic");
		}
		return returnValue;
	}

	private String getCommandId(String fdtFQN) {
		LOGGER.debug("Generating commandId for f.q.n. " + fdtFQN);
		String fdtName = fdtFQN.substring(FDT_PACKAGE.length());

		String commandName = fdtName.substring(0, fdtName.indexOf(BEAN_SUFFIX));

		String commandId = commandName + COMMAND_SUFFIX;
		
		return commandId;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.factory = beanFactory;
	}
}


package vin.monitoring.core.performance;

//*****************************************************************************
//$Date: 2009/01/18 $
//$Author:ID803500 (Marc Thomas) $ 
//$Revision: 1.0 $ 
//*****************************************************************************
//GENERATED INCLUDES
//*****************************************************************************
import java.lang.reflect.Method;
import java.text.MessageFormat;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
/******************************************************************************
* A proxy for intercepting invocations of methods that needs to be monitored
* for performances. The proxy needs to be declared explicitly for methods that 
* needs to be monitored (see BeanNameAutoProxyCreator).
* Statistics are registered through performance clocks onto the performance 
* context. 
******************************************************************************/
public class PerformanceInterceptor implements MethodInterceptor
{
	//***************************************************************************
	// PUBLIC CONSTANTS
	//***************************************************************************
	//
	//***************************************************************************
	// PRIVATE CONSTANTS
	//***************************************************************************
	//
	//***************************************************************************
	// PUBLIC PROPERTIES
	//***************************************************************************
	//
	//***************************************************************************
	// PRIVATE PROPERTIES
	//***************************************************************************
	private static Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);
	private PerformanceContext performanceContext;
	//***************************************************************************
	// CONSTRUCTOR METHODS
	/*****************************************************************************
	 * Default constructor.
	 ****************************************************************************/
	public PerformanceInterceptor()
	{
	}
	//***************************************************************************
	// PUBLIC METHODS
	/****************************************************************************
	* Intercept the invocation of a method and register its performance into the
	* performance context. 
	***************************************************************************/
	public Object invoke(MethodInvocation joinpoint) throws Throwable
	{
		Integer performanceClockId = -1;
		// Extract the identification of the target class and method.
		Class proxyClass = joinpoint.getThis().getClass();
		String classAlias = proxyClass.getSimpleName();
		Method proxyMethod = joinpoint.getMethod();
		String methodAlias = proxyMethod.getName();   
		// Intercept the invocation of a method and register its performance.
		try
		{
			// Evaluate if the target class is a proxy.
			Boolean isProxy = AopUtils.isAopProxy(joinpoint.getThis());
			// In case of a proxy, extract the veritable target class.
			if (isProxy == true)
			{
			    proxyClass = proxyMethod.getDeclaringClass();
			    classAlias = proxyClass.getSimpleName();
			}
			// Create a performance clock for clocking the method.
			logger.info("ENTER {}.{}()", proxyClass.getName(), methodAlias);
			String method = MessageFormat.format("{0}.{1}()", classAlias, methodAlias);
			performanceClockId = performanceContext.startClock(method);
			// Invoke the target method encapsulated by the proxy.
			Object outcome = joinpoint.proceed();
			return (outcome);
		}
		finally
		{
			// Stop the performance clock associated to the interceptor.
			logger.info("EXIT {}.{}()", proxyClass.getName(), methodAlias);
			performanceContext.stopClock(performanceClockId);
		}
	}
	//***************************************************************************
	// GETTER/SETTER METHODS
	//***************************************************************************
	public PerformanceContext getPerformanceContext()
	{
		return performanceContext;
	}
	public void setPerformanceContext(PerformanceContext performanceContext)
	{
		this.performanceContext = performanceContext;
	}
}


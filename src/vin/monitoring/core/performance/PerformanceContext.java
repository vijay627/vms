package vin.monitoring.core.performance;


//*****************************************************************************
//$Date: 27/01/2009
//$Author: ID803500 (Marc Thomas) 
//$Revision: 1.0 
//*****************************************************************************
//GENERATED INCLUDES
//*****************************************************************************
/******************************************************************************
* A thread context to manage performance clocks associated to a client request. 
******************************************************************************/
public class PerformanceContext
{
	//***************************************************************************
	// PUBLIC CONSTANTS
	//***************************************************************************
	//
	//***************************************************************************
	// PRIVATE PROPERTIES
	//***************************************************************************
	private static final  ThreadLocal<PerformanceSummary> THREAD_SLOT = new ThreadLocal<PerformanceSummary>();
	//***************************************************************************
	// PUBLIC METHODS
	/****************************************************************************
	* Start a performance clock for clocking a well-defined business task during 
	* the processing of the client request. 
	* 
	* @param clazz the identification of the class running the business task
	* @param performanceTaskId the identification of the business task to clock
	* @return the index of the performance clock
	****************************************************************************/
	public Integer startClock(Class clazz, String performanceTaskId)
	{
		PerformanceSummary performanceSummary = provideContext();
		Integer performanceClockIndex = performanceSummary.start(clazz, performanceTaskId);
		return (performanceClockIndex);
	}
	/****************************************************************************
	* Start a performance clock for clocking a well-defined business task during 
	* the processing of the client request. 
	* 
	* @param performanceTaskId the identification of the business task to clock
	* @return the index of the performance clock
	****************************************************************************/
	public Integer startClock(String performanceTaskId)
	{
		PerformanceSummary performanceSummary = provideContext();
		Integer performanceClockIndex = performanceSummary.start(performanceTaskId);
		return (performanceClockIndex);
	}
	/****************************************************************************
	* Stop a well-defined performance clock that was used for clocking a business 
	* task during the processing of the client request. Intermidiate performance
	* clocks are also stopped.
	* 
	* @param performanceClockIndex the index of the performance clock to stop
	* @return the performance clock in milli-seconds
	****************************************************************************/
	public Long stopClock(Integer performanceClockIndex)
	{
		PerformanceSummary performanceSummary = provideContext();
		Long performanceValue = performanceSummary.stop(performanceClockIndex);
		return (performanceValue);
	}
	/****************************************************************************
	* Stop the most recent performance clock that was used for clocking a 
	* business task during the processing  of the client request. 
	* 
	* @return the performance clock in milli-seconds
	****************************************************************************/
	public Long stopClock()
	{
		PerformanceSummary performanceSummary = provideContext();
		Long performanceValue = performanceSummary.stop();
		return (performanceValue);
	}
	/****************************************************************************
	* Retrieve the performance clocks from the performance context. 
	* 
	* @return the performance context for the thread
	****************************************************************************/
	public PerformanceSummary getContext()
	{
		PerformanceSummary performanceSummary = THREAD_SLOT.get();
		return (performanceSummary);
	}
	/****************************************************************************
	* Create a new performance context on the thread for clocking the processing 
	* of one client request. 
	****************************************************************************/
	public PerformanceSummary resetContext()
	{
		PerformanceSummary performanceSummary = new PerformanceSummary();
		THREAD_SLOT.set(performanceSummary);
		return(performanceSummary);
	}	
	/****************************************************************************
	* Provide the performance context from the thread or create one for clocking 
	* the processing of one client request. 
	****************************************************************************/
	public PerformanceSummary provideContext()
	{
		PerformanceSummary performanceSummary = THREAD_SLOT.get();
		if (performanceSummary == null) {
			performanceSummary = resetContext();
		}
		return(performanceSummary);
	}	
	/****************************************************************************
	* Cleanup the performance clocks from the performance context. 
	****************************************************************************/
	public PerformanceSummary cleanupContext()
	{
		PerformanceSummary performanceSummary = provideContext();
		THREAD_SLOT.remove();
		return (performanceSummary);
	}
}
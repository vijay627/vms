package vin.monitoring.core.performance;


//*****************************************************************************
//$Date: 27/01/2009
//$Author: ID803500 (Marc Thomas) 
//$Revision: 1.0 
//*****************************************************************************
//GENERATED INCLUDES
//*****************************************************************************
import java.text.MessageFormat;

import org.springframework.util.StopWatch;
/******************************************************************************
* Implement a performance clock for clocking a well-defined business task.
******************************************************************************/
public class PerformanceClock
{
	//***************************************************************************
	// PUBLIC CONSTANTS
	//***************************************************************************
	//
	//***************************************************************************
	// PRIVATE PROPERTIES
	//***************************************************************************
	private StopWatch performanceClock;
	private Integer performanceLevel;
	private String performanceTask;
	//***************************************************************************
	// CONSTRUCTOR METHODS
	/****************************************************************************
	* Instantiate a performance clock for clocking a well-defined business task. 
	*
	* @param performanceTask the identification of the business task to clock
	* @param performanceLevel the invocation level of the performance clock
	****************************************************************************/
	public PerformanceClock(String performanceTask, Integer performanceLevel)
	{
		this.performanceTask = performanceTask;
		this.performanceLevel = performanceLevel;
		this.performanceClock = new StopWatch(performanceTask);
		performanceClock.setKeepTaskList(false);
	}
	//***************************************************************************
	// PUBLIC METHODS
	/****************************************************************************
	* Start the performance clock for clocking a well-defined business task. 
	****************************************************************************/
	public void start()
	{
		performanceClock.start();
	}
	/****************************************************************************
	* Stop the performance clock associated to a well-defined business task.
	* 
	* @return the performance clock in milli-second.
	****************************************************************************/
	public Long stop()
	{
		performanceClock.stop();
		Long performanceValue = performanceClock.getTotalTimeMillis();
		return (performanceValue);
	}
	/****************************************************************************
	* Encode a default text for the performance clock.
	* 
	* @return the default text representing the performance clock. 
	****************************************************************************/
	public String toString()
	{
		Object[] parameterList = { performanceTask, performanceClock.getTotalTimeMillis(), performanceLevel };
		String outputText = MessageFormat.format("{0}={1,number,0}|level={2}", parameterList);
		return(outputText);
	}
	//***************************************************************************
	// GETTER/SETTER METHODS
	//***************************************************************************
	public StopWatch getPerformanceClock()
	{
		return performanceClock;
	}
	public void setPerformanceClock(StopWatch performanceClock)
	{
		this.performanceClock = performanceClock;
	}
	public Integer getPerformanceLevel()
	{
		return performanceLevel;
	}
	public void setPerformanceLevel(Integer performanceLevel)
	{
		this.performanceLevel = performanceLevel;
	}
	public String getPerformanceTask()
	{
		return performanceTask;
	}
	public void setPerformanceTask(String performanceTask)
	{
		this.performanceTask = performanceTask;
	}
}

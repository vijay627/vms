package vin.monitoring.core.performance;


//*****************************************************************************
//$Date: 27/01/2009
//$Author: ID803500 (Marc Thomas) 
//$Revision: 1.0 
//*****************************************************************************
//GENERATED INCLUDES
//*****************************************************************************
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/******************************************************************************
* Implement a container for collecting performance clocks used for clocking
* business tasks during the processing of a client request.
******************************************************************************/
public class PerformanceSummary
{
	//***************************************************************************
	// PUBLIC CONSTANTS
	//***************************************************************************
	//
	//***************************************************************************
	// PRIVATE PROPERTIES
	//***************************************************************************
	private static final  Logger LOG = LoggerFactory.getLogger(PerformanceSummary.class);
	private Integer clockActiveLevel;
	private Integer clockActiveCount;
	private List<Integer> clockActiveList;
	private Integer clockValueCount;
	private List<PerformanceClock> clockValueList;
	//***************************************************************************
	// CONSTRUCTOR METHODS
	//***************************************************************************
	public PerformanceSummary()
	{
		clockActiveLevel = 0;
		clockActiveCount = 0;
		clockActiveList = new ArrayList<Integer>();
		clockValueCount = 0;
		clockValueList = new ArrayList<PerformanceClock>();
	}
	//***************************************************************************
	// PUBLIC METHODS
	/****************************************************************************
	* Create a performance clock and preserve it on the active list. 
	* 
	* @param clazz the identification of the class running the business task
	* @param performanceTaskId the identification of the business task to clock
	* @return the index of the performance clock
	****************************************************************************/
	public Integer start(Class clazz, String performanceTaskId)
	{
		// Include the class identification to the business task.
		String completeTaskId = MessageFormat.format("{0}#{1}", clazz.getSimpleName(), performanceTaskId);
		// Create and start a performance clock.
		Integer performanceClockIndex = start(completeTaskId);
		// Provide the index of the performance clock.
		return (performanceClockIndex);
	}
	/****************************************************************************
	* Create a performance clock and preserve it on the active list. 
	* 
	* @param performanceTaskId the identification of the business task to clock
	* @return the index of the performance clock
	****************************************************************************/
	public Integer start(String performanceTaskId)
	{
		// Create and start a performance clock.
		PerformanceClock performanceClock = new PerformanceClock(performanceTaskId, clockActiveLevel);
		performanceClock.start();
		// Preserve the performance clock into the value list.
		clockValueList.add(performanceClock);
		clockValueCount++;
		// Preserve the performance clock on the active list.
		Integer performanceClockIndex = Integer.valueOf(clockValueCount - 1);
		clockActiveList.add(performanceClockIndex);
		clockActiveLevel++;
		clockActiveCount++;
		// Provide the index of the performance clock.
		return (performanceClockIndex);
	}
	/****************************************************************************
	* Stop a well-defined performance clock. Intermediate performance clocks not
	* stopped yet are also stopped and removed from the the active list. 
	*
	* @param performanceClockIndex the index of the performance clock to stop
	* @return the performance clock in milli-seconds
	****************************************************************************/
	public Long stop(Integer performanceClockIndex)
	{
		Long performanceValue = -1L;
		try{
		
		// Lookup for the performance clock into the active list.
		int clockToStopIndex = clockActiveList.lastIndexOf(performanceClockIndex);
		// Evaluate if the performance is still active.
		if (clockToStopIndex > -1)
		{
			while (clockActiveCount > clockToStopIndex)
			{
				// Retrieve the performance clock from the active list. 
				int clockActiveIndex = clockActiveCount - 1;
				Integer clockValueIndex = clockActiveList.remove(clockActiveIndex);
				clockActiveLevel--;
				clockActiveCount--;
				// Stop the performance clock extracted from the active list.
				PerformanceClock performanceClock = (PerformanceClock) clockValueList.get(clockValueIndex);
				performanceValue = performanceClock.stop();
			}
		}
		}catch(Exception ex){
			
		}
		return (performanceValue);
	}
	/****************************************************************************
	* Stop the most recent performance clock and remove it from the the active 
	* list. 
	*
	* @return the performance clock in milli-seconds
	****************************************************************************/
	public Long stop()
	{
		Long performanceValue = -1L;
		// Evaluate if a least one performance clock is still active.
		if (clockActiveCount > 0)
		{
			// Retrieve the performance clock from the active list. 
			int clockActiveIndex = clockActiveCount - 1;
			Integer clockValueIndex = clockActiveList.remove(clockActiveIndex);
			clockActiveLevel--;
			clockActiveCount--;
			// Stop the performance clock extracted from the active list.
			PerformanceClock performanceClock = (PerformanceClock) clockValueList.get(clockValueIndex);
			performanceValue = performanceClock.stop();
		}
		return (performanceValue);
	}
	/****************************************************************************
	* Trace performance clocks that are registered into the container. 
	****************************************************************************/
	public void trace()
	{
		for (PerformanceClock performanceClock : clockValueList)
		{
			Integer performanceLevel = performanceClock.getPerformanceLevel();
			String performanceTask = performanceClock.getPerformanceTask();
			Long performanceValue = performanceClock.getPerformanceClock().getTotalTimeMillis();
			Object[] parameterList = { performanceTask, performanceValue, performanceLevel };
			LOG.debug("{}={} level={}", parameterList);
		}
	}
	//***************************************************************************
	// GETTER/SETTER METHODS
	//***************************************************************************
	public Integer getClockValueCount()
	{
		return clockValueCount;
	}
	public void setClockValueCount(Integer clockValueCount)
	{
		this.clockValueCount = clockValueCount;
	}
	public List<PerformanceClock> getClockValueList()
	{
		return clockValueList;
	}
	public void setClockValueList(List<PerformanceClock> clockValueList)
	{
		this.clockValueList = clockValueList;
	}
}
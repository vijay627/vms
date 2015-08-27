package vin.monitoring.core.hibernate;


import java.io.Serializable;

import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.ScopedBeanInterceptor;
import org.springframework.util.PatternMatchUtils;
//TODO: Auto-generated Javadoc

/**
* ****************************************************************************
* Implement an interceptor that reports operations performed on persistent
* objects of the database.
* ****************************************************************************
*/
public class DatabaseUpdateInterceptor extends ScopedBeanInterceptor
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
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(DatabaseUpdateInterceptor.class);
	
	/** The entity pattern list. */
	private String[] entityPatternList;
	
	/** The trace attribute on load. */
	private boolean traceAttributeOnLoad;
	
	/** The trace attribute on update. */
	private boolean traceAttributeOnUpdate;
	
	/** The trace attribute on save. */
	private boolean traceAttributeOnSave;
	
	/** The trace attribute on delete. */
	private boolean traceAttributeOnDelete;
	
	/** The trace sql statement. */
	private boolean traceSqlStatement;
	//***************************************************************************
	// CONSTRUCTOR METHODS
	/**
	 * **************************************************************************
	 * Default constructor.
	 * *************************************************************************
	 */
	public DatabaseUpdateInterceptor()
	{
	}
	//***************************************************************************
	// PUBLIC METHODS
	/**
	 * **************************************************************************
	 * Intercept the start of a transaction on the database.
	 * *************************************************************************
	 * 
	 * @param transaction the transaction
	 */
	public void afterTransactionBegin(Transaction transaction)
	{
		logger.debug("Transaction|START");
	}
	
	/**
	 * **************************************************************************
	 * Intercept the end of a transaction on the database.
	 * *************************************************************************
	 * 
	 * @param transaction the transaction
	 */
	public void afterTransactionCompletion(Transaction transaction)
	{
		if (transaction.wasCommitted()) {
			logger.debug("Transaction|COMMIT");
		}
		if (transaction.wasRolledBack()) {
			logger.debug("Transaction|ROLLBACK");
		}
	}
	
	/**
	 * **************************************************************************
	 * Intercept the load of a persistent entity.
	 * *************************************************************************
	 * 
	 * @param entity the entity
	 * @param id the id
	 * @param state the state
	 * @param propertyNames the property names
	 * @param types the types
	 * 
	 * @return true, if on load
	 */
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
	{
		// Evaluate if attributes need to be dumped.
		if (traceAttributeOnLoad)
		{
			String entityClass = entity.getClass().getName();
			// Evaluate if the entity needs to be intercepted.
			if (PatternMatchUtils.simpleMatch(entityPatternList, entityClass))
			{
				String entityAlias = entity.getClass().getSimpleName();
				// Trace the entity that is loaded from the database.
				StringBuffer traceBuffer = new StringBuffer(entityAlias);
				traceBuffer.append("|LOAD|");
				traceBuffer.append(id);
					// Trace properties and values of the entity that is created.
					for (int index = 0; index < propertyNames.length; index++)
					{
						traceBuffer.append("|");
						traceBuffer.append(propertyNames[index]);
						traceBuffer.append("=");
						traceBuffer.append(state[index]);
					}
				logger.debug("{}", traceBuffer);
			}
		}
		return (false);
	}
	
	/**
	 * **************************************************************************
	 * Intercept the flush of a persistent entity.
	 * *************************************************************************
	 * 
	 * @param entity the entity
	 * @param id the id
	 * @param currentState the current state
	 * @param previousState the previous state
	 * @param propertyNames the property names
	 * @param types the types
	 * 
	 * @return true, if on flush dirty
	 */
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
	    String[] propertyNames, Type[] types)
	{
		// Evaluate if attributes need to be dumped.
		if (traceAttributeOnUpdate)
		{
			String entityClass = entity.getClass().getName();
			// Evaluate if the entity needs to be intercepted.
			if (PatternMatchUtils.simpleMatch(entityPatternList, entityClass))
			{
				String entityAlias = entity.getClass().getSimpleName();
				// Trace the entity after the update in the database.
				StringBuffer traceBuffer = new StringBuffer(entityAlias);
				traceBuffer.append("|UPDATE|");
				traceBuffer.append(id);
					// Trace properties and values of the entity that is created.
					for (int index = 0; index < propertyNames.length; index++)
					{
						traceBuffer.append("|");
						traceBuffer.append(propertyNames[index]);
						traceBuffer.append("=");
						traceBuffer.append(currentState[index]);
					}
				logger.debug("{}", traceBuffer);
			}
		}
		return (false);
	}
	
	/**
	 * **************************************************************************
	 * Intercept the save of a transient entity.
	 * *************************************************************************
	 * 
	 * @param entity the entity
	 * @param id the id
	 * @param state the state
	 * @param propertyNames the property names
	 * @param types the types
	 * 
	 * @return true, if on save
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
	{
		// Evaluate if attributes need to be dumped.
		if (traceAttributeOnSave)
		{
			String entityClass = entity.getClass().getName();
			// Evaluate if the entity needs to be intercepted.
			if (PatternMatchUtils.simpleMatch(entityPatternList, entityClass))
			{
				String entityAlias = entity.getClass().getSimpleName();
				// Trace the entity that is created in the database.
				StringBuffer traceBuffer = new StringBuffer(entityAlias);
				traceBuffer.append("|CREATE|");
				traceBuffer.append(id);
					// Trace properties and values of the entity that is created.
					for (int index = 0; index < propertyNames.length; index++)
					{
						traceBuffer.append("|");
						traceBuffer.append(propertyNames[index]);
						traceBuffer.append("=");
						traceBuffer.append(state[index]);
					}
				logger.debug("{}", traceBuffer);
			}
		}
		return (false);
	}
	
	/**
	 * **************************************************************************
	 * Intercept the delete of a persistent entity.
	 * *************************************************************************
	 * 
	 * @param entity the entity
	 * @param id the id
	 * @param state the state
	 * @param propertyNames the property names
	 * @param types the types
	 */
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
	{
		// Evaluate if attributes need to be dumped.
		if (traceAttributeOnDelete)
		{
			String entityClass = entity.getClass().getName();
			// Evaluate if the entity needs to be intercepted.
			if (PatternMatchUtils.simpleMatch(entityPatternList, entityClass))
			{
				String entityAlias = entity.getClass().getSimpleName();
				// Trace the entity that is created in the database.
				StringBuffer traceBuffer = new StringBuffer(entityAlias);
				traceBuffer.append("|DELETE|");
				traceBuffer.append(id);
					// Trace properties and values of the entity that is created.
					for (int index = 0; index < propertyNames.length; index++)
					{
						traceBuffer.append("|");
						traceBuffer.append(propertyNames[index]);
						traceBuffer.append("=");
						traceBuffer.append(state[index]);
					}
				logger.debug("{}", traceBuffer);
			}
		}
	}
	
	/**
	 * **************************************************************************
	 * Intercept the prepare of a SQL statement.
	 * *************************************************************************
	 * 
	 * @param sql the sql
	 * 
	 * @return the string
	 */
	public String onPrepareStatement(String sql)
	{
		if (traceSqlStatement == true) {
			logger.debug("SQL|{}", sql);
		}
		return (sql);
	}
	//***************************************************************************
	// GETTER/SETTER METHODS
	//***************************************************************************
	/**
	 * Gets the entity pattern list.
	 * 
	 * @return the entity pattern list
	 */
	public String[] getEntityPatternList()
	{
		return entityPatternList;
	}
	
	/**
	 * Sets the entity pattern list.
	 * 
	 * @param entityPatternList the new entity pattern list
	 */
	public void setEntityPatternList(String[] entityPatternList)
	{
		this.entityPatternList = entityPatternList;
	}
	
	/**
	 * Checks if is trace attribute on load.
	 * 
	 * @return true, if is trace attribute on load
	 */
	public boolean isTraceAttributeOnLoad()
	{
		return traceAttributeOnLoad;
	}
	
	/**
	 * Sets the trace attribute on load.
	 * 
	 * @param traceAttributeOnLoad the new trace attribute on load
	 */
	public void setTraceAttributeOnLoad(boolean traceAttributeOnLoad)
	{
		this.traceAttributeOnLoad = traceAttributeOnLoad;
	}
	
	/**
	 * Checks if is trace attribute on update.
	 * 
	 * @return true, if is trace attribute on update
	 */
	public boolean isTraceAttributeOnUpdate()
	{
		return traceAttributeOnUpdate;
	}
	
	/**
	 * Sets the trace attribute on update.
	 * 
	 * @param traceAttributeOnUpdate the new trace attribute on update
	 */
	public void setTraceAttributeOnUpdate(boolean traceAttributeOnUpdate)
	{
		this.traceAttributeOnUpdate = traceAttributeOnUpdate;
	}
	
	/**
	 * Checks if is trace attribute on save.
	 * 
	 * @return true, if is trace attribute on save
	 */
	public boolean isTraceAttributeOnSave()
	{
		return traceAttributeOnSave;
	}
	
	/**
	 * Sets the trace attribute on save.
	 * 
	 * @param traceAttributeOnSave the new trace attribute on save
	 */
	public void setTraceAttributeOnSave(boolean traceAttributeOnSave)
	{
		this.traceAttributeOnSave = traceAttributeOnSave;
	}
	
	/**
	 * Checks if is trace attribute on delete.
	 * 
	 * @return true, if is trace attribute on delete
	 */
	public boolean isTraceAttributeOnDelete()
	{
		return traceAttributeOnDelete;
	}
	
	/**
	 * Sets the trace attribute on delete.
	 * 
	 * @param traceAttributeOnDelete the new trace attribute on delete
	 */
	public void setTraceAttributeOnDelete(boolean traceAttributeOnDelete)
	{
		this.traceAttributeOnDelete = traceAttributeOnDelete;
	}
	
	/**
	 * Checks if is trace sql statement.
	 * 
	 * @return true, if is trace sql statement
	 */
	public boolean isTraceSqlStatement()
	{
		return traceSqlStatement;
	}
	
	/**
	 * Sets the trace sql statement.
	 * 
	 * @param traceSqlStatement the new trace sql statement
	 */
	public void setTraceSqlStatement(boolean traceSqlStatement)
	{
		this.traceSqlStatement = traceSqlStatement;
	}
}


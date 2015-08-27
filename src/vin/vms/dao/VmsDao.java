package vin.vms.dao;


import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import vin.vms.commons.exception.VmsPersistenceException;



// TODO: Auto-jtmerated Javadoc
/**
 * Base class for all Hibernate DAOs
 * Provides common services to all DAOs that work with GEN database.
 */


public class VmsDao extends HibernateDaoSupport {
	
	
	public static final String JTM_TEST_DETAILS_SEQ = "JTM_TEST_DETAILS_SEQ";
	/** The Constant FAIL_LOAD_MSG. */
	private static final String FAIL_LOAD_MSG = "Failure to load ";
	
	/** The Constant WITH_ID_MSG. */
	private static final String WITH_ID_MSG = " with identifier of ";
    
    /** The Constant DATABASE_MSG. */
    private static final String DATABASE_MSG = " because of the following database problem: ";
    
   
	
	/** The Constant log. */
	private static final Log LOG = LogFactory.getLog(VmsDao.class);
	

	
	 /**
 	 * Loads an object from persistent storage based on oid (object id).
 	 * 
 	 * @param oid object id of the object to be loaded.
 	 * @param referencedClass class type to be loaded.
 	 * 
 	 * @return a loaded object or null if the object identified by oid is not found.
 	 * 
 	 * @throws DataAccessException when an error occurs during loading of the object.
 	 * @throws VmsPersistenceException the jtm persistence exception
 	 */
    public Object load(final Serializable oid, final Class referencedClass)
            throws VmsPersistenceException {
        Object object = null;
        try {
            object = getHibernateTemplate().get(referencedClass, oid);

            if ((object == null) && LOG.isDebugEnabled()) {
                LOG.debug("load: " + referencedClass + " for oid=" + oid
                        + " was not found");
            }
        } catch (org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException rfex) {
            /** If an attempt to retrieve a deleted object occurs within the transaction
             * that deleted it, this exception will occur.  Return null to provide
             * consistent results within and without the transaction.
             * 
             */
            Throwable cause = rfex.getCause();

            if (cause instanceof org.hibernate.ObjectDeletedException) {
                return null;
            }

            String msg = FAIL_LOAD_MSG + referencedClass + WITH_ID_MSG + oid
                    + DATABASE_MSG + rfex.getMessage();
            LOG.trace(msg, rfex);

            VmsPersistenceException newex = new VmsPersistenceException(msg, rfex.getCause(),
                    "");
            throw newex;

        } catch (org.springframework.dao.DataAccessException ex) {
            String msg = FAIL_LOAD_MSG + referencedClass + WITH_ID_MSG + oid
                    + DATABASE_MSG + ex.getMessage();
            LOG.trace(msg, ex);

            VmsPersistenceException newex = new VmsPersistenceException(msg, ex.getCause(),
                    "");
            throw newex;
        } 
        return object;
    }
    
	/**
	 * Inserts a new object or updates an existing one.
	 * 
	 * @param object Object to be saved.
	 * 
	 * @throws DataAccessException when Hibernate saveOrUpdate is not
	 * successful.
	 * @throws VmsPersistenceException the jtm persistence exception
	 */
   public void save(final Object object) throws VmsPersistenceException {
       try {
           /**
            * Use contains to validate whether object is already persisted in current transaction,
            * if so flush() to ensure the cache and db reflect current state of object.
            * The call to saveOrUpdate will have no affect on db or cache if object is already persisted.
            */
           HibernateTemplate hibernateTemplate = getHibernateTemplate();
           hibernateTemplate.setCheckWriteOperations(false);
           if (hibernateTemplate.contains(object)) {
               hibernateTemplate.flush();
           } else {
               hibernateTemplate.saveOrUpdate(object);
               hibernateTemplate.flush();
           }
       } catch (org.springframework.dao.DataAccessException ex) {
           String msg = "Failure to save " + object.getClass() + DATABASE_MSG
                   + ex.getMessage();

           if (LOG.isTraceEnabled()) {
               LOG.trace(msg + " object: " + object, ex);
           }

           VmsPersistenceException newex = new VmsPersistenceException(msg, ex.getCause(),
                   "default");
           throw newex;
       } 
   }
   
   /**
    * Removes an object from persistent storage.
    * 
    * @param object Object to be removed.
    * 
    * @throws DataAccessException when an error occurs during removal of the object.
    * @throws VmsPersistenceException the jtm persistence exception
    */
   public void remove(final Object object) throws VmsPersistenceException {       
       try {
    	   getHibernateTemplate().setCheckWriteOperations(false);
    	   getHibernateTemplate().delete(object);
           getHibernateTemplate().flush();
       } catch (org.springframework.dao.DataAccessException ex) {
           String msg = "Failure to remove " + object.getClass() + DATABASE_MSG
                   + ex.getMessage();

           if (LOG.isTraceEnabled()) {
               LOG.trace(msg + " object: " + object, ex);
           }

           VmsPersistenceException newex = new VmsPersistenceException(msg, ex.getCause(),
                   "");
           throw newex;
       } finally {
          
       }
   }

   /**
    * Removes an object from persistent storage using oid (object id).
    * 
    * @param oid id of the object to be removed.
    * @param referencedClass class type to be removed (needed for the load).
    * 
    * @throws DataAccessException when an error occurs during removal of the object.
    * @throws VmsPersistenceException the jtm persistence exception
    */
   public void remove(final Serializable oid, final Class referencedClass)
           throws VmsPersistenceException {
       Object object = null;
       try {
           object = load(oid, referencedClass);

           if (object != null) {
               getHibernateTemplate().delete(object);
           }
       } catch (org.springframework.dao.DataAccessException ex) {
           String msg = "Failure to remove " + referencedClass + WITH_ID_MSG + oid
                   + DATABASE_MSG + ex.getMessage();
           LOG.trace(msg, ex);

           VmsPersistenceException newex = new VmsPersistenceException(msg, ex.getCause(),
                   "");
           throw newex;
       } 
   }
   
   /**
	 * Return the next available object id built using the provided sequence.
	 * 
	 * @param sequenceName the sequence name
	 * 
	 * @return the new Object Id
	 */
	public Integer nextObjId(final String sequenceName) {
		final Integer newId = (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(org.hibernate.Session session) throws HibernateException, SQLException {
				final Integer nextId = (Integer) session.createSQLQuery("SELECT " + sequenceName + ".nextval nextId FROM DUAL").addScalar("nextId", Hibernate.LONG)
						.uniqueResult();
				return nextId;
			}
		});
		return newId;
	}
}


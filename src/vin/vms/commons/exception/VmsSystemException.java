package vin.vms.commons.exception;


// TODO: Auto-Ttcserated Javadoc
/**
 * The Class TtcsSystemException.
 * 
 * 
 */
public class VmsSystemException extends VmsException {


	/** The message code. */
	private String messageCode;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4093230321189129337L;

	/**
	 * The Constructor.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 * @param args the args
	 */
	public VmsSystemException(String message, Throwable cause, String key,
			Object... args) {
		super(message, cause, key, args);
		// TODO Auto-Ttcserated constructor stub
	}

	/**
	 * The Constructor.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 */
	public VmsSystemException(String message, Throwable cause, String key) {
		super(message, cause, key);
		// TODO Auto-Ttcserated constructor stub
	}

	/**
	 * The Constructor.
	 * 
	 * @param message the message
	 * @param key the key
	 */
	public VmsSystemException(String message, String key) {
		super(message, key);
		// TODO Auto-Ttcserated constructor stub
	}

	/**
	 * The Constructor.
	 * 
	 * @param cause the cause
	 * @param key the key
	 */
	public VmsSystemException(Throwable cause, String key) {
		super(cause, key);
		// TODO Auto-Ttcserated constructor stub
	}

	/**
	 * The Constructor.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param messageParameters the message parameters
	 */
	public VmsSystemException(String message, String key, Object[] messageParameters) {
		super(message, key, messageParameters);
		// TODO Auto-Ttcserated constructor stub
	}

	/**
	 * The Constructor.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param detail the detail
	 * @param messageParameters the message parameters
	 */
	public VmsSystemException(String message, String key, String detail, Object[] messageParameters) {
		super(message, key, detail, messageParameters);
		// TODO Auto-Ttcserated constructor stub
	}

	
	/**
	 * The Constructor.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param detail the detail
	 */
	public VmsSystemException(String message, String key, String detail) {
		super(message, key, detail);
		// TODO Auto-Ttcserated constructor stub
	}

	/**
	 * The Constructor.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 * @param detail the detail
	 * @param args the args
	 */
	public VmsSystemException(String message, Throwable cause, String key, String detail, Object... args) {
		super(message, cause, key, detail, args);
		// TODO Auto-Ttcserated constructor stub
	}

	/**
	 * Gets the message code.
	 * 
	 * @return the message code
	 */
	public String getMessageCode() {
		return messageCode;
	}

	/**
	 * Sets the message code.
	 * 
	 * @param messageCode the new message code
	 */
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	

}

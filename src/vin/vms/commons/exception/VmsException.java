package vin.vms.commons.exception;


import org.apache.commons.lang.StringUtils;

// TODO: Auto-Ttcserated Javadoc
/**
 * The Class TtcsException.
 */
public class VmsException extends Exception {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2941898546742897403L;
	
	/** The key. */
	private String key;
	
	/** The args. */
	private Object[] args;
	
	/** The detail. */
	private String detail;
	
	/** The Constant PREFIX. */
	public static final String PREFIX = "Ttcs.exception.";

	/**
	 * Instantiates a new Ttcs exception.
	 * 
	 * @param message the message
	 * @param key the key
	 */
	public VmsException(String message, String key) {
		super(message);
		this.key=this.buildKey(key);
	}

	/**
	 * Instantiates a new Ttcs exception.
	 * 
	 * @param cause the cause
	 * @param key the key
	 */
	public VmsException(Throwable cause, String key) {
		super(cause);
		this.key=this.buildKey(key);
	}

	/**
	 * Instantiates a new Ttcs exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 */
	public VmsException(String message, Throwable cause, String key) {
		super(message, cause);
		this.key=this.buildKey(key);
	}

	/**
	 * Instantiates a new Ttcs exception.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param detail the detail
	 */
	public VmsException(String message, String key, String detail) {
		this(message, key);
		this.detail = detail;
	}

	/**
	 * Instantiates a new Ttcs exception.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param messageParameters the message parameters
	 */
	public VmsException(String message, String key, Object[] messageParameters) {
		this(message, key);
		this.args = messageParameters;
	}

	/**
	 * Instantiates a new Ttcs exception.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param detail the detail
	 * @param messageParameters the message parameters
	 */
	public VmsException(String message, String key, String detail, Object[] messageParameters) {
		this(message, key, messageParameters);
		this.detail = detail;
	}

	/**
	 * Instantiates a new Ttcs exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 * @param args the args
	 */
	public VmsException(String message, Throwable cause, String key, Object ... args) {
		this(message, cause, key);
		this.args=args;
	}

	/**
	 * Instantiates a new Ttcs exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 * @param detail the detail
	 * @param args the args
	 */
	public VmsException(String message, Throwable cause, String key, String detail, Object ... args) {
		this(message, cause, key, args);
		this.detail = detail;
	}


	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 * 
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Gets the args.
	 * 
	 * @return the args
	 */
	public Object[] getArgs() {
		return args;
	}
	
	/**
	 * Sets the args.
	 * 
	 * @param args the new args
	 */
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	/**
	 * Gets the detail.
	 * 
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}
	
	/**
	 * Sets the detail.
	 * 
	 * @param detail the new detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * Builds the key.
	 * 
	 * @param key the key
	 * 
	 * @return the string
	 */
	private String buildKey(String key) {
		return VmsException.PREFIX+key;
	}
	
	/**
	 * Gets the key without prefix.
	 * 
	 * @return the key without prefix
	 */
	public String getKeyWithoutPrefix() {
		return StringUtils.removeStart(key, VmsException.PREFIX);
	}

	
}


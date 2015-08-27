package vin.vms.commons.exception;



//TODO: Auto-generated Javadoc
/**
* The Class TtcsFunctionalException.
*/
public class VmsFunctionalException extends VmsException {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 467624819599877906L;
	
	/**
	 * Instantiates a new gen functional exception.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param args the args
	 */
	public VmsFunctionalException(String message, String key, Object... args) {
		super(message, key, args);
	}	

	/**
	 * Instantiates a new gen functional exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 * @param args the args
	 */
	public VmsFunctionalException(String message, Throwable cause, String key, Object... args) {
		super(message, cause, key, args);
	}

	/**
	 * Instantiates a new gen functional exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 */
	public VmsFunctionalException(String message, Throwable cause, String key) {
		super(message, cause, key);
	}

	/**
	 * Instantiates a new gen functional exception.
	 * 
	 * @param message the message
	 * @param key the key
	 */
	public VmsFunctionalException(String message, String key) {
		super(message, key);
	}

	/**
	 * Instantiates a new gen functional exception.
	 * 
	 * @param cause the cause
	 * @param key the key
	 */
	public VmsFunctionalException(Throwable cause, String key) {
		super(cause, key);
	}

	/**
	 * Instantiates a new gen functional exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 * @param key the key
	 * @param detail the detail
	 * @param args the args
	 */
	public VmsFunctionalException(String message, Throwable cause, String key, String detail, Object... args) {
		super(message, cause, key, detail, args);
	}

	/**
	 * Instantiates a new gen functional exception.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param detail the detail
	 * @param messageParameters the message parameters
	 */
	public VmsFunctionalException(String message, String key, String detail, Object[] messageParameters) {
		super(message, key, detail, messageParameters);
	}

	/**
	 * Instantiates a new gen functional exception.
	 * 
	 * @param message the message
	 * @param key the key
	 * @param detail the detail
	 */
	public VmsFunctionalException(String message, String key, String detail) {
		super(message, key, detail);
	}

	

}


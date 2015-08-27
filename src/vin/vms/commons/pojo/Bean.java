package vin.vms.commons.pojo;



import java.io.Serializable;

/**
 * This interface is defined to provide a hierarchy to the fdt bean definition.
 * Such beans are supposed to ships the data to and from the Command layer. As such, this interface extends the Serializable interface.
 * Any fdt implementing this interface should add the code line:
 * 
 * private static final long serialVersionUID = <a long number>;
 * 
 * The interface implementation should provide common content field to beans that share the same functionalities.
 * 
 * @author id814929
 * @see Serializable
 */
public interface Bean extends Serializable {

}

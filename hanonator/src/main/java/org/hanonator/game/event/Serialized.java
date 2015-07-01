package org.hanonator.game.event;

/**
 * 
 * 
 * @author user104
 */
public @interface Serialized {
	
	int index() default 0;
	
	/**
	 * Get the DataType of the 
	 */
	DataType value() default DataType.BYTE;
	
	public static enum DataType {
		BYTE, SHORT, TRI, INT, LONG;
	}
	
}
package org.hanonator.game;

/**
 * 
 * 
 * @author user104
 */
public @interface Decode {
	
	/**
	 * Get the DataType of the 
	 */
	DataType value() default DataType.BYTE;
	
	public static enum DataType {
		BYTE, SHORT, TRI, INT, LONG;
	}
	
}
package org.hanonator.net;

public interface Session {

	/**
	 * Reads object
	 * 
	 * @param object
	 */
	public abstract void read(Object object);
	
	/**
	 * Writes object
	 * 
	 * @param object
	 */
	public abstract void write(Object object);

}
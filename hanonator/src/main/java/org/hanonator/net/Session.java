package org.hanonator.net;

import org.hanonator.game.GameException;

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

	/**
	 * Pushes an exception to the player
	 * 
	 * @param ex
	 */
	public abstract void push(GameException ex);

}
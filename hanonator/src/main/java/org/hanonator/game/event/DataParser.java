package org.hanonator.game.event;

import org.hanonator.game.User;
import org.hanonator.net.GameMessage;

@FunctionalInterface
public interface DataParser {

	/**
	 * 
	 * 
	 * @param payload
	 * @throws Exception
	 */
	public abstract void parse(GameMessage message, User user) throws Exception;

	/**
	 * 
	 * @param throwable
	 */
	default void handleException(Throwable throwable, GameMessage message, User user) {
		
	}

}
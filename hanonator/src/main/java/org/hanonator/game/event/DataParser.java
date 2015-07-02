package org.hanonator.game.event;

import org.hanonator.game.GameException;
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
	public abstract void parse(GameMessage message, User user) throws GameException;

	/**
	 * 
	 * @param throwable
	 */
	default void handleException(GameException exception, GameMessage message, User user) {
		user.push(exception);
	}

}
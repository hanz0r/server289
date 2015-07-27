package org.hanonator.game.event.filter;

import org.hanonator.game.GameException;
import org.hanonator.net.GameMessage;
import org.hanonator.net.Session;

@FunctionalInterface
public interface Filter {

	/**
	 * Parses the GameMessage to the given data type
	 * 
	 * @param payload
	 * @throws Exception
	 */
	public abstract FilterResult filter(FilterChainContext context) throws GameException;

	/**
	 * 
	 * @param throwable
	 */
	default void handleException(GameException exception, GameMessage message, Session<?> session) {
		session.push(exception);
	}

	/**
	 * 
	 * @author user104
	 */
	public static enum FilterResult {
		SUCCESS, STOP, REWIND; 
	}

}
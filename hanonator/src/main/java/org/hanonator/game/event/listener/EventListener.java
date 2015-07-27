package org.hanonator.game.event.listener;

import org.hanonator.game.GameException;
import org.hanonator.game.event.GameEvent;
import org.hanonator.net.Session;

@FunctionalInterface
public interface EventListener<T extends GameEvent> {

	/**
	 * Called when a game event has been received with the ID which this listener listens to.
	 * 
	 * @param session
	 * @param event
	 * @throws GameException
	 */
	public abstract void listen(Session<?> session, T event) throws GameException;

	/**
	 * Called when an exception occurs in listen()
	 * @param session
	 * @param ex
	 */
	default void exceptionOccured(Session<?> session, GameException ex) {
		session.push(ex);
	}

}
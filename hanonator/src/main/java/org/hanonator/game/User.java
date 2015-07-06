package org.hanonator.game;

import org.glassfish.grizzly.Connection;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.GameEventProcessor;
import org.hanonator.processor.Processor;

public class User {

	/**
	 * The connection
	 */
	private final Connection<?> connection;
	
	/**
	 * The game event processor
	 */
	private final Processor<GameEvent> eventProcessor = new GameEventProcessor(this);

	public User(Connection<?> connection) {
		this.connection = connection;
	}

	/**
	 * Push exceptions to the user
	 * 
	 * @param ex
	 */
	public void push(GameException ex) {
		// TODO: Disconnect the player with a given error message
	}

	public Connection<?> getConnection() {
		return connection;
	}

	public Processor<GameEvent> getEventProcessor() {
		return eventProcessor;
	}

}
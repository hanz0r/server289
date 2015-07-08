package org.hanonator.game;

import org.glassfish.grizzly.Connection;
import org.hanonator.game.event.GameEventProcessor;
import org.hanonator.net.Session;

public class User implements Session {

	/**
	 * The connection
	 */
	private final Connection<?> connection;
	
	/**
	 * The game event processor
	 */
	private final GameEventProcessor eventProcessor = new GameEventProcessor(this);

	public User(Connection<?> connection) {
		this.connection = connection;
	}

	@Override
	public void read(Object object) {
		System.out.println("Object read: " + object);
	}

	@Override
	public void write(Object object) {
		System.out.println("Object written: " + object);
	}

	@Override
	public void push(GameException ex) {
		// TODO: Disconnect the player with a given error message
	}

	public Connection<?> getConnection() {
		return connection;
	}

	public GameEventProcessor getEventProcessor() {
		return eventProcessor;
	}

}
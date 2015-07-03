package org.hanonator.game;

import org.glassfish.grizzly.Connection;

public class User {

	/**
	 * The connection
	 */
	private final Connection<?> connection;

	public User(Connection<?> connection) {
		this.connection = connection;
	}

	public void push(GameException ex) {
		// TODO: Disconnect the player with a given error message
	}

	public Connection<?> getConnection() {
		return connection;
	}

}
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
		// TODO
	}

	public Connection<?> getConnection() {
		return connection;
	}

}
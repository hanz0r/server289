package org.hanonator.game;

import org.glassfish.grizzly.Connection;

public class User {

	private final Connection<?> connection;

	public User(Connection<?> connection) {
		this.connection = connection;
	}

	public Connection<?> getConnection() {
		return connection;
	}

}
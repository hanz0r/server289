package org.hanonator.net.grizzly;

import org.glassfish.grizzly.Connection;
import org.hanonator.game.GameException;
import org.hanonator.net.GameMessage;
import org.hanonator.net.Session;
import org.hanonator.util.Attributes;

public class GrizzlySession extends Session {

	/**
	 * This session's connection
	 */
	private final Connection<?> connection;
	
	public GrizzlySession(Connection<?> connection) {
		this.connection = connection;
	}

	public GrizzlySession(Attributes attributes) {
		this (attributes.<Connection<?>>get("connection"));
	}

	@Override
	public void read(Object object) {
		if (object instanceof GameMessage) {
			super.getEventProcessor().add((GameMessage) object);
		}
	}

	@Override
	public void write(Object object) {
		connection.write(object);
	}

	@Override
	public void push(GameException ex) {
		/*
		 * TODO: Log the exception
		 */
		ex.printStackTrace();
		
		/*
		 * Close the connection
		 */
		connection.closeSilently();
	}

}
package org.hanonator.net.grizzly;

import org.glassfish.grizzly.Connection;
import org.hanonator.game.GameException;
import org.hanonator.net.channel.AbstractChannel;

/**
 * GrizzlyChannel
 * 
 * @author Red
 *
 */
public class GrizzlyChannel extends AbstractChannel<Connection<?>> {

	/**
	 * This session's connection
	 */
	private final Connection<?> connection;

	/**
	 * Creates a GrizzlyChannel for a given connection
	 * 
	 * @param connection
	 */
	public GrizzlyChannel(Connection<?> connection) {
		this.connection = connection;
	}

	@Override
	public <I> void write(I object) throws GameException {
		connection.write(object);
	}

	@Override
	public void close() throws GameException {
		connection.closeSilently();
	}

}
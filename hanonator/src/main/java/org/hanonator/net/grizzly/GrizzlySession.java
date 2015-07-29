package org.hanonator.net.grizzly;

import java.io.IOException;

import org.glassfish.grizzly.Connection;
import org.hanonator.net.Session;
import org.hanonator.net.channel.Channel;

public class GrizzlySession extends Session<Connection<?>> implements Channel<Connection<?>> {

	/**
	 * The connection for this session/channel
	 */
	private final Connection<?> connection;

	public GrizzlySession(Connection<?> connection) {
		this.connection = connection;
	}

	@Override
	public Channel<Connection<?>> channel() {
		return this;
	}

	@Override
	public void read(Object object) throws IOException {
		System.out.println(object);
	}

	@Override
	public void write(Object object) throws IOException {
		connection.write(object);
	}

	@Override
	public void close() throws IOException {
		connection.closeSilently();
	}

}
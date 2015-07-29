package org.hanonator.net.grizzly;

import java.io.IOException;

import javax.enterprise.context.SessionScoped;

import org.glassfish.grizzly.Connection;
import org.hanonator.net.Channel;

public class GrizzlyChannel implements Channel<Connection<?>> {

	/**
	 * The connection for this session/channel
	 */
	private Connection<?> connection;
	
	@Override
	public void bind(Connection<?> connection) throws IOException {
		if (connection != null) {
			throw new IOException("connection already exists");
		}
		this.connection = connection;
	}

	@Override
	public void read(Object object) throws IOException {
		System.out.println(object);
	}

	@Override
	public void write(Object object) throws IOException {
		if (connection == null || !connection.isOpen()) {
			throw new IOException("can't write to connection");
		}
		connection.write(object);
	}

	@Override
	public void close() throws IOException {
		if (connection == null || !connection.isOpen()) {
			throw new IOException("connection already closed");
		}
		connection.closeSilently();
	}

}
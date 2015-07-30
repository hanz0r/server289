package org.hanonator.net.grizzly;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import org.glassfish.grizzly.Connection;
import org.hanonator.net.Channel;

@SessionScoped
public class GrizzlyChannel implements Channel<Connection<?>>, Serializable {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The connection for this session/channel
	 */
	private Connection<?> connection;
	
	@Override
	public void bind(Connection<?> connection) throws IOException {
		if (this.connection != null) {
			throw new IOException("connection already exists");
		}
		this.connection = connection;
	}

	@Override
	public void read(Object object) throws IOException {
		throw new UnsupportedOperationException("not yet implemented");
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
		connection.close();
	}

	@Override
	public void closeSilently() {
		if (connection == null || !connection.isOpen()) {
			throw new IllegalStateException("connection already closed");
		}
		connection.closeSilently();
	}

}
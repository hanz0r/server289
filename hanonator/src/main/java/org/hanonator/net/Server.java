package org.hanonator.net;

import java.io.IOException;
import java.net.SocketAddress;

/**
 * 
 * @author Red
 */
public interface Server {

	/**
	 * Binds the server to the given socket address
	 * 
	 * @param address
	 * @throws IOException
	 */
	public abstract void bind(SocketAddress address) throws IOException;
	
	/**
	 * Stops the server
	 * 
	 * @throws IOException
	 */
	public abstract void stop() throws IOException;

}
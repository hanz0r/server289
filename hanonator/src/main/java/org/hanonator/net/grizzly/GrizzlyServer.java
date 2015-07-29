package org.hanonator.net.grizzly;

import java.io.IOException;
import java.net.SocketAddress;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.hanonator.net.Server;
import org.jboss.weld.exceptions.UnsupportedOperationException;

/**
 * The server implementation for the Grizzly networking framework
 * @author Red
 *
 */
public class GrizzlyServer implements Server {
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GrizzlyServer.class);

	@Override
	public void bind(SocketAddress address) throws IOException {
		/*
		 * Create the FilterChain
		 */
		final FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
		filterChainBuilder.add(new TransportFilter());
		filterChainBuilder.add(new TempFilter());
		
		/*
		 * Create the transport
		 */
		final TCPNIOTransport transport = TCPNIOTransportBuilder.newInstance().build();
		transport.setProcessor(filterChainBuilder.build());
        transport.bind(address);
        transport.start();
		
		/*
		 * 
		 */
		logger.log(Priority.INFO, "listening on " + address);
	}

	@Override
	public void stop() throws IOException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
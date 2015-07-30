package org.hanonator.net.grizzly;

import java.io.IOException;
import java.net.SocketAddress;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.hanonator.net.Server;
import org.hanonator.net.event.ServerListener;
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
	
	/**
	 * 
	 */
	@Inject private ServerListener listener;

	@Override
	public void bind(SocketAddress address) throws IOException {
		/*
		 * Create the FilterChain
		 */
		final FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
		filterChainBuilder.add(new TransportFilter());
		filterChainBuilder.add(new TempFilter(this));
		
		/*
		 * Create the transport
		 */
		final TCPNIOTransport transport = TCPNIOTransportBuilder.newInstance().build();
		transport.setProcessor(filterChainBuilder.build());
        transport.bind(address);
        transport.start();
        
		logger.info("listening on " + address);
	}

	@Override
	public void stop() throws IOException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public ServerListener getListener() {
		return listener;
	}

}
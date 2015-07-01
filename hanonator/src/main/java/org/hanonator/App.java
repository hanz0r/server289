package org.hanonator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.logging.Logger;

import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.hanonator.net.GameMessageFilter;

/**
 * Hello world!
 *
 */
public class App {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(App.class.getName());

	/**
	 * The address to bind the server on
	 */
	private static final SocketAddress ADDRESS = new InetSocketAddress("localhost", 43594);

	public static void main(String[] args) throws IOException {
		FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();

		/*
		 * Handles basic transport
		 */
		filterChainBuilder.add(new TransportFilter());

		/*
		 * Decode the game packets
		 */
		filterChainBuilder.add(new GameMessageFilter());

		/*
		 * Create TCP transport
		 */
		final TCPNIOTransport transport =
				TCPNIOTransportBuilder.newInstance().build();
		transport.setProcessor(filterChainBuilder.build());

        try {
            /*
             * binding transport to start listen on certain host and port
             */
            transport.bind(ADDRESS);

            /*
             * start the transport
             */
            transport.start();

    		/*
             * Up and running
             */
    		logger.info("OK");
            System.in.read();
        } finally {
            logger.info("Stopping transport...");
            // stop the transport
            transport.shutdownNow();

            logger.info("Stopped transport...");
        }
	}
}

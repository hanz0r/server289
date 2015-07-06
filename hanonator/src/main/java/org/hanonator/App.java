package org.hanonator;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.logging.Logger;

import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.hanonator.game.event.template.Templates;
import org.hanonator.net.ConnectionFilter;
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

	public static void main(String[] args) throws DocumentException, Exception {
		/*
		 * Loading decoders
		 */
		SAXReader reader = new SAXReader();
		Templates.load(reader.read(new File("data/decoders.xml")));
		
		/*
		 * Create the FilterChain
		 */
		final FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();
		
		/*
		 * Handle the connections
		 */
		filterChainBuilder.add(new ConnectionFilter());

		/*
		 * Handles basic transport
		 */
		filterChainBuilder.add(new TransportFilter());

		/*
		 * Decode the game packets
		 */
		filterChainBuilder.add(new GameMessageFilter());
		
		/*
		 * Parse into events
		 */
		// filterChainBuilder.add(new GameMessageBeanFilter());

		/*
		 * Create TCP transport
		 */
		final TCPNIOTransport transport = TCPNIOTransportBuilder.newInstance().build();
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
    		
    		/*
    		 * FIXME
    		 */
            System.in.read();
        } finally {
            transport.shutdownNow();
            logger.info("Stopping transport");
        }
	}
}

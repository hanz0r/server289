package org.hanonator;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.logging.Logger;

import org.glassfish.grizzly.filterchain.FilterChainBuilder;
import org.glassfish.grizzly.filterchain.TransportFilter;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.hanonator.game.event.filter.Filters;
import org.hanonator.game.event.listener.Listeners;
import org.hanonator.net.grizzly.TempFilter;
import org.hanonator.service.Services;

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

	/**
	 * Program entry point 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * Loading decoders
		 */
		Filters.load(new File("data/message-decoders.xml"));
		Services.load(new File("data/services.xml")).forEach(s -> s.start());
		Listeners.load(new File("data/event-listeners.xml"));
		
		/*
		 * Create the FilterChain
		 */
		final FilterChainBuilder filterChainBuilder = FilterChainBuilder.stateless();

		/*
		 * Handles basic transport
		 */
		filterChainBuilder.add(new TransportFilter());

		/*
		 * Decode the game packets
		 */
		filterChainBuilder.add(new TempFilter());
		
		/*
		 * Parse messages into events
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
        	Services.shutdownNow();
            transport.shutdownNow();
            logger.info("Stopping transport");
        }
	}

}
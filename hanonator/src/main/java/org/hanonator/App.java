package org.hanonator;

import java.io.File;
import java.net.InetSocketAddress;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.hanonator.game.event.filter.Filters;
import org.hanonator.game.event.listener.Listeners;
import org.hanonator.net.Server;
import org.hanonator.service.Services;
import org.jboss.weld.environment.se.events.ContainerInitialized;

public class App {

	/**
	 * The server implementation for this application
	 */
	@Inject private Server server;

	/**
	 * Entry point of the application and also initializes.
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void initialize(@Observes ContainerInitialized event) throws Exception {
		/*
		 * Load all of the config files
		 */
		Filters.load(new File("data/message-decoders.xml"));
		Services.load(new File("data/services.xml")).forEach(s -> s.start());
		Listeners.load(new File("data/event-listeners.xml"));
		
		/*
		 * Bind the server to the address
		 */
		server.bind(new InetSocketAddress("localhost", 43594));
	}

}
package org.hanonator.net.event;

import java.io.IOException;
import java.util.Collections;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.listener.Listeners;
import org.hanonator.net.io.Message;
import org.hanonator.net.transformer.Transformer;

@ApplicationScoped
public class ServerListener {

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ServerListener.class);
	
	@Inject private Transformer<Message, GameEvent> transformer;

	/**
	 * The event object for the read events
	 */
	@Inject private Event<ReadEvent> readEvents;
	
	/**
	 * The event object for the write events
	 */
	@Inject private Event<WriteEvent> writeEvents;
	
	/**
	 * The event object for the connect events
	 */
	@Inject private Event<ConnectEvent> connectEvents;
	
	/**
	 * The event object for the disconnect events
	 */
	@Inject private Event<DisconnectEvent> disconnectEvents;

	/**
	 * Handles every read event
	 * 
	 * @param event
	 */
	public void read(@Observes ReadEvent event) throws IOException {
		logger.info("message received - " + event.getMessage());
		
		/*
		 * 
		 */
		GameEvent game_event = transformer.transform(event.getMessage());
		
		/*
		 * transform to event, etc
		 */
		Listeners.notify(Collections.singleton(game_event), event.getSession());
	}

	/**
	 * Handles every write event
	 * 
	 * @param event
	 */
	public void write(@Observes WriteEvent event) throws IOException {
		System.out.println("write");
	}

	/**
	 * Handles every connect event
	 * 
	 * @param event
	 */
	public void connect(@Observes ConnectEvent event) {
		logger.info("connection accepted from " + event.getRemoteAddress());
	}

	/**
	 * Handles every disconnect event
	 * 
	 * @param event
	 */
	public void disconnect(@Observes DisconnectEvent event) throws IOException {
		logger.info("connection " + event.getRemoteAddress() + " closed.");
	}

	/**
	 * @param event
	 * @see javax.enterprise.event.Event#fire(java.lang.Object)
	 */
	public void fire(ReadEvent event) {
		readEvents.fire(event);
	}

	/**
	 * @param event
	 * @see javax.enterprise.event.Event#fire(java.lang.Object)
	 */
	public void fire(WriteEvent event) {
		writeEvents.fire(event);
	}

	/**
	 * @param event
	 * @see javax.enterprise.event.Event#fire(java.lang.Object)
	 */
	public void fire(ConnectEvent event) {
		connectEvents.fire(event);
	}

	/**
	 * @param event
	 * @see javax.enterprise.event.Event#fire(java.lang.Object)
	 */
	public void fire(DisconnectEvent event) {
		disconnectEvents.fire(event);
	}

}
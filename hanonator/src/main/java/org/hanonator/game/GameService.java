package org.hanonator.game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.glassfish.grizzly.Connection;
import org.hanonator.clock.Clock;
import org.hanonator.clock.ClockException;
import org.hanonator.clock.ClockWorker;
import org.hanonator.game.entity.EntityList;
import org.hanonator.game.entity.Player;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.GameEventProcessor;
import org.hanonator.game.event.listener.Listeners;
import org.hanonator.net.Session;
import org.hanonator.service.Service;
import org.hanonator.service.Services;

public class GameService implements Service, ClockWorker {

	/**
	 * The executor service for this game service
	 */
	private final ExecutorService service = Executors.newFixedThreadPool(4);
	
	/**
	 * The collection of players
	 */
	private final EntityList<Player> players = new EntityList<>(EntityList.PLAYER_LIMIT, uuid -> new Player(uuid));
	
	/**
	 * The collection of connections
	 */
	private final Set<Connection<?>> connections = new HashSet<>();

	@Override
	public void start() {
		Services.get(Clock.class).submit(this);
	}

	@Override
	public void stop() {
		service.shutdownNow();
	}

	@Override
	public Result tick() throws ClockException {
		for (Iterator<Connection<?>> iterator = connections.iterator(); iterator.hasNext(); ) {
			try {
				Connection<?> connection = iterator.next();
				
				/*
				 * Get the session
				 */
				Session session = (Session) connection.getAttributes().getAttribute("session");
			
				/*
				 * Transform messages into events
				 */
				List<GameEvent> events = session.getEventProcessor().process(GameEventProcessor.DEFAULT_EVENT_TRANSFORMER);
				
				/*
				 * Distribute the events to the corresponding listeners
				 */
				Listeners.notify(events, session);  
			} catch (GameException e) {
				throw new ClockException(e);
			}
		}
		return Result.RESCHEDULE;
	}

	/**
	 * Register a connection
	 * 
	 * @param connection
	 */
	public void register(Connection<?> connection) {
		synchronized(connection) {
			connections.add(connection);
		}
	}

	/**
	 * Remove a connection
	 * 
	 * @param connection
	 */
	public void remove(Connection<?> connection) {
		synchronized(connection) {
			connections.remove(connection);
		}
	}

	public EntityList<Player> getPlayers() {
		return players;
	}

}
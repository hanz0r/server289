package org.hanonator.game;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hanonator.clock.Clock;
import org.hanonator.clock.ClockException;
import org.hanonator.clock.ClockWorker;
import org.hanonator.game.entity.EntityList;
import org.hanonator.game.entity.Player;
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
//		synchronized (sessions) {
//			for (Iterator<Session<?>> iterator = sessions.iterator(); iterator.hasNext(); ) {
//				try {
//					Session<?> session = iterator.next();
//				
//					/*
//					 * Transform messages into events
//					 */
//					List<GameEvent> events = session.getEventProcessor().process(GameEventProcessor.DEFAULT_EVENT_TRANSFORMER);
//					
//					/*
//					 * Distribute the events to the corresponding listeners
//					 */
//					Listeners.notify(events, session);  
//				} catch (GameException e) {
//					throw new ClockException(e);
//				}
//			}
//		}
		return Result.RESCHEDULE;
	}

	public EntityList<Player> getPlayers() {
		return players;
	}

}
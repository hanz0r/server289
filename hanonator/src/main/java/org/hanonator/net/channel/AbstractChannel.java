package org.hanonator.net.channel;

import java.util.Collections;

import org.hanonator.game.GameException;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.listener.Listeners;
import org.hanonator.net.GameMessage;
import org.hanonator.net.Session;
import org.hanonator.net.transformer.Transformers;

/**
 * Implements the read method which should be the same for each channel
 * 
 * @author Red
 *
 * @param <T>
 */
public abstract class AbstractChannel<T> implements Channel<T> {

	/**
	 * The session
	 */
	private final Session<?> session;

	public AbstractChannel(Session<?> session) {
		this.session = session;
	}

	@Override
	public <I> void read(I object) throws GameException {
		if (session == null) {
			throw new NullPointerException("session is null");
		}
		
		/*
		 * If the object is an unfiltered message, transform it into a game event
		 */
		if (object instanceof GameMessage) {
			this.read((GameMessage) object, Transformers.DEFAULT_EVENT_TRANSFORMER);
		}
		
		/*
		 * If it is a game event, dispatch it to the right event listener
		 */
		else if(object instanceof GameEvent) {
			Listeners.notify(Collections.singleton((GameEvent) object), session);
		}
	}

}
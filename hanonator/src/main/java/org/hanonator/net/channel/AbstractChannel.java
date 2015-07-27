package org.hanonator.net.channel;

import org.hanonator.game.GameException;
import org.hanonator.net.GameMessage;
import org.hanonator.net.transformer.Transformers;

/**
 * Implements the read method which should be the same for each channel
 * 
 * @author Red
 *
 * @param <T>
 */
public abstract class AbstractChannel<T> implements Channel<T> {

	@Override
	public <I> void read(I object) throws GameException {
		/*
		 * If the object is an unfiltered message, transform it into a game event
		 */
		if (object instanceof GameMessage) {
			this.read((GameMessage) object, Transformers.DEFAULT_EVENT_TRANSFORMER);
		}
		
		/*
		 * 
		 */
		else {
			
		}
	}

}
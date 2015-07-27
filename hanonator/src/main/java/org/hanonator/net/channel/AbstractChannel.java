package org.hanonator.net.channel;

import java.util.Collections;

import org.hanonator.game.GameException;
import org.hanonator.net.GameMessage;
import org.hanonator.net.transformer.Transformers;
import org.hanonator.processor.Processor;

/**
 * Implements the read method which should be the same for each channel
 * 
 * @author Red
 *
 * @param <T>
 */
public abstract class AbstractChannel<T> implements Channel<T> {

	/**
	 * 
	 */
	private final Processor<? super Object, ?> processor;

	/**
	 * 
	 * @param processor
	 */
	public AbstractChannel(Processor<? super Object, ?> processor) {
		this.processor = processor;
	}

	@Override
	public <I> void read(I object) throws GameException {
		/*
		 * If the object is an unfiltered message, transform it into a game event
		 */
		if (object instanceof GameMessage) {
			this.read((GameMessage) object, Transformers.DEFAULT_EVENT_TRANSFORMER);
		}
		
		/*
		 * Else submit the element to the process
		 */
		else {
			processor.process(Collections.singleton(object));
		}
	}

}
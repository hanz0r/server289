package org.hanonator.game.event;

import java.util.List;

import org.hanonator.game.GameException;
import org.hanonator.net.GameMessage;
import org.hanonator.net.transform.MessageTransformer;
import org.hanonator.net.transform.Transformer;
import org.hanonator.processor.AsynchronousProcessor;

/**
 * Processes all of the game messages received into game events
 * 
 * @author user104
 */
public final class GameEventProcessor extends AsynchronousProcessor<GameMessage> {

	/**
	 * The default transformer that will attempt to transform the message into an event
	 */
	public static final Transformer<GameMessage, GameEvent> DEFAULT_EVENT_TRANSFORMER = new MessageTransformer();

	/**
	 * Transforms all the messages into events with the use of a transformer class
	 * 
	 * @param transformer
	 * @return
	 */
	public List<GameEvent> process(Transformer<GameMessage, GameEvent> transformer) throws GameException {
		return super.process(m -> transformer.transform(m));
	}

}
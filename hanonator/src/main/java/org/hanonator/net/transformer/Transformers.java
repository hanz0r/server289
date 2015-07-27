package org.hanonator.net.transformer;

import org.hanonator.game.event.GameEvent;
import org.hanonator.net.GameMessage;

public class Transformers {

	/**
	 * The default transformer that will attempt to transform the message into an event
	 */
	public static final Transformer<GameMessage, GameEvent> DEFAULT_EVENT_TRANSFORMER = new MessageTransformer();

}
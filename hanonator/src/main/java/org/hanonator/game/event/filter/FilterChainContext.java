package org.hanonator.game.event.filter;

import org.hanonator.game.event.GameEvent;
import org.hanonator.net.GameMessage;
import org.hanonator.util.Attributes;

/**
 * The filter chain context
 * 
 * @author Red
 */
public class FilterChainContext {

	/**
	 * THe map of attributes
	 */
	private final Attributes attributes = new Attributes();
	
	/**
	 * The message
	 */
	private final GameMessage message;
	
	/**
	 * The event
	 */
	private final GameEvent event;

	public FilterChainContext(GameMessage message, GameEvent event) {
		this.message = message;
		this.event = event;
	}

	public GameMessage getMessage() {
		return message;
	}

	public GameEvent getEvent() {
		return event;
	}

	public Attributes getAttributes() {
		return attributes;
	}

}
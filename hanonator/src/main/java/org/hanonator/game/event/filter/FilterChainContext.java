package org.hanonator.game.event.filter;

import java.util.HashMap;
import java.util.Map;

import org.hanonator.game.User;
import org.hanonator.game.event.GameEvent;
import org.hanonator.net.GameMessage;

public class FilterChainContext {

	/**
	 * THe map of attributes
	 */
	private final Map<String, Object> attributes = new HashMap<>();

	/**
	 * The user
	 */
	private final User user;
	
	/**
	 * The message
	 */
	private final GameMessage message;
	
	/**
	 * The event
	 */
	private final GameEvent event;

	public FilterChainContext(User user, GameMessage message, GameEvent event) {
		this.user = user;
		this.message = message;
		this.event = event;
	}

	public Object getAttribute(Object key) {
		return attributes.get(key);
	}

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public User getUser() {
		return user;
	}

	public GameMessage getMessage() {
		return message;
	}

	public GameEvent getEvent() {
		return event;
	}

}
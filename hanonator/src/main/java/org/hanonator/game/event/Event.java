package org.hanonator.game.event;

import java.util.HashMap;
import java.util.Map;

/**
 * An event
 * 
 * @author user104
 */
public class Event {

	/**
	 * The opcode of the event
	 */
	private final int index;

	/**
	 * THe map of attributes
	 */
	private final Map<String, Object> attributes = new HashMap<>();
	
	public Event(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
}
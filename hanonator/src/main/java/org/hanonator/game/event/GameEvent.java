package org.hanonator.game.event;

import org.hanonator.util.Attributes;

/**
 * An event
 * 
 * @author user104
 */
public class GameEvent {

	/**
	 * The opcode of the event
	 */
	private final int index;

	/**
	 * The attributes of this game event
	 */
	private final Attributes attributes = new Attributes();

	public GameEvent(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
	public <T> T getAttribute(String name) {
		return attributes.get(name);
	}
	
	public void setAttribute(String name, Object object) {
		this.attributes.set(name, object);
	}

	public Attributes getAttributes() {
		return attributes;
	}

	@Override
	public String toString() {
		return "GameEvent [index=" + index + ", attributes=" + attributes.size() + "]";
	}

}
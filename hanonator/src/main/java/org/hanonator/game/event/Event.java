package org.hanonator.game.event;

/**
 * An event
 * 
 * @author user104
 */
public abstract class Event {

	/**
	 * The opcode of the event
	 */
	private final int index;
	
	public Event(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
}
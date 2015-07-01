package org.hanonator.game;

/**
 * An event
 * 
 * @author user104
 */
public abstract class Event {

	private final int index;

	public Event(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

}
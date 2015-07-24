package org.hanonator.net;

import org.hanonator.game.GameException;
import org.hanonator.game.event.GameEventProcessor;
import org.hanonator.util.Attributes;

/**
 * Represents a session.
 * 
 * @author Red
 */
public abstract class Session {
	
	/**
	 * The state of the session. By default it is connected
	 */
	private State state = State.CONNECTED;
	
	/**
	 * The attributes for this session
	 */
	private final Attributes attributes = new Attributes();

	/**
	 * The game event processor
	 */
	private final GameEventProcessor eventProcessor = new GameEventProcessor();
	
	/**
	 * Reads object
	 * 
	 * @param object
	 */
	public abstract void read(Object object);
	
	/**
	 * Writes object
	 * 
	 * @param object
	 */
	public abstract void write(Object object);

	/**
	 * Pushes an exception to the player
	 * 
	 * @param ex
	 */
	public abstract void push(GameException ex);
	
	/**
	 * Get the attributes for this session
	 * 
	 * @return
	 */
	public Attributes getAttributes() {
		return attributes;
	}

	/**
	 * Gets the session's state
	 * @return
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets the session's state
	 * @param state
	 * @return
	 */
	public Session setState(State state) {
		this.state = state;
		return this;
	}

	/**
	 * Get the game processor 
	 * @return
	 */
	public GameEventProcessor getEventProcessor() {
		return eventProcessor;
	}

	/**
	 * Indicates the state the session is currently in 
	 * 
	 * @author Red
	 */
	public static enum State {
		/**
		 * The connection has recently connected and is in the process of logging in
		 */
		CONNECTED,
		
		/**
		 * The connection has passed the login sequence and is actively being used
		 */
		ACTIVE,
		
		/**
		 * The connection is idle and will be closed as soon as possible
		 */
		IDLE,
		
		/**
		 * The connection has been disconnected and will be removed from the pool
		 */
		DISCONNECTED;
	}

}
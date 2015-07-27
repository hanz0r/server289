package org.hanonator.net;

import org.hanonator.game.GameException;
import org.hanonator.net.channel.Channel;
import org.hanonator.util.Attributes;

/**
 * Represents a session.
 * 
 * @author Red
 */
public class Session<T> {
	
	/**
	 * The state of the session. By default it is connected
	 */
	private State state = State.CONNECTED;
	
	/**
	 * The attributes for this session
	 */
	private final Attributes attributes = new Attributes();

	/**
	 * The channel for this session
	 */
	private Channel<T> channel;

	/**
	 * Pushes an exception to the player
	 * 
	 * @param ex
	 */
	public void push(GameException ex) {
		ex.printStackTrace();
	}
	
	/**
	 * Registers a channel to this session
	 * 
	 * @param channel
	 */
	public void register(Channel<T> channel) {
		if (this.channel != null) {
			throw new IllegalStateException("There already is a channel registered to this session");
		}
		this.channel = channel;
	}
	
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
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Gets the channel
	 * 
	 * @return
	 */
	public Channel<T> channel() {
		return channel;
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
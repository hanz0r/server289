package org.hanonator.game.event;

import org.hanonator.game.User;
import org.hanonator.net.GameMessage;
import org.hanonator.processor.AsynchronousProcessor;

/**
 * Processes all of the game messages received into game events
 * 
 * @author user104
 */
public class GameEventProcessor extends AsynchronousProcessor<GameMessage> {

	/**
	 * The user object
	 */
	private final User user;
	
	/**
	 * Creates a game processor for a given user
	 * 
	 * @param user
	 */
	public GameEventProcessor(User user) {
		this.user = user;
	}

}
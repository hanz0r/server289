package org.hanonator.game.event;

import org.hanonator.game.User;
import org.hanonator.processor.AbstractProcessor;

public class GameEventProcessor extends AbstractProcessor<GameEvent> {

	/**
	 * The user object
	 */
	private final User user;
	
	public GameEventProcessor(User user) {
		this.user = user;
	}

}
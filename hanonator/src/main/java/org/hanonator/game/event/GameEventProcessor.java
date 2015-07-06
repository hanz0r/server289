package org.hanonator.game.event;

import java.util.concurrent.Phaser;

import org.hanonator.game.GameException;
import org.hanonator.game.User;
import org.hanonator.processor.Processor;

public class GameEventProcessor extends Processor<GameEvent> {

	/**
	 * The user object
	 */
	private final User user;
	
	/**
	 * The phaser object
	 */
	private final Phaser phaser = new Phaser(1);
	
	public GameEventProcessor(User user) {
		this.user = user;
	}

	@Override
	public ProcessResult process(GameEvent item) throws GameException {
		return null;
	}

}
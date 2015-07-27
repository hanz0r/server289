package org.hanonator.game.event;

import org.hanonator.processor.AsynchronousProcessor;

/**
 * 
 * @author user104
 *
 */
public class GameEventProcessor extends AsynchronousProcessor<GameEvent, GameEvent> {

	@Override
	public GameEvent processElement(GameEvent element) {
		return element;
	}

}
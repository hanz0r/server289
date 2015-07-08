package org.hanonator.net;

import org.hanonator.game.event.GameEvent;

public class MessageTransformer implements Transformer<GameMessage, GameEvent> {

	@Override
	public GameEvent transform(GameMessage input) throws TransformationException {
		throw new TransformationException(new UnsupportedOperationException("Not yet implemented"));
	}

}
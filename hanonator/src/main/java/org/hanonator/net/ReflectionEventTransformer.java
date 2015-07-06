package org.hanonator.net;

import org.glassfish.grizzly.AbstractTransformer;
import org.glassfish.grizzly.TransformationException;
import org.glassfish.grizzly.TransformationResult;
import org.glassfish.grizzly.attributes.AttributeStorage;
import org.hanonator.game.event.GameEvent;

public class ReflectionEventTransformer extends AbstractTransformer<GameEvent, GameMessage> {

	public String getName() {
		return "bean encoder";
	}

	@Override
	protected TransformationResult<GameEvent, GameMessage> transformImpl(AttributeStorage storage, GameEvent input) throws TransformationException {
		
		return null;
	}

	public boolean hasInputRemaining(AttributeStorage storage, GameEvent input) {
		return false;
	}

}
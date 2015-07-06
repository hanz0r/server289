package org.hanonator.net;

import org.glassfish.grizzly.filterchain.AbstractCodecFilter;
import org.hanonator.game.event.GameEvent;

/**
 * 
 * 
 * @author Red
 */
public class GameMessageBeanFilter extends AbstractCodecFilter<GameMessage, GameEvent> {

	public GameMessageBeanFilter() {
		super(new ReflectionMessageTransformer(), new ReflectionEventTransformer());
	}

}
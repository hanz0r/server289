package org.hanonator.net;

import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.AbstractTransformer;
import org.glassfish.grizzly.TransformationException;
import org.glassfish.grizzly.TransformationResult;
import org.glassfish.grizzly.attributes.AttributeStorage;
import org.glassfish.grizzly.filterchain.AbstractCodecFilter;
import org.hanonator.game.Event;

public class GameMessageBeanFilter extends AbstractCodecFilter<GameMessage, Event> {

	/**
	 * The map
	 */
	private static final Map<Integer, Class<? extends Event>> events = new HashMap<>();

	static {
		events.put(60, null);
	}

	public GameMessageBeanFilter() {
		super(new BeanDecoder(), new BeanEncoder());
	}

	public static class BeanDecoder extends AbstractTransformer<GameMessage, Event> {

		public String getName() {
			return "bean decoder";
		}

		public boolean hasInputRemaining(AttributeStorage storage, GameMessage input) {
			return input.hasRemaining();
		}

		@Override
		protected TransformationResult<GameMessage, Event> transformImpl(AttributeStorage storage, GameMessage input) throws TransformationException {
			return null;
		}
		
	}

	public static class BeanEncoder extends AbstractTransformer<Event, GameMessage> {

		public String getName() {
			return "bean encoder";
		}

		@Override
		protected TransformationResult<Event, GameMessage> transformImpl(AttributeStorage storage, Event input) throws TransformationException {
			return null;
		}

		public boolean hasInputRemaining(AttributeStorage storage, Event input) {
			return false;
		}
		
	}

}
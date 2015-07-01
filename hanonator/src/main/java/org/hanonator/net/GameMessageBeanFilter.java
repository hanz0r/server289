package org.hanonator.net;

import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.AbstractTransformer;
import org.glassfish.grizzly.TransformationException;
import org.glassfish.grizzly.TransformationResult;
import org.glassfish.grizzly.TransformationResult.Status;
import org.glassfish.grizzly.attributes.AttributeStorage;
import org.glassfish.grizzly.filterchain.AbstractCodecFilter;
import org.hanonator.game.event.Event;

public class GameMessageBeanFilter extends AbstractCodecFilter<GameMessage, Event> {

	/**
	 * Maps packet opcodes to the events that they trigger
	 */
	private static final Map<Integer, Class<? extends Event>> events = new HashMap<>();

	public GameMessageBeanFilter() {
		super(new BeanDecoder(), new BeanEncoder());
	}

	/**
	 * Tries to decode a game message into an event
	 * 
	 * @author user104
	 *
	 */
	public static class BeanDecoder extends AbstractTransformer<GameMessage, Event> {

		public String getName() {
			return "bean decoder";
		}

		public boolean hasInputRemaining(AttributeStorage storage, GameMessage input) {
			return input.hasRemaining();
		}

		@Override
		protected TransformationResult<GameMessage, Event> transformImpl(AttributeStorage storage, GameMessage input) throws TransformationException {
			Class<? extends Event> c = events.get(input.getId());
			
			/*
			 * If the class is null, there is an error
			 */
			if (c == null) {
				return new TransformationResult<GameMessage, Event>(Status.ERROR, null, input);
			}
			
			try {
				/*
				 * Create the event
				 */
				Event event = c.getConstructor(Integer.class).newInstance(input.getId());

				/*
				 * 
				 */
				// event.unpack();
				
				/*
				 * Push the transformation to the next filter
				 */
				System.out.println(event);
				return new TransformationResult<GameMessage, Event>(Status.COMPLETE, event, input);	
			} catch (Exception ex) {
				throw new TransformationException("Error parsing Event", ex);
			}
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
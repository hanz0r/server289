package org.hanonator.net;

import java.util.HashMap;
import java.util.Map;

import org.glassfish.grizzly.AbstractTransformer;
import org.glassfish.grizzly.TransformationException;
import org.glassfish.grizzly.TransformationResult;
import org.glassfish.grizzly.TransformationResult.Status;
import org.glassfish.grizzly.attributes.AttributeStorage;
import org.glassfish.grizzly.filterchain.AbstractCodecFilter;
import org.hanonator.game.event.GameEvent;

public class GameMessageBeanFilter extends AbstractCodecFilter<GameMessage, GameEvent> {

	/**
	 * Maps packet opcodes to the events that they trigger
	 */
	private static final Map<Integer, Class<? extends GameEvent>> events = new HashMap<>();

	public GameMessageBeanFilter() {
		super(new BeanDecoder(), new BeanEncoder());
	}

	/**
	 * Tries to decode a game message into an event
	 * 
	 * @author user104
	 *
	 */
	public static class BeanDecoder extends AbstractTransformer<GameMessage, GameEvent> {

		public String getName() {
			return "bean decoder";
		}

		public boolean hasInputRemaining(AttributeStorage storage, GameMessage input) {
			return input.hasRemaining();
		}

		@Override
		protected TransformationResult<GameMessage, GameEvent> transformImpl(AttributeStorage storage, GameMessage input) throws TransformationException {
			Class<? extends GameEvent> c = events.get(input.getId());
			
			/*
			 * If the class is null, there is an error
			 */
			if (c == null) {
				return new TransformationResult<GameMessage, GameEvent>(Status.ERROR, null, input);
			}
			
			try {
				/*
				 * Create the event
				 */
				GameEvent event = c.getConstructor(Integer.class).newInstance(input.getId());

				/*
				 * 
				 */
				// event.unpack();
				
				/*
				 * Push the transformation to the next filter
				 */
				System.out.println(event);
				return new TransformationResult<GameMessage, GameEvent>(Status.COMPLETE, event, input);	
			} catch (Exception ex) {
				throw new TransformationException("Error parsing Event", ex);
			}
		}
		
	}

	public static class BeanEncoder extends AbstractTransformer<GameEvent, GameMessage> {

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

}
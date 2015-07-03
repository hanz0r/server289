package org.hanonator.net;

import java.util.Iterator;

import org.glassfish.grizzly.AbstractTransformer;
import org.glassfish.grizzly.TransformationException;
import org.glassfish.grizzly.TransformationResult;
import org.glassfish.grizzly.TransformationResult.Status;
import org.glassfish.grizzly.attributes.AttributeStorage;
import org.glassfish.grizzly.filterchain.AbstractCodecFilter;
import org.hanonator.game.GameException;
import org.hanonator.game.event.Attribute;
import org.hanonator.game.event.DataParser;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.Template;
import org.hanonator.game.event.Templates;

public class GameMessageBeanFilter extends AbstractCodecFilter<GameMessage, GameEvent> {

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
			/*
			 * Create the event
			 */
			GameEvent event = new GameEvent(input.getId());

			/*
			 * Get the template for the message's id
			 */
			Template template = Templates.get(input.getId());
			
			/*
			 * Parse the data for all of the template's attributes
			 */
			for (Iterator<Attribute> iterator = template.iterator(); iterator.hasNext(); ) {
				Attribute attribute = iterator.next();
				
				/*
				 * The data parser
				 */
				DataParser<?> parser = attribute.getType().getParser();
				
				/*
				 * Parse the message
				 */
				try {
					event.setAttribute(attribute.getName(), parser.parse(input, null));
				} catch (GameException e) {
					e.printStackTrace();
				}
			}
			
			/*
			 * Push the transformation to the next filter
			 */
			return new TransformationResult<GameMessage, GameEvent>(Status.COMPLETE, event, input);
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
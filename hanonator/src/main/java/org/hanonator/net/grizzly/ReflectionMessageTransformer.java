package org.hanonator.net.grizzly;

import org.glassfish.grizzly.AbstractTransformer;
import org.glassfish.grizzly.TransformationException;
import org.glassfish.grizzly.TransformationResult;
import org.glassfish.grizzly.attributes.AttributeStorage;
import org.hanonator.game.event.GameEvent;
import org.hanonator.net.GameMessage;

public class ReflectionMessageTransformer extends AbstractTransformer<GameMessage, GameEvent> {

	@Override
	protected TransformationResult<GameMessage, GameEvent> transformImpl(AttributeStorage storage, GameMessage input) throws TransformationException {
		return null;
//		switch ((GameState) storage.getAttributes().getAttribute("game-state", () -> GameState.CONNECTED)) {
//		/*
//		 * 
//		 */
//		case INACTIVE:
//		case INITIALIZED:
//		case ACTIVE:
//			return null;
//			
//		/*
//		 * 
//		 */
//		case CONNECTED:
//		case LOGIN:
//			GameEvent login_event = new GameEvent(-1);
//			
//			/*
//			 * Add the payload to the event
//			 */
//			
//			
//			/*
//			 * 
//			 */
//			return new TransformationResult<GameMessage, GameEvent>(Status.COMPLETE, login_event, input);
//			
//		/*
//		 * The other cases should not happen
//		 */
//		default:
//			throw new TransformationException(new GameException(new IllegalStateException("cannot transform game message")));
//		}
	}
	
//		@Override
//		protected TransformationResult<GameMessage, GameEvent> transformImpl(AttributeStorage storage, GameMessage input) throws TransformationException {
//			/*
//			 * Create the event
//			 */
//			GameEvent event = new GameEvent(input.getId());
//
//			/*
//			 * Get the template for the message's id
//			 */
//			Template template = Templates.get(input.getId());
//			
//			/*
//			 * Parse the data for all of the template's attributes
//			 */
//			for (Iterator<Attribute> iterator = template.iterator(); iterator.hasNext(); ) {
//				Attribute attribute = iterator.next();
//				
//				/*
//				 * The data parser
//				 */
//				Filter<?> parser = attribute.getType().getParser();
//				
//				/*
//				 * Parse the message
//				 */
//				try {
//					event.setAttribute(attribute.getName(), parser.parse(input, null));
//				} catch (GameException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			/*
//			 * Push the transformation to the next filter
//			 */
//			return new TransformationResult<GameMessage, GameEvent>(Status.COMPLETE, event, input);
//		}

		public String getName() {
			return "bean decoder";
		}

		public boolean hasInputRemaining(AttributeStorage storage, GameMessage input) {
			return input.hasRemaining();
		}

}
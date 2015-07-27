package org.hanonator.net.transformer;

import org.hanonator.game.GameException;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.filter.FilterChain;
import org.hanonator.game.event.filter.impl.Templates;
import org.hanonator.net.GameMessage;

public class MessageTransformer implements Transformer<GameMessage, GameEvent> {

	@Override
	public GameEvent transform(GameMessage input) throws TransformationException {
		try {
			/*
			 * Get the filterchain for the corresponding event opcode
			 */
			FilterChain filterchain = Templates.get(input.getId());
		
			/*
			 * If the filterchain exists, use it and apply the filterchain 
			 */
			if (filterchain != null) {
				return filterchain.apply(input);
			}
			
			/*
			 * If it doesn't exist, return a new GameEvent with the raw payload as attribute
			 */
			else {
				GameEvent event = new GameEvent(input.getId());
				event.getAttributes().set("payload", input.getPayload().flip());
				return event;
			}
		} catch (GameException ex) {
			throw new TransformationException(ex);
		}
	}
}
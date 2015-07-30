package org.hanonator.net.transformer;

import javax.enterprise.context.ApplicationScoped;

import org.hanonator.game.GameException;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.filter.FilterChain;
import org.hanonator.game.event.filter.Filters;
import org.hanonator.net.io.Message;

@ApplicationScoped
public class MessageTransformer implements Transformer<Message, GameEvent> {

	@Override
	public GameEvent transform(Message input) throws TransformationException {
		try {
			/*
			 * Get the filterchain for the corresponding event opcode
			 */
			FilterChain filterchain = Filters.get(input.header().getOpcode());
		
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
				GameEvent event = new GameEvent(input.header().getOpcode());
				event.getAttributes().set("payload", input.payload());
				return event;
			}
		} catch (GameException ex) {
			throw new TransformationException(ex);
		}
	}
}
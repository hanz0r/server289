package org.hanonator.game.event.template;

import org.hanonator.game.GameException;
import org.hanonator.net.GameMessage;


@FunctionalInterface
public interface DataTypeFilter {

	/**
	 * 
	 * @param context
	 * @return
	 * @throws GameException
	 */
	public abstract Object parseDataType(GameMessage message) throws GameException;

}
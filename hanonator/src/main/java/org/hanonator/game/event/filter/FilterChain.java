package org.hanonator.game.event.filter;

import java.util.Iterator;
import java.util.List;

import org.hanonator.game.GameException;
import org.hanonator.game.User;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.filter.Filter.FilterResult;
import org.hanonator.net.GameMessage;

/**
 * 
 * @author user104
 *
 */
public class FilterChain implements Iterable<Filter> {

	/**
	 * The filter chain
	 */
	private final List<Filter> filters;

	protected FilterChain(List<Filter> filters) {
		this.filters = filters;
	}

	/**
	 * Process the filter chain
	 * 
	 * @param message
	 * @param user
	 * @throws GameException
	 */
	public void process(GameMessage message, User user) throws GameException {
		FilterChainContext context = new FilterChainContext(user, message, new GameEvent(message.getId()));
		
		for (Iterator<Filter> iterator = filters.iterator(); iterator.hasNext(); ) {
			FilterResult result = iterator.next().filter(context);
			if (result == FilterResult.CANCEL) {
				break;
			}
		}
	}
	
	@Override
	public Iterator<Filter> iterator() {
		return filters.iterator();
	}

}
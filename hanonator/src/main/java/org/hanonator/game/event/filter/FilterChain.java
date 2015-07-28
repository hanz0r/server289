package org.hanonator.game.event.filter;

import java.util.Iterator;
import java.util.List;

import org.hanonator.game.GameException;
import org.hanonator.game.event.GameEvent;
import org.hanonator.game.event.filter.Filter.FilterResult;
import org.hanonator.net.Message;

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
	public GameEvent apply(Message message) throws GameException {
		FilterChainContext context = new FilterChainContext(message, new GameEvent(message.header().getOpcode()));
		try {
		/*
		 * Apply all of the filters
		 */
		for (Iterator<Filter> iterator = filters.iterator(); iterator.hasNext(); ) {
			FilterResult result = iterator.next().filter(context);
			
			/*
			 * Stop the filterchain if a filter asks to stop
			 */
			if (result == FilterResult.STOP) {
				break;
			}
			
			/*
			 * Rewind the filterchain if the filter asks for it
			 */
			else if (result == FilterResult.REWIND) {
				iterator = filters.iterator();
			}
		}
		}catch (Exception ex) { ex.printStackTrace(); }
		/*
		 * Return the created event
		 */
		return context.getEvent();
	}
	
	@Override
	public Iterator<Filter> iterator() {
		return filters.iterator();
	}

}
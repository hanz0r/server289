package org.hanonator.game.event.template;

import org.hanonator.game.GameException;
import org.hanonator.game.event.filter.Filter;
import org.hanonator.game.event.filter.FilterChainContext;

/**
 * Filters the game message with a given template
 * 
 * @author user104
 */
public class AttributeFilter implements Filter {

	/**
	 * 
	 */
	private final Attribute attribute;

	public AttributeFilter(Attribute attribute) {
		this.attribute = attribute;
	}

	@Override
	public FilterResult filter(FilterChainContext context) throws GameException {
		/*
		 * The data parser
		 */
		DataTypeFilter parser = attribute.getType().getParser();
		
		/*
		 * Parse the message
		 */
		context.getEvent().setAttribute(attribute.getName(), parser.parseDataType(context.getMessage()));
		return FilterResult.SUCCESS;
	}

}
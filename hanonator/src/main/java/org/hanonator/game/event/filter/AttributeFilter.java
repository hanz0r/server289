package org.hanonator.game.event.filter;

import org.hanonator.game.GameException;
import org.hanonator.game.event.filter.DataFilter.DataType;

/**
 * AttributeFilter
 * 
 * @author Red
 *
 * @param <T>
 */
class AttributeFilter<T> implements Filter {
	
	/**
	 * Name of the attribute
	 */
	private final String attribute_name;
	
	/**
	 * The data type
	 */
	private final DataType datatype;

	public AttributeFilter(String attribute_name, DataType datatype) {
		this.attribute_name = attribute_name;
		this.datatype = datatype;
	}

	@Override
	public FilterResult filter(FilterChainContext ctx) throws GameException {
		ctx.getEvent().setAttribute(attribute_name, datatype.getFilter().filter(ctx.getMessage()));
		return FilterResult.SUCCESS;
	}

}
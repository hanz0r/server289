package org.hanonator.game.event.filter;

import org.hanonator.game.event.filter.Filter.DataType;

public class Attribute {
	
	/**
	 * Name of the attribute
	 */
	private final String name;
	
	/**
	 * Datatype of the attribute
	 */
	private final DataType type;

	public Attribute(String name, DataType type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public DataType getType() {
		return type;
	}
	
}
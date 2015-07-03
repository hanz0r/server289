package org.hanonator.game.event;

import org.hanonator.game.event.DataParser.DataType;

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
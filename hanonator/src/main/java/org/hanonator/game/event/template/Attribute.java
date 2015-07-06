package org.hanonator.game.event.template;

/**
 * Represents an attribute from a game event
 * @author user104
 *
 */
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
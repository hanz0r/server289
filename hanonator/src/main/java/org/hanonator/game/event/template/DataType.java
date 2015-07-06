package org.hanonator.game.event.template;

import org.hanonator.game.GameException;

/**
 * The type of data
 * 
 * @author user104
 *
 */
public enum DataType {
	/**
	 * Unsigned byte
	 */
	BYTE(m -> m.get() & 0xFF),
	
	/**
	 * Unsigned short (2 bytes)
	 */
	SHORT(m -> m.getShort() & 0xFFFF), 
	
	/**
	 * Unsigned integer (3 bytes)
	 */
	TRI(m -> {
		throw new GameException(new UnsupportedOperationException());
	}), 
	
	/**
	 * Signed integer (4 bytes)
	 */
	INT(m -> m.getInt()),
	
	/**
	 * Unsigned long (8 bytes)
	 */
	LONG(m -> (m.getInt() << 32) | m.getInt()),
	
	/**
	 * Gets the walking queue;
	 */
	QUEUE(m -> { throw new GameException(new UnsupportedOperationException()); }),
	
	/**
	 * Gets the walking queue;
	 */
	STRING(m -> { throw new GameException(new UnsupportedOperationException()); });
	
	/**
	 * The data parser
	 */
	private final DataTypeFilter parser;
	
	/**
	 * 
	 * @param parser
	 */
	private DataType(DataTypeFilter parser) {
		this.parser = parser;
	}

	public DataTypeFilter getParser() {
		return parser;
	}
	
}
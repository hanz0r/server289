package org.hanonator.game.event;

import org.hanonator.game.GameException;
import org.hanonator.game.User;
import org.hanonator.net.GameMessage;

@FunctionalInterface
public interface DataParser<T> {

	/**
	 * Parses the GameMessage to the given data type
	 * 
	 * @param payload
	 * @throws Exception
	 */
	public abstract T parse(GameMessage message, User user) throws GameException;

	/**
	 * 
	 * @param throwable
	 */
	default void handleException(GameException exception, GameMessage message, User user) {
		user.push(exception);
	}
	
	/**
	 * The type of data
	 * 
	 * @author user104
	 *
	 */
	public static enum DataType {
		/**
		 * Unsigned byte
		 */
		BYTE((m, u) -> m.get() & 0xFF),
		
		/**
		 * Unsigned short (2 bytes)
		 */
		SHORT((m, u) -> m.getShort() & 0xFFFF), 
		
		/**
		 * Unsigned integer (3 bytes)
		 */
		TRI((m, u) -> {
			throw new GameException(new UnsupportedOperationException());
		}), 
		
		/**
		 * Signed integer (4 bytes)
		 */
		INT((m, u) -> m.getInt()),
		
		/**
		 * Unsigned long (8 bytes)
		 */
		LONG((m, u) -> (m.getInt() << 32) | m.getInt()),
		
		/**
		 * Gets the walking queue;
		 */
		QUEUE((m, u) -> {
			throw new GameException(new UnsupportedOperationException());
		}),
		
		/**
		 * Gets the walking queue;
		 */
		STRING((m, u) -> {
			throw new GameException(new UnsupportedOperationException());
		});
		
		/**
		 * The data parser
		 */
		private final DataParser<?> parser;
		
		/**
		 * 
		 * @param parser
		 */
		private DataType(DataParser<?> parser) {
			this.parser = parser;
		}

		public DataParser<?> getParser() {
			return parser;
		}
		
	}

}
package org.hanonator.game.event.filter;

import org.hanonator.game.GameException;
import org.hanonator.net.Message;

@FunctionalInterface
interface DataFilter<T> {

	/**
	 * 
	 * @param context
	 * @return
	 * @throws GameException
	 */
	public abstract T filter(Message message) throws GameException;
	
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
		BYTE(m -> m.payload().get()),
		
		/**
		 * Unsigned short (2 bytes)
		 */
		SHORT(m -> m.payload().getShort()), 
		
		/**
		 * Unsigned integer (3 bytes)
		 */
		TRI(m -> {
			throw new GameException(new UnsupportedOperationException());
		}), 
		
		/**
		 * Signed integer (4 bytes)
		 */
		INT(m -> m.payload().getInt()),
		
		/**
		 * Unsigned long (8 bytes)
		 */
		LONG(m -> m.payload().getLong()),
		
		/**
		 * Gets the walking queue;
		 */
		COORDINATE_QUEUE(m -> {
			throw new GameException(new UnsupportedOperationException());
		}),
		
		/**
		 * Gets the walking queue;
		 */
		STRING(m -> {
			throw new GameException(new UnsupportedOperationException());
		});
		
		/**
		 * The data parser
		 */
		private final DataFilter<?> parser;
		
		/**
		 * 
		 * @param parser
		 */
		private DataType(DataFilter<?> parser) {
			this.parser = parser;
		}

		public DataFilter<?> getFilter() {
			return parser;
		}
		
	}
}
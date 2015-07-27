package org.hanonator.processor;

import org.hanonator.game.GameException;

@FunctionalInterface
public interface ProcessW<T> {

	/**
	 * 
	 * 
	 * @param element
	 * @return
	 * @throws GameException
	 */
	public abstract ProcessResult process(T element) throws GameException;

	/**
	 * The result returned when an item has been processed
	 *
	 * @author user104
	 */
	public static enum ProcessResult {
		/**
		 * Indicates the processor needs to retain the element
		 */
		RETAIN,
		
		/**
		 * Indicates the processor needs to remove the element from the stack
		 */
		REMOVE,
		
		/**
		 * Cancel the processing of further elements in the stack
		 */
		CANCEL;
	}

}
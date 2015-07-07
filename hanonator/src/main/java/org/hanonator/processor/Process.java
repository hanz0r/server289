package org.hanonator.processor;

import org.hanonator.game.GameException;
import org.hanonator.processor.AbstractProcessor.ProcessResult;

@FunctionalInterface
public interface Process<T> {

	/**
	 * 
	 * 
	 * @param element
	 * @return
	 * @throws GameException
	 */
	public abstract ProcessResult process(T element) throws GameException;

}
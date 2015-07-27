package org.hanonator.processor;

import java.util.Collection;
import java.util.List;

import org.hanonator.game.GameException;

public abstract class AsynchronousProcessor<T> implements Processor<T> {

	/**
	 * 
	 * @param element
	 */
	public abstract void processElement(T element);

	@Override
	public <R> List<R> process(Collection<T> collection) throws GameException {
		return null;
	}

}
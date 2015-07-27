package org.hanonator.processor;

import java.util.Collection;
import java.util.List;

import org.hanonator.game.GameException;

/**
 * 
 * 
 * @author Red
 */
@FunctionalInterface
public interface Processor<T> {

	/**
	 * 
	 * @param collection
	 * @return
	 */
	public abstract <R> List<R> process(Collection<T> collection) throws GameException;

}
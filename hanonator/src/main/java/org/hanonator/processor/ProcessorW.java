package org.hanonator.processor;

import java.util.List;
import java.util.function.Function;

import org.hanonator.game.GameException;

/**
 * 
 * @author user104
 *
 * @param <T>
 */
@FunctionalInterface
public interface ProcessorW<T> {

	/**
	 * Process elements
	 * 
	 * @throws GameException
	 */
	public abstract <R> List<R> process(Function<T, R> function) throws GameException;

}
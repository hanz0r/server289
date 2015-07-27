package org.hanonator.processor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.hanonator.game.GameException;

/**
 * 
 * 
 * @author Red
 */
@FunctionalInterface
public interface Processor<T, R> {

	/**
	 * Processes a single element
	 * 
	 * @param element
	 */
	public abstract R processElement(T element);

	/**
	 * 
	 * 
	 * @param collection
	 * @return
	 */
	default List<R> process(Collection<T> collection) throws GameException {
		return collection.stream().map(e -> processElement(e)).filter(e -> e != null).collect(Collectors.toList());
	}

}
package org.hanonator.processor;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

/**
 * Represents a Processor.
 * 
 * @author Red
 */
@FunctionalInterface
public interface Processor<T> {

	/**
	 * Processes an element
	 * 
	 * @param input
	 * @return
	 */
	public abstract <R> Process<R> process(Collection<T> input, Function<T, R> function);

	/**
	 * 
	 * @param input
	 * @return
	 */
	default <R> Process<R> process(T input, Function<T, R> function) {
		return process(Collections.singleton(input), function);
	}

}
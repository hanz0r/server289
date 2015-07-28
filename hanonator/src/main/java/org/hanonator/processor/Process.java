package org.hanonator.processor;

import java.util.Collection;

/**
 * A process to be submitted to the processor
 * 
 * @author Red
 *
 * @param <T>
 */
public interface Process<T> {

	/**
	 * Fetches the results. Will throw an {@link InterruptedException} when 
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public abstract Collection<T> results() throws InterruptedException;

	/**
	 * TODO
	 */
	default void interrupt() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
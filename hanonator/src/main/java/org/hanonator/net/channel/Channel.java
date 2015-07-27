package org.hanonator.net.channel;

import org.hanonator.game.GameException;
import org.hanonator.net.Stream;
import org.hanonator.net.transformer.Transformer;

/**
 * 
 * @author Red
 *
 * @param <T>
 */
public interface Channel<T> {
	
	/**
	 * Reads object
	 * 
	 * @param object
	 */
	public abstract <I> void read(I object) throws GameException;
	
	/**
	 * Writes object
	 * 
	 * @param object
	 */
	public abstract <I> void write(I object) throws GameException;

	/**
	 * Closes the stream
	 * 
	 * @throws GameException
	 */
	public abstract void close() throws GameException;
	
	/**
	 * Reads an object with a default transformer
	 * 
	 * @param object
	 */
	default <I> void read(I object, Transformer<I, ?> transformer) throws GameException {
		this.read(transformer.transform(object));
	}

	/**
	 * Write an object that needs to be transformed
	 * 
	 * @param object
	 */
	default <I> void write(I object, Transformer<I, ?> transformer) throws GameException {
		this.write(transformer.transform(object));
	}

	/**
	 * Open a stream with a given capacity and index
	 * 
	 * @param capacity
	 * @param index
	 * @return
	 */
	default Stream<T> stream(int index, int capacity) {
		return new Stream<>(this, capacity, index);
	}

	/**
	 * Open a raw data stream with a given capacity
	 * 
	 * @param capacity
	 * @return
	 */
	default Stream<T> stream(int capacity) {
		return new Stream<>(this, capacity, -1);
	}

	/**
	 * Opens a raw data stream with a default capacity of 64
	 * 
	 * @return
	 */
	default Stream<T> stream() {
		return new Stream<>(this, 64, -1);
	}

}
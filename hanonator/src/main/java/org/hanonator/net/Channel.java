package org.hanonator.net;

import java.io.IOException;

import org.hanonator.game.GameException;
import org.hanonator.net.io.Stream;
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
	public abstract void read(Object object) throws IOException;
	
	/**
	 * Writes object
	 * 
	 * @param object
	 */
	public abstract void write(Object object) throws IOException;
	
	/**
	 * Opens the connection
	 * 
	 * @param connection
	 * @throws IOException
	 */
	public abstract void bind(T connection) throws IOException;

	/**
	 * Closes the stream
	 * 
	 * @throws GameException
	 */
	public abstract void close() throws IOException;
	
	/**
	 * Reads an object with a default transformer
	 * 
	 * @param object
	 */
	default <I> void read(I object, Transformer<I, ?> transformer) throws IOException {
		this.read(transformer.transform(object));
	}

	/**
	 * Write an object that needs to be transformed
	 * 
	 * @param object
	 */
	default <I> void write(I object, Transformer<I, ?> transformer) throws IOException {
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
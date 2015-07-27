package org.hanonator.net;

import java.nio.ByteBuffer;

import org.hanonator.game.GameException;
import org.hanonator.net.channel.Channel;
import org.hanonator.net.transformer.Transformer;

/**
 * channel.open(OPCODE).
 * 
 * @author Red
 *
 */
public class Stream<T> {

	/**
	 * The channel
	 */
	private final Channel<T> channel;
	
	/**
	 * This stream's buffer
	 */
	private final ByteBuffer buffer;

	/**
	 * The opcode of the message sent through the stream
	 */
	private final int index;

	/**
	 * 
	 * @param channel
	 * @param capacity
	 */
	public Stream(Channel<T> channel, int capacity, int index) {
		this.channel = channel;
		this.buffer = ByteBuffer.allocate(capacity);
		this.index = index;
	}

	/**
	 * Write a ByteBuffer with a default transformer that returns its input
	 * 
	 * @param input
	 * @return
	 */
	public Stream<T> write(ByteBuffer input) {
		return write(input, i -> i);
	}

	/**
	 * Write a byte to the stream
	 * 
	 * @param input
	 * @return
	 */
	public Stream<T> write(byte input) {
		return write(input, i -> (ByteBuffer) ByteBuffer.allocate(1).put((byte) input).flip());
	}

	/**
	 * Write a short to the stream
	 * 
	 * @param input
	 * @return
	 */
	public Stream<T> write(short input) {
		return write(input, i -> (ByteBuffer) ByteBuffer.allocate(2).putShort(input).flip());
	}

	/**
	 * Write an int to the stream
	 * 
	 * @param input
	 * @return
	 */
	public Stream<T> write(int input) {
		return write(input, i -> (ByteBuffer) ByteBuffer.allocate(4).putInt(input).flip());
	}

	/**
	 * Write an int to the stream
	 * 
	 * @param input
	 * @return
	 */
	public Stream<T> write(long input) {
		return write(input, i -> (ByteBuffer) ByteBuffer.allocate(8).putLong(input).flip());
	}

	/**
	 * Write an object to the end of the stream
	 * 
	 * @param input
	 * @param transformer
	 * @return
	 */
	public <I> Stream<T> write(I input, Transformer<I, ByteBuffer> transformer) {
		buffer.put(transformer.transform(input));
		return this;
	}

	/**
	 * Write the message to the channel
	 * 
	 * @return
	 */
	public Stream<T> flush() throws GameException {
		channel.write(new GameMessage(index, buffer.capacity(), (ByteBuffer) buffer.flip()));
		System.out.println(new GameMessage(index, buffer.capacity(), (ByteBuffer) buffer.flip()).getPayload());
		return this;
	}

}
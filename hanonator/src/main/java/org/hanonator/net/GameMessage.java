package org.hanonator.net;

import java.nio.ByteBuffer;

public class GameMessage {

	/**
	 * Message sends/receives length in next byte
	 */
	public static final int VAR_LENGTH_BYTE = -1;
	
	/**
	 * Message sends/receives length in next 2 bytes
	 */
	public static final int VAR_LENGTH_SHORT = -2;

	/**
	 * opcode of the message
	 */
	private final int id;
	
	/**
	 * Length of the message
	 */
	private final int length;
	
	/**
	 * The payload
	 */
	private final ByteBuffer payload;

	public GameMessage(int id, int length, ByteBuffer payload) {
		this.id = id;
		this.length = length;
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "GameMessage [id=" + id + ", length=" + length + ", payload=" + payload + "]";
	}

	public byte get() {
		return payload.get();
	}

	public short getShort() {
		return payload.getShort();
	}

	public int getInt() {
		return payload.getInt();
	}

	public int getId() {
		return id;
	}

	public int getLength() {
		return length;
	}

	public ByteBuffer getPayload() {
		return payload;
	}

	public boolean hasRemaining() {
		return payload.hasRemaining();
	}

}
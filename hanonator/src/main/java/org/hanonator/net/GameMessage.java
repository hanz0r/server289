package org.hanonator.net;

import org.glassfish.grizzly.memory.HeapBuffer;

public class GameMessage {

	public static final int VAR_LENGTH_BYTE = -1;
	public static final int VAR_LENGTH_SHORT = -2;

	private final int id;
	private final int length;
	private final HeapBuffer payload;

	public GameMessage(int id, int length, HeapBuffer payload) {
		this.id = id;
		this.length = length;
		this.payload = payload;
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

	public boolean hasRemaining() {
		return payload.hasRemaining();
	}

	public HeapBuffer getPayload() {
		return payload;
	}

}
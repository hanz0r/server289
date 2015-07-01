package org.hanonator.net;

import org.glassfish.grizzly.memory.HeapBuffer;

public class GameMessage {

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

	@Override
	public String toString() {
		return "GameMessage [id=" + id + ", length=" + length + ", payload=" + payload.getShort() + "," + payload.getShort() + "]";
	}

	public boolean hasRemaining() {
		return payload.hasRemaining();
	}

}
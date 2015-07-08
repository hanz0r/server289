package org.hanonator.net;

import java.nio.ByteBuffer;

public class GameMessage {

	public static final int VAR_LENGTH_BYTE = -1;
	public static final int VAR_LENGTH_SHORT = -2;

	private final int id;
	private final int length;
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
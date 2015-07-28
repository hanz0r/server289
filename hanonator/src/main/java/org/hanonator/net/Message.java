package org.hanonator.net;

import java.nio.ByteBuffer;

/**
 * A message from a client
 * 
 * @author user104
 *
 */
public class Message {

	/**
	 * opcode of the message
	 */
	private final Header header;
	
	/**
	 * The payload
	 */
	private final ByteBuffer payload;

	public Message(Header header, ByteBuffer payload) {
		this.header = header;
		this.payload = payload;
	}

	/**
	 * Gets the size of the message
	 * 
	 * @return
	 */
	public int size() {
		return header.size();
	}

	public Header header() {
		return header;
	}

	public ByteBuffer payload() {
		return payload;
	}

	@Override
	public String toString() {
		return "GameMessage [header=" + header + ", payload=" + payload + "]";
	}

}
package org.hanonator.net.io;

import java.nio.ByteBuffer;

/**
 * Header of a message
 * 
 * @author user104
 *
 */
public class Header {

	/**
	 * Opcode of the message
	 */
	private final int opcode;
	
	/**
	 * Size of the message
	 */
	private final int size;

	/**
	 * The header meta data
	 */
	private final MetaData metaData;

	Header(int opcode, int size, MetaData metaData) {
		this.opcode = opcode;
		this.size = size;
		this.metaData = metaData;
	}
	/**
	 * 
	 * @param buffer
	 * @return
	 */
	public static Header wrap(ByteBuffer buffer) {
		return new Header(-1, buffer.capacity(), MetaData.STATIC);
	}

	/**
	 * 
	 * @param opcode
	 * @return
	 */
	public static Header get(ByteBuffer buffer) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	/**
	 * Try to estimate the header for the given data
	 * 
	 * @param buffer
	 * @return
	 */
	public static Header estimate(ByteBuffer buffer) {
		/*
		 * The opcode is always the first byte
		 */
		int opcode = buffer.get() & 0xFF;
		
		/*
		 * Slice the buffer
		 */
		ByteBuffer payload = buffer.slice();
		
		/*
		 * If there are no more bytes remaining, it is a 
		 */
		if (!payload.hasRemaining()) {
			return new Header(opcode, 0, MetaData.STATIC);
		}
		
		/*
		 * check and see if message is DEFINED_8
		 */
		else if (payload.remaining() >= 1) {
			int length = payload.get() & 0xFF;
			
			/*
			 * If the remaining capacity of the payload is of the same as the length specified by the
			 * first byte. Assume the message has its length sent in the first byte.
			 */
			if (payload.remaining() == length) {
				return new Header(opcode, buffer.get() & 0xFF , MetaData.DEFINED_8);
			}
			
			/*
			 * Rewind for the next attempt
			 */
			payload.rewind();
		}
		
		/*
		 * Check and see if the message is of type DEFINED_16
		 */
		else if (payload.remaining() >= 2) {
			int length = payload.getShort() & 0xFFFF;
			
			/*
			 * If the remaining capacity of the payload is of the same as the length specified by the
			 * first 2 bytes. Assume the message has its length sent in the first 2 bytes.
			 */
			if (payload.remaining() == length) {
				return new Header(opcode, buffer.getShort() & 0xFFFF, MetaData.DEFINED_16);
			}
			
			/*
			 * Rewind for the next attempt
			 */
			payload.rewind();
		}
		
		/*
		 * If none of the above. Make a header that contains the capacity of the buffer
		 */
		return new Header(opcode, payload.capacity(), MetaData.STATIC);
	}

	/**
	 * Attempts to get the message
	 * 
	 * @param buffer
	 * @return
	 */
	public Message resolve(ByteBuffer buffer) {
		return new Message(this, buffer);
	}

	/**
	 * Creates a message that conforms to this header's specifications
	 * 
	 * @return
	 */
	public Message create() {
		return new Message(this, ByteBuffer.allocate(size));
	}

	/**
	 * Gets the opcode
	 * 
	 * @return
	 */
	public int getOpcode() {
		return opcode;
	}

	/**
	 * Gets the size
	 * @return
	 */
	public int size() {
		return size;
	}

	/**
	 * Gets the MetaData
	 * 
	 * @return
	 */
	public MetaData getMetaData() {
		return metaData;
	}

	/**
	 * Holds information about how the Message defines its length
	 * 
	 * @author Red
	 */
	public static enum MetaData {
		STATIC, DEFINED_8, DEFINED_16, VOLATILE;
	}

}
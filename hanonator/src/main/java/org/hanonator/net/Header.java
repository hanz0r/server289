package org.hanonator.net;


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

	Header(int opcode, int size) {
		this.opcode = opcode;
		this.size = size;
	}

	public int getOpcode() {
		return opcode;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return "opcode=" + opcode + ", size=" + size;
	}

}
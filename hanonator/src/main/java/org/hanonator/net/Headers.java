package org.hanonator.net;

/**
 * 
 * @author user104
 */
public final class Headers {

	/**
	 * The lengths of each known packet
	 */
	private static final int[] lengths = new int[256];

	/**
	 * 
	 * @param opcode
	 * @return
	 */
	public static int get(int opcode) {
		return lengths[opcode];
	}

	/**
	 * 
	 * @param index
	 * @param length
	 */
	public static void put(int index, int length) {
		lengths[index] = length;
	}

}
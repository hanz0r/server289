package org.hanonator.net.util;

/**
 * 
 * @author user104
 *
 */
public class PacketLength {

	/**
	 * 
	 */
	private static final int[] lengths = new int[255];

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
package org.hanonator.game.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * 
 * @author user104
 */
public class Template implements Iterable<Attribute> {

	/**
	 * The list of attributes in the template
	 */
	private final List<Attribute> attributes = new ArrayList<>();

	/**
	 * The opcode of the message
	 */
	private final int opcode;

	/**
	 * The length of the message
	 */
	private final int length;

	public Template(int opcode, int length) {
		this.opcode = opcode;
		this.length = length;
	}

	public Iterator<Attribute> iterator() {
		return attributes.iterator();
	}

	public boolean add(Attribute e) {
		return attributes.add(e);
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public int getOpcode() {
		return opcode;
	}

	public int getLength() {
		return length;
	}

}
package org.hanonator.game.event.impl;

import org.hanonator.game.event.Event;
import org.hanonator.game.event.Serialized;
import org.hanonator.game.event.Serialized.DataType;

public class TestEvent extends Event {

	/**
	 * 
	 */
	@Serialized(index=0, value=DataType.SHORT)
	private int integerA;
	
	/**
	 * 
	 */
	@Serialized(index=0, value=DataType.SHORT)
	private int integerB;

	public TestEvent(int index) {
		super(index);
	}

	public int getIntegerA() {
		return integerA;
	}

	public int getIntegerB() {
		return integerB;
	}

	@Override
	public String toString() {
		return "TestEvent [integerA=" + integerA + ", integerB=" + integerB
				+ "]";
	}

}
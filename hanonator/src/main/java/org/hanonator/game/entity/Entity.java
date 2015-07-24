package org.hanonator.game.entity;

import org.hanonator.util.Attributes;

public abstract class Entity {
	
	/**
	 * The attributes
	 */
	private final Attributes attributes = new Attributes();

	/**
	 * The uuid
	 */
	private final int id;

	public Entity(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Attributes getAttributes() {
		return attributes;
	}

}
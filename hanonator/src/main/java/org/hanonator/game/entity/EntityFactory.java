package org.hanonator.game.entity;

import org.hanonator.game.GameException;

@FunctionalInterface
public interface EntityFactory<T extends Entity> {

	/**
	 * Creates an entity factory
	 * 
	 * @param uuid
	 * @throws GameException
	 */
	public abstract T create(int uuid) throws GameException;

}
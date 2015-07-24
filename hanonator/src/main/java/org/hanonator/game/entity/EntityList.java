package org.hanonator.game.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.hanonator.game.GameException;

/**
 * 
 * @author Red
 *
 * @param <T>
 */
public class EntityList<T extends Entity> implements Iterable<T>, Supplier<Stream<T>> {

	/**
	 * The maximum amount of active players
	 */
	public static final int PLAYER_LIMIT = 200;
	
	/**
	 * The maximumu amount of active npcs
	 */
	public static final int NPC_LIMIT = 2000;

	/**
	 * The collection of entities
	 */
	private final List<T> entities = new ArrayList<>();
	
	/**
	 * The entity factory
	 */
	private final EntityFactory<T> factory;

	/**
	 * The entity limit
	 */
	private final int limit;

	public EntityList(int limit, EntityFactory<T> factory) {
		this.limit = limit;
		this.factory = factory;
	}

	/**
	 * Allocate an entity
	 * 
	 * @return
	 */
	public T allocate() throws GameException {
		T entity = factory.create(this.nextUuid());
		add(entity);
		return entity;
	}
	
	/**
	 * Releases an entity
	 * 
	 * @param entity
	 */
	public void release(T entity) throws GameException {
		
	}
	
	/**
	 * Add an entity if there is still, otherwise throw an error
	 * 
	 * @param entity
	 * @throws GameException
	 */
	protected boolean add(T entity) throws GameException {
		if (entities.size() >= limit) {
			throw new GameException("list has reached its limit");
		}
		return entities.add(entity);
	}

	/**
	 * Attempt to remove an entity from the list
	 * @param entity
	 * @throws GameException
	 */
	protected boolean remove(T entity) throws GameException {
		if (!entities.contains(entity)) {
			throw new GameException("list does not contain entity");
		}
		return entities.remove(entity);
	}

	/**
	 * Gets the first free uuid
	 * 
	 * @return
	 * @throws GameException
	 */
	private int nextUuid() throws GameException {
		for (final AtomicInteger index = new AtomicInteger(1); index.get() < limit; index.incrementAndGet()) {
			if (get().anyMatch(e -> e.getId() == index.get())) {
				return index.get();
			}
		}
		throw new GameException("no free uuid available");
	}

	@Override
	public Stream<T> get() {
		return entities.stream();
	}

	@Override
	public Iterator<T> iterator() {
		return entities.iterator();
	}

}
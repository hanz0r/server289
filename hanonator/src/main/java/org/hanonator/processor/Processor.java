package org.hanonator.processor;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.hanonator.game.GameException;

/**
 * 
 * @author user104
 *
 * @param <T>
 */
public abstract class Processor<T> {

	/**
	 * List of items waiting to be processed
	 */
	private final Queue<T> stack = new LinkedList<>();

	/**
	 * 
	 * @param item
	 * @throws GameException
	 */
	public abstract ProcessResult process(T item) throws GameException;

	/**
	 * 
	 * @throws GameException
	 */
	public void process() throws GameException {
		for (Iterator<T> iterator = stack.iterator(); iterator.hasNext(); ) {
			ProcessResult result = process(iterator.next());
			
			if (result == ProcessResult.REMOVE) {
				iterator.remove();
			}
		}
	}

	/**
	 * 
	 * 
	 * @author user104
	 */
	public static enum ProcessResult {
		/**
		 * Indicates the processor needs to retain the element
		 */
		RETAIN,
		
		/**
		 * Indicates the processor needs to remove the element from the stack
		 */
		REMOVE;
	}

	public boolean add(T e) {
		return stack.add(e);
	}

	public Iterator<T> iterator() {
		return stack.iterator();
	}

	public boolean addAll(Collection<? extends T> c) {
		return stack.addAll(c);
	}

	public void clear() {
		stack.clear();
	}

	public int size() {
		return stack.size();
	}

}
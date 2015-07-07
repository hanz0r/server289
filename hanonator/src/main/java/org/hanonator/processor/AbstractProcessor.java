package org.hanonator.processor;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hanonator.game.GameException;

/**
 * 
 * @author user104
 *
 * @param <T>
 */
public abstract class AbstractProcessor<T> implements Processor<T>,
			Iterable<T>, Supplier<Stream<T>> {

	/**
	 * List of items waiting to be processed
	 */
	private final Queue<T> stack = new ArrayDeque<>();

	@Override
	public <R> List<R> process(Function<T, R> function) throws GameException {
		return this.get().map(function).collect(Collectors.toList());
	}

	public boolean offer(T e) {
		return stack.offer(e);
	}

	@Override
	public Stream<T> get() {
		return stack.stream();
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

	protected Queue<T> getStack() {
		return stack;
	}

	/**
	 * The result returned when an item has been processed
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
		REMOVE,
		
		/**
		 * Cancel the processing of further elements in the stack
		 */
		CANCEL;
	}

}

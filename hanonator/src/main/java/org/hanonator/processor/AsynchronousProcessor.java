package org.hanonator.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.function.Function;

import org.hanonator.game.GameException;

/**
 * Processes all the elements on the stack
 * 
 * @author user104
 *
 * @param <T>
 */
public class AsynchronousProcessor<T> extends AbstractProcessor<T> {

	/**
	 * The phaser for this class
	 */
	@SuppressWarnings("unused")
	private final Phaser phaser = new Phaser(1);
	
	/**
	 * The executor service
	 */
	private final ExecutorService service;
	
	public AsynchronousProcessor(ExecutorService service) {
		this.service = service;
	}
	
	public AsynchronousProcessor() {
		this (Executors.newCachedThreadPool());
	}

	@Override
	public <R> List<R> process(Function<T, R> function) throws GameException {
		try {
			final List<R> output = new ArrayList<>();
			
			/*
			 * Register amount of parties equal to the stack
			 */
			//phaser.bulkRegister(super.getStack().size());
			
			/*
			 * Only execute tasks when there are elements on the stack
			 */
			if (!super.getStack().isEmpty()) {
				final CyclicBarrier barrier = new CyclicBarrier(super.getStack().size() + 1);
				
				/*
				 * Loop over all the elements in the stack
				 */
				for (Iterator<T> iterator = super.iterator(); iterator.hasNext(); ) {
					/*
					 * Get the element. This was causing a shitload of problems, just saying
					 */
					T element = iterator.next();
					
					/*
					 * Add the result of the function of the processed element to the collection
					 */
					service.submit(() -> {
						try {
							/*
							 * Add the result to the output list
							 */
							output.add(function.apply(element));
							
							/*
							 * Call await on the cyclic barrier
							 */
							barrier.await();
						} catch (Exception ex) {
							throw new RuntimeException(ex);
						}
					});
					
					/*
					 * Remove the element from the stack
					 */
					iterator.remove();
				}
				
				/*
				 * Deregister the phaser after all the tasks have been completed
				 */
				// phaser.arriveAndDeregister();
				barrier.await();
			}
			return output;
		} catch (Exception ex) {
			throw new GameException(ex);
		}
	}

}
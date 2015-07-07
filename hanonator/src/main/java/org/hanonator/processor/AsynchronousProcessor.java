package org.hanonator.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.function.Function;

import org.hanonator.game.GameException;

/**
 * Processes all the 
 * @author user104
 *
 * @param <T>
 */
public class AsynchronousProcessor<T> extends AbstractProcessor<T> {

	/**
	 * The phaser for this class
	 */
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
		final List<R> output = new ArrayList<>();
		
		/*
		 * Register amount of parties equal to the stack
		 */
		phaser.bulkRegister(super.getStack().size());
		
		/*
		 * Loop over all the elements in the stack
		 */
		for (Iterator<T> iterator = super.iterator(); iterator.hasNext(); ) {
			/*
			 * Add the result of the function of the processed element to the collection
			 */
			service.submit(() -> output.add(function.apply(iterator.next())));
		}
		
		/*
		 * Deregister the phaser after all the tasks have been completed
		 */
		phaser.arriveAndDeregister();
		return output;
	}

}
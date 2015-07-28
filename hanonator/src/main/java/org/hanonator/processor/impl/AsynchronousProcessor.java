package org.hanonator.processor.impl;

import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import org.hanonator.processor.Process;
import org.hanonator.processor.Processor;

public class AsynchronousProcessor<T> implements Processor<T> {

	/**
	 * The executor service
	 */
	private final ExecutorService service;
	
	/**
	 * Creates a new processor with a given threadpool
	 * 
	 * @param service
	 */
	public AsynchronousProcessor(ExecutorService service) {
		this.service = service;
	}
	
	/**
	 * Creates a new processor with a cached threadpool
	 */
	public AsynchronousProcessor() {
		this (Executors.newCachedThreadPool());
	}

	@Override
	public <R> Process<R> process(Collection<T> input, Function<T, R> function) {
		/*
		 * Create the cyclic barrier
		 */
		final CountDownLatch latch = new CountDownLatch(input.size());
		
		/*
		 * Create the output
		 */
		final Queue<R> output = new ArrayBlockingQueue<>(input.size());
		
		/*
		 * Submit each task to the service
		 */
		input.forEach(i -> service.submit(() -> {
			/*
			 * Get the result
			 */
			R result = function.apply(i);
			
			/*
			 * Add the result of the processing to the output
			 */
			synchronized(output) {
				output.add(result);
			}
			
			/*
			 * Wait until the others are done executing
			 */
			latch.countDown();
			
			/*
			 * Return the current barrier count
			 */
			return latch.getCount();
		}));
		
		/*
		 * Return a new asynchronous process
		 */
		return new AsynchronousProcess<R>(output, latch);
	}

}
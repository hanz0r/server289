package org.hanonator.processor.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import org.hanonator.processor.Process;
import org.hanonator.processor.Processor;

@Deprecated
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
		final CyclicBarrier barrier = new CyclicBarrier(input.size() + 1);
		
		/*
		 * Create the output
		 */
		final List<R> output = new ArrayList<>();
		
		/*
		 * Submit each task to the service
		 */
		input.forEach(i -> service.submit(() -> {
			/*
			 * Add the result of the processing to the output
			 */
			output.add(function.apply(i));
			
			/*
			 * Wait until the others are done executing
			 */
			return barrier.await();
		}));
		
		/*
		 * Return a new asynchronous process
		 */
		return new AsynchronousProcess<R>(output, barrier);
	}

}
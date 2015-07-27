package org.hanonator.processor.impl;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

import org.hanonator.processor.Process;

@Deprecated
public class AsynchronousProcess<T> implements Process<T> {

	/**
	 * The collection of futures for each thread
	 */
	private final List<T> output;
	
	/**
	 * The cyclic barrier responsible for making sure 
	 */
	private final CyclicBarrier barrier;

	public AsynchronousProcess(List<T> output, CyclicBarrier barrier) {
		this.output = output;
		this.barrier = barrier;
	}

	@Override
	public List<T> results() throws InterruptedException {
		try {
			/*
			 * Wait until all of the threads have been finished executing
			 */
			barrier.await();
			
			/*
			 * Return the output once the cyclic barrier has been broken
			 */
			return output;
		} catch (Exception ex) {
			throw new InterruptedException(ex.getMessage());
		}
	}

}
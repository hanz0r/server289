package org.hanonator.processor.impl;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import org.hanonator.processor.Process;

public class AsynchronousProcess<T> implements Process<T> {

	/**
	 * The collection of futures for each thread
	 */
	private final Collection<T> output;
	
	/**
	 * The cyclic barrier responsible for making sure 
	 */
	private final CountDownLatch latch;

	public AsynchronousProcess(Collection<T> output, CountDownLatch latch) {
		this.output = output;
		this.latch = latch;
	}

	@Override
	public Collection<T> results() throws InterruptedException {
		try {
			/*
			 * Wait until all of the threads have been finished executing
			 */
			latch.await();
			
			/*
			 * Return the output once the cyclic barrier has been broken
			 */
			return output;
		} catch (Exception ex) {
			throw new InterruptedException(ex.getMessage());
		}
	}

}
package org.hanonator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hanonator.processor.Processor;
import org.hanonator.processor.impl.AsynchronousProcessor;

public class ProcessorTest extends TestCase {
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public ProcessorTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ProcessorTest.class);
	}

	private static final int TEST_AMOUNT = 100;

	public void testApp() throws InterruptedException {
		Processor<Integer> processor = new AsynchronousProcessor<>(Executors.newFixedThreadPool(4));
		for (int i = 0; i < TEST_AMOUNT; i++) {
			
			List<Integer> input = new ArrayList<>();
			for (int j = 0; j < 1000; j++) {
				input.add(j);
			}
			
			Collection<Integer> results = processor.process(input, g -> {
				try { Thread.sleep(10);}catch(Exception ex){}
				return g;
			}).results();
			System.out.printf("iteration[%d]: %d%n", i, results.size());
			results.clear();
		}
	}

}
package org.hanonator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hanonator.processor.Processor;
import org.hanonator.processor.impl.AsynchronousProcessor;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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

	public void testApp() throws InterruptedException {
		Processor<Integer> processor = new AsynchronousProcessor<>();
		
		List<Integer> input = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			input.add(i);
		}
		
		final Random random = new Random();
		List<Integer> list = processor.process(input, i -> {
			for (int j = 0; j < 10000; j++) {
				random.nextDouble();
			}
			return i;
		}).results();
		System.out.println(list.size());
	}

}
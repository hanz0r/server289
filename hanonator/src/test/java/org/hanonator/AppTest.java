package org.hanonator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hanonator.route.MapMatrix;
import org.hanonator.route.Matrix;
import org.hanonator.route.Point;
import org.hanonator.route.RouteFinder;
import org.hanonator.route.RouteNotFoundException;
import org.hanonator.route.impl.AStarRouteFinder;
import org.hanonator.route.impl.DijkstraRouteFinder;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

	public static final int ITERATIONS = 50_000;
	
	private static final int[] TEST_MATRIX = {
			0, 1, 1, 0, 1, 0, 0, 0, 0,
			0, 0, 1, 0, 0, 1, 0, 1, 0,
			1, 0, 1, 1, 0, 1, 0, 1, 0,
			1, 0, 0, 0, 0, 1, 0, 1, 0,
			1, 0, 1, 0, 1, 1, 0, 1, 0,
			1, 0, 1, 0, 0, 0, 0, 1, 0,
			0, 0, 1, 0, 1, 1, 1, 1, 0,
			0, 1, 1, 0, 1, 1, 1, 1, 0,
			0, 1, 1, 0, 0, 0, 0, 0, 0,
	};
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		try {
			Matrix matrix = new MapMatrix(TEST_MATRIX, 9);
			
			long total = calculateTimeTaken(new DijkstraRouteFinder(), matrix, ITERATIONS);
			System.out.println("Dijkstra sum:" + (total / 1_000_000) + "ms avg: " + (total / ITERATIONS) + "ns (0," + (total / ITERATIONS / 10_000) + "ms)");
		
			long deez_nuts = calculateTimeTaken(new AStarRouteFinder(), matrix, ITERATIONS);
			System.out.println("A* sum:" + (deez_nuts / 1_000_000) + "ms avg: " + (deez_nuts / ITERATIONS) + "ns (0," + (deez_nuts / ITERATIONS / 10_000) + "ms)");
			
		} catch (RouteNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	private long calculateTimeTaken(RouteFinder finder, Matrix matrix, int iterations) throws RouteNotFoundException {
		long total = 0;
		for (int i = 0; i < ITERATIONS; i++) {
			long delta = System.nanoTime();
			finder.find(matrix, new Point(0, 0), new Point(3, 7));
			total += (System.nanoTime() - delta);
		}
		return total;
	}

}

package org.hanonator;

import org.hanonator.route.MapMatrix;
import org.hanonator.route.Matrix;
import org.hanonator.route.Point;
import org.hanonator.route.Route;
import org.hanonator.route.RouteFinder;
import org.hanonator.route.RouteNotFoundException;
import org.hanonator.route.impl.DijkstraRouteFinder;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	
	private static final int[] TEST_MATRIX = {
			0, 1, 1, 0,
			0, 0, 1, 0,
			1, 0, 1, 1,
			1, 0, 0, 0,
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
			Matrix matrix = new MapMatrix(TEST_MATRIX, 4);
			RouteFinder finder = new DijkstraRouteFinder();
		
			Route route = finder.find(matrix, new Point(0, 0), new Point(3, 3));
			System.out.println(route.get().count());
		} catch (RouteNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}

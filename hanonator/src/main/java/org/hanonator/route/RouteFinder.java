package org.hanonator.route;

@FunctionalInterface
public interface RouteFinder {

	/**
	 * Attempts to find the shortest route between two points in a given
	 * matrix.
	 * 
	 * @param matrix
	 * @param source
	 * @param destination
	 * @return
	 * @throws RouteNotFoundException when no route is found
	 */
	public abstract Route find(Matrix matrix, Point source, Point destination) throws RouteNotFoundException;

}
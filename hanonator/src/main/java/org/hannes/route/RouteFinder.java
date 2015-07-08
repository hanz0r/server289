package org.hannes.route;

@FunctionalInterface
public interface RouteFinder {

	/**
	 * Find the quickest route in the given for the given matrix
	 * 
	 * @param matrix
	 * @return
	 * @throws RouteNotFoundException
	 */
	public abstract Route findRoute(Matrix matrix, Point source, Point destination) throws RouteNotFoundException;

}
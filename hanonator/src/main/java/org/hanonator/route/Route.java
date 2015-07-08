package org.hanonator.route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Route implements Iterable<Point>, Supplier<Stream<Point>> {

	/**
	 * The collection of points in the route
	 */
	private final List<Point> route = new ArrayList<Point>();

	/**
	 * 
	 * @param points
	 */
	private Route(Collection<Point> points) {
		route.addAll(points);
	}

	/**
	 * Walks the chain of the node
	 * 
	 * @param node
	 * @return
	 */
	public static Route walk(Node node) {
		return walk(node, new ArrayList<>());
	}

	/**
	 * Walk a node and add the links to the list
	 * 
	 * @param node
	 * @param chain
	 * @return
	 */
	private static Route walk(Node node, List<Point> chain) {
		/*
		 * Add the node's point
		 */
		chain.add(node.getPoint());
		
		/*
		 * If there is no link left in the chain, return route
		 */
		if (node.getLink() == null) {
			return new Route(chain);
		}
		
		/*
		 * If there is a link present, keep walking until we find head of the chain
		 */
		return walk(node.getLink(), chain);
	}

	@Override
	public Stream<Point> get() {
		return route.stream();
	}

	@Override
	public Iterator<Point> iterator() {
		return route.iterator();
	}

}
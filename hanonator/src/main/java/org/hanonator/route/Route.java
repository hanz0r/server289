package org.hanonator.route;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Represents a route from a source to a destination position
 * 
 * @author Red
 */
public class Route implements Iterable<Point>, Supplier<Stream<Point>> {

	/**
	 * The collection of points in the route
	 */
	private final Deque<Point> route = new LinkedList<Point>();

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
		return walk(node, new LinkedList<>());
	}

	/**
	 * Walk a node and add the links to the list
	 * 
	 * @param node
	 * @param chain
	 * @return
	 */
	private static Route walk(Node node, Deque<Point> chain) {
		/*
		 * Add the node's point
		 */
		chain.addFirst(node.getPoint());
		
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
package org.hannes.route;

public class Node {

	/**
	 * 
	 */
	private Node previous;
	
	/**
	 * 
	 */
	private double distance;
	
	/**
	 * 
	 */
	private final Point coordinate;

	public Node(Point coordinate) {
		this.coordinate = coordinate;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	
}
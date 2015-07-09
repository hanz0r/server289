package org.hanonator.route;

public class Node implements Comparable<Node> {

	/**
	 * Previous link in the chain
	 */
	private Node link;
	
	/**
	 * Point on the matrix
	 */
	private final Point point;
	
	/**
	 * Weight of the node
	 */
	private int weight;
	
	/**
	 * Indicates this node has been visited
	 */
	private boolean closed;

	/**
	 * Create a node with the default weight (int.MAX_VALUE)
	 * and no link value
	 * 
	 * @param point
	 */
	public Node(Point point) {
		this (point, null, Integer.MAX_VALUE);
	}

	/**
	 * Don't allow users to automatically create a node with
	 * a given weight
	 * 
	 * @param point
	 * @param link
	 * @param weight
	 */
	private Node(Point point, Node link, int weight) {
		this.point = point;
		this.link = link;
		this.weight = weight;
	}

	/**
	 * Creates a source node from a given point. A source node is
	 * a default node with weight 0
	 * 
	 * @param point
	 * @return
	 */
	public static Node createSourceNode(Point point) {
		return new Node(point, null, 0);
	}

	/**
	 * Checks if the nodes are neighbours
	 * 
	 * @param peek
	 * @return
	 */
	public boolean adjacent(Node peek) {
		for (Direction dir : Direction.values()) {
			if (point.offset(dir).equals(peek.getPoint())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Node other) {
		return Integer.compare(weight, other.weight);
	}

	@Override
	public String toString() {
		return "Node [point=" + point + ", weight=" + weight + ", link=" + link + "]";
	}
	
	@Override
	public int hashCode() {
		return point.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}

	public Point getPoint() {
		return point;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node getLink() {
		return link;
	}

	public void setLink(Node link) {
		this.link = link;
	}

	public boolean closed() {
		return closed;
	}

	public void close() {
		this.closed = true;
	}

	/**
	 * Util for looping over neighbour nodes
	 * 
	 * @author Red
	 */
	public enum Direction {
		LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);

		/**
		 * Offset along the x axis
		 */
		private final int x;
		
		/**
		 * Offset along the y axis
		 */
		private final int y;

		private Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	
	}

}
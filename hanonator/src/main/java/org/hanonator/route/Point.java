package org.hanonator.route;

import org.hanonator.route.Node.Direction;

public class Point implements Comparable<Point> {

	/**
	 * The absolute X coordinate
	 */
	private final int x;
	
	/**
	 * The absolute Y coordinate
	 */
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a new Point object with offset by the values specified in Direction
	 * 
	 * @param dir
	 * @return
	 */
	public Point offset(Direction dir, int distance) {
		return new Point(x + dir.getX() * distance, y + dir.getY() * distance);
	}

	/**
	 * Creates a new Point object with offset by the values specified in Direction
	 * 
	 * @param dir
	 * @return
	 */
	public Point offset(Direction dir) {
		return offset(dir, 1);
	}

	/**
	 * Calculates the Manhattan distance between this point and the given other point
	 * 
	 * @param other
	 * @return
	 */
	public int distance(Point other) {
		return Math.abs(other.x - x) + Math.abs(other.y - y);
	}

	@Override
	public int compareTo(Point point) {
		if (x == point.getX()) {
			return Integer.compare(y, point.y);
		}
		return Integer.compare(x, point.x);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
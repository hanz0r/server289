package org.hannes.route;

import java.awt.geom.Point2D;

public class Point {

	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean neighbour(Point p) {
		for (Direction direction : Direction.values()) {
			if (this.offset(direction.getX(), direction.getY()).equals(p))
				return true;
		}
		return false;
	}

	public double distance(Point neighbour) {
		return Point2D.distance(x, y, neighbour.x, neighbour.y);
	}

	public Point offset(int x, int y) {
		return new Point(this.x + x, this.y + y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

}
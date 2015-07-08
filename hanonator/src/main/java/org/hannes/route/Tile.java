package org.hannes.route;

public class Tile {

	private final Point point;
	private final Attribute attribute;

	public Tile(Point point, Attribute attribute) {
		this.point = point;
		this.attribute = attribute;
	}

	public Point getPoint() {
		return point;
	}

	public Attribute getAttribute() {
		return attribute;
	}

}
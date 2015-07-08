package org.hanonator.route;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 
 * @author Red
 */
public interface Matrix extends Iterable<Point>, Supplier<Stream<Point>> {
	
	/**
	 * Map all the nodes to their relevant positions on the matrix
	 * 
	 * @return
	 */
	public abstract NodeQueue createTemplate();

	/**
	 * TODO
	 * 
	 * @param point
	 * @return
	 */
	public abstract Tile get(Point point);

	/**
	 * Represents a tile and its contents
	 * 
	 * @author Red
	 */
	public static enum Tile {
		TERRAIN, WALL;
	}

}
package org.hanonator.route;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Matrix with a hash map implementation
 * 
 * @author Red
 *
 */
public class MapMatrix implements Matrix {

	/**
	 * The collection of points in the matrix
	 */
	private final Map<Point, Tile> points = new HashMap<>();

	/**
	 * Creates a matrix for the given one dimensional array where Tile values are represented
	 * by their ordinal
	 * 
	 * @param matrix
	 * @param width
	 */
	public MapMatrix(int[] matrix, int width) {
		for (int index = 0; index < matrix.length; index++) {
			points.put(new Point(index / width, index % width), Tile.values()[matrix[index]]);
		}
	}

	@Override
	public NodeQueue createTemplate() {
		return new NodeQueue(points.entrySet().stream().filter(e -> e.getValue() != Tile.WALL).map(e -> new Node(e.getKey())).collect(Collectors.toList()));
	}

	@Override
	public Tile get(Point point) {
		return points.get(point);
	}

	@Override
	public Iterator<Point> iterator() {
		return points.keySet().iterator();
	}

	@Override
	public Stream<Point> get() {
		return points.keySet().stream();
	}

}
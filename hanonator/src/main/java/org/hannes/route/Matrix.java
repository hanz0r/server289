package org.hannes.route;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Matrix implements Iterable<Tile>, Supplier<Stream<Tile>> {
	
	/**
	 * The tiles
	 */
	private final Tile[] tiles;

	/**
	 * The width of the matrix
	 */
	private final int width;
	
	/**
	 * The height of the matrix
	 */
	private final int height;

	/**
	 * 
	 * @param tiles
	 * @param width
	 * @param height
	 */
	public Matrix(Tile[] tiles, int width, int height) {
		this.tiles = tiles;
		this.width = width;
		this.height = height;
	}

	/**
	 * 
	 * @param tiles
	 * @return
	 */
	public static Matrix wrap(Tile[][] tiles) {
		int height = tiles.length;
		int width = tiles[0].length;
		Tile[] out = new Tile[height * width];
		for (int i = 0; i < tiles.length; i++) {
			System.arraycopy(tiles[i], 0, out, i, width);
		}
		return new Matrix(out, width, height);
	}

	/**
	 * 
	 * @param tiles
	 * @return
	 */
	public static Matrix wrap(int[][] tiles) {
		int height = tiles.length;
		int width = tiles[0].length;
		Tile[] out = new Tile[height * width];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				out[i * width + j] = new Tile(new Point(i, j), Attribute.values()[tiles[i][j]]);
			}
		}
		return new Matrix(out, width, height);
	}

	/**
	 * Creates a matrix for a given set of tile flags and a set width and height
	 * 
	 * @param tiles
	 * @param width
	 * @param height
	 * @return
	 */
	public static Matrix wrap(int[] tiles, int width, int height) {
		Tile[] out = new Tile[tiles.length];
		for (int i = 0; i < tiles.length; i++) {
				out[i] = new Tile(new Point(i / width, i % width), Attribute.values()[tiles[i]]);
		}
		return new Matrix(out, width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public Stream<Tile> get() {
		return Arrays.stream(tiles);
	}

	@Override
	public Iterator<Tile> iterator() {
		return Arrays.asList(tiles).iterator();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < tiles.length; index++) {
			builder.append(tiles[index].toString()).append(index % width == width -1 ? "\n" : " ");
		}
		return builder.toString();
	}

	public int capacity() {
		return tiles.length;
	}

}
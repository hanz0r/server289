package org.hannes.route;

import java.util.ArrayList;
import java.util.List;

public class Route {

	private final List<Node> nodes = new ArrayList<>();

	public Route(Node source) {
		nodes.add(source);
	}

	public void fill(Matrix matrix) {
		matrix.forEach(t -> nodes.add(new Node(t.getPoint())));
	}

}
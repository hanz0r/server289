package org.hanonator.route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * 
 * @author Red
 */
public class NodeQueue implements Iterable<Node>, Supplier<Stream<Node>> {

	/**
	 * The collection of nodes mapped by their point on the matrix
	 */
	private final List<Node> nodes = new ArrayList<>();
	
	/**
	 * The head of the queue
	 */
	private Node head;

	/**
	 * Creates a NodeMap for the given collection of nodes. It is assumed
	 * only valid walkable nodes have been provided
	 * 
	 * @param nodes_
	 */
	public NodeQueue(Collection<Node> nodes_) {
		nodes_.stream().forEach(n -> nodes.add(n));
	}

	/**
	 * Sees if the given node is present in the stack
	 * @param node
	 * @return
	 */
	public boolean contains(Node node) {
		return nodes.contains(node);
	}

	/**
	 * Adds a node to the tail of the stack
	 * @param node
	 */
	public void offer(Node node) {
		nodes.add(node);
	}

	/**
	 * Gets a list of all the neighbouring nodes
	 * 
	 * @return
	 */
	public List<Node> adjacent() {
		return adjacent(head);
	}

	/**
	 * Gets a list of all the neighbouring nodes
	 * 
	 * @return
	 */
	public List<Node> adjacent(Node node) {
		return nodes.stream().filter(node::adjacent).collect(Collectors.toList());
	}

	/**
	 * Gets the Node with the lowest weight available
	 * 
	 * @return
	 */
	public Node peek() {
		head = nodes.stream().min(Node::compareTo).get();
		return head;
	}

	/**
	 * Removes the current node
	 */
	public void release() {
		nodes.remove(head);
	}

	/**
	 * Release a given node
	 * @param node
	 */
	public void release(Node node) {
		nodes.remove(node);
	}

	/**
	 * Checks to see if there are nodes waiting in the stack
	 * 
	 * @return
	 */
	public boolean hasNext() {
		return !nodes.isEmpty();
	}

	/**
	 * Create a source node for the given position
	 * 
	 * @param point
	 * @return
	 */
	public NodeQueue createSourceNode(Point point) {
		Node sourceNode = Node.createSourceNode(point);
		nodes.remove(sourceNode);
		nodes.add(sourceNode);
		return this;
	}

	@Override
	public Stream<Node> get() {
		return nodes.stream();
	}

	@Override
	public Iterator<Node> iterator() {
		return nodes.iterator();
	}

}
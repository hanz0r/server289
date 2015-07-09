package org.hanonator.route.impl;

import java.util.ArrayList;

import org.hanonator.route.Matrix;
import org.hanonator.route.Node;
import org.hanonator.route.NodeQueue;
import org.hanonator.route.Point;
import org.hanonator.route.Route;
import org.hanonator.route.RouteFinder;
import org.hanonator.route.RouteNotFoundException;

/**
 * function A*(start,goal)
 *     closedset := the empty set 	// The set of nodes already evaluated.
 *     openset := {start}    		// The set of tentative nodes to be evaluated, initially containing the start node
 *     came_from := the empty map	// The map of navigated nodes.
 * 
 *    g_score[start] := 0    		// Cost from start along best known path.
 *    								// Estimated total cost from start to goal through y.
 *    f_score[start] := g_score[start] + heuristic_cost_estimate(start, goal)
 *     
 *    while openset is not empty
 *        current := the node in openset having the lowest f_score[] value
 *        if current = goal
 *            return reconstruct_path(came_from, goal)
 *         
 *        remove current from openset
 *        add current to closedset
 *        for each neighbor in neighbor_nodes(current)
 *            if neighbor in closedset
 *                continue
 *            tentative_g_score := g_score[current] + dist_between(current,neighbor)
 * 
 *            if neighbor not in openset or tentative_g_score < g_score[neighbor] 
 *                came_from[neighbor] := current
 *                g_score[neighbor] := tentative_g_score
 *                f_score[neighbor] := g_score[neighbor] + heuristic_cost_estimate(neighbor, goal)
 *                if neighbor not in openset
 *                    add neighbor to openset
 * 
 *    return failure
 *
 * function reconstruct_path(came_from,current)
 *    total_path := [current]
 *    while current in came_from:
 *        current := came_from[current]
 *         total_path.append(current)
 *     return total_path
 * @author user104
 *
 */
public class AStarRouteFinder implements RouteFinder {

	@Override
	public Route find(Matrix matrix, Point source, Point destination) throws RouteNotFoundException {
		NodeQueue nodes = matrix.createTemplate().createSourceNode(source);
		NodeQueue open = new NodeQueue(new ArrayList<>()).createSourceNode(source);
		
		while (open.hasNext()) {
			Node next = open.peek();
			
			/*
			 * If source equals destination, return route with only source node
			 */
			if (next.getPoint().equals(destination)) {
				return Route.walk(next);
			}
			
			/*
			 * Close the current node
			 */
			next.close();
			
			/*
			 * Walk over all of the adjacent nodes
			 */
			for (Node neighbour : nodes.adjacent(next)) {
				if (neighbour.closed()) {
					continue;
				}
				
				/*
				 * Calculate the tentative weight
				 */
				int weight = next.getWeight() + 1;
				
				/*
				 * If the neighbour is walkable or the neighbour's weight is larger than the tentative weight
				 * add the neighbour to the available nodes and set its link/weight
				 */
				if (open.contains(neighbour) || weight < neighbour.getWeight()) {
					neighbour.setLink(next);
					neighbour.setWeight(weight);
					
					/*
					 * Add the neighbour to available nodes
					 */
					if (!open.contains(neighbour)) {
						open.offer(neighbour);
					}
				}
			}

			/*
			 * Remove the node from the stack
			 */
			open.release();
		}
		
		/*
		 * If end of method is reached, no suitable path has been found
		 */
		throw new RouteNotFoundException("route to destination not found");
	}

}

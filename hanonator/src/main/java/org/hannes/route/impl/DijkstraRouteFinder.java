package org.hannes.route.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

import org.hannes.route.Attribute;
import org.hannes.route.Matrix;
import org.hannes.route.Point;
import org.hannes.route.Route;
import org.hannes.route.RouteFinder;
import org.hannes.route.RouteNotFoundException;
import org.hannes.route.Tile;


/**
 *  1  function Dijkstra(Graph, source):
 *   2
  *  3      dist[source] <- 0                       // Distance from source to source
  *  4      prev[source] <- undefined               // Previous node in optimal path initialization
  *  5
  *  6      for each vertex v in Graph:  			// Initialization
  *  7          if v != source:           			// Where v has not yet been removed from Q (unvisited nodes)
  *  8              dist[v] <- infinity             // Unknown distance function from source to v
  *  9              prev[v] <- undefined            // Previous node in optimal path from source
  * 10          end if 
  * 11          add v to Q                     		// All nodes initially in Q (unvisited nodes)
  * 12      end for
  * 13      
  * 14      while Q is not empty:
  * 15          u <- vertex in Q with min dist[u]  	// Source node in first case
  * 16          remove u from Q 
  * 17          
  * 18          for each neighbor v of u:           // where v is still in Q.
  * 19              alt <- dist[u] + length(u, v)
  * 20              if alt < dist[v]:               // A shorter path to v has been found
  * 21                  dist[v] <- alt 
  * 22                  prev[v] <- u 
  * 23              end if
  * 24          end for
  * 25      end while
  * 26
  * 27      return dist[], prev[]
  * 28
  * 29  end function
 * @author user104
 *
 */
public class DijkstraRouteFinder implements RouteFinder {

	@Override
	public Route findRoute(Matrix matrix, Point source, Point destination) throws RouteNotFoundException {
		Map<Point, Double> distance = new HashMap<>();
		Map<Point, Point> previous = new HashMap<>();
		Queue<Point> unvisited = new ArrayBlockingQueue<>(matrix.capacity());
		
		distance.put(source, 0D);
		
		for (Tile tile : matrix) {
			if (tile.getAttribute() == Attribute.WALL) {
				continue;
			}
			
			if (!tile.getPoint().equals(source)) {
				distance.put(tile.getPoint(), Double.MAX_VALUE);
			}
			unvisited.add(tile.getPoint());
			System.out.println(tile.getPoint().hashCode());
		}
		
		System.out.println("distance");
		distance.entrySet().forEach(System.out::println);
		System.out.println("previous");
		previous.entrySet().forEach(System.out::println);
		
		System.out.println("walk");
		while (!unvisited.isEmpty()) {
			Point tile = unvisited.poll();
//			Point tile = getMinimumCost(distance, unvisited);
//			unvisited.remove(tile);

			if (unvisited.contains(tile)) {
				System.out.println("hiii");
			}
			System.out.println("working with: " + tile);
			
			if (tile.equals(destination)) {
				System.out.println("lol");
				break;
			}
			
			for (Point neighbour : neighbours(tile, unvisited)) {
				System.out.println("neighbours " + neighbour);
				
				double alt = neighbour.distance(tile);
				if (alt < distance.get(tile)) {
					distance.put(neighbour, alt);
					previous.put(neighbour, tile);
				}
			}
		}
		
		System.out.println("distance");
		distance.entrySet().forEach(System.out::println);
		System.out.println("previous");
		previous.entrySet().forEach(System.out::println);
		
//		
//		while (!unvisited.isEmpty()) {
//			//Tile tile = unvisited.poll();
//			Tile tile = getMinimumCost(unvisited);
//			unvisited.remove(tile);
//			
//			System.out.println(tile.getPoint());
//			
//			for (Tile neighbour : tile.getNeighbours(matrix)) {
//				double alt = distance.get(tile.getPoint()) + tile.distance(neighbour);
//				if (alt < distance.get(tile.getPoint())) {
//					distance.put(neighbour.getPoint(), alt);
//					previous.put(neighbour.getPoint(), tile.getPoint());
//				}
//			}
//		}
//		
		return null;
	}

	private List<Point> neighbours(Point tile, Queue<Point> tiles) {
		return tiles.stream().filter(p -> p.neighbour(tile)).collect(Collectors.toList());
	}

	private Point getMinimumCost(Map<Point, Double> map, Queue<Point> tiles) {
		return map.entrySet().stream().filter(e -> map.containsKey(e.getKey())).min((t1, t2) -> t1.getValue().compareTo(t2.getValue())).get().getKey();
	}


}
package org.hannes.route.impl;

import org.hannes.route.Matrix;
import org.hannes.route.Point;
import org.hannes.route.Route;
import org.hannes.route.RouteFinder;
import org.hannes.route.RouteNotFoundException;

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
public class Dijkstra2RouteFinder implements RouteFinder {

	@Override
	public Route findRoute(Matrix matrix, Point source, Point destination) throws RouteNotFoundException {
		Route route = new Route();
		
		return null;
	}

}
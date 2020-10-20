package finalProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Finds path in Graph from one room to another.
 */
public class DijPath {
	/** IDs used for the creation of origin and dest nodes. */
	private final int ORIGIN_ID = -1;
	private final int DEST_ID = -2;

	private Graph g;

	/**
	 * Class constructor.
	 *
	 * @param g The graph used to find the path between two rooms.
	 */
	public DijPath(Graph g) {
		this.g = g;
	}
	
	/**
	 * Public method used to find the shortest path between two rooms
	 *
	 * @param origin Name of starting room.
	 * @param dest Name of destination room.
	 * @return Path object representing path from origin to dest, or null if origin or dest are not in Graph or no path is found from origin to dest.
	 */
	public Path findPath(String origin, String dest)
	{
		Edge originEdge = this.g.getEdge(origin);
		Edge destEdge = this.g.getEdge(dest);

		// Edge cases
		if (originEdge == null || destEdge == null)
		{
			return null;
		}
		if (originEdge.hasRoom(dest))
		{
			return new Path(origin, dest);
		}
		
		/** Priority queue of DijPair representing directed edges with weight that sorts ascending by weight. */
		PriorityQueue<DijPair> q = new PriorityQueue<>(10, new Comparator<DijPair>() {
				public int compare(DijPair p1, DijPair p2)
				{
					// Sort from least weight to greatest weight
					if (p1.weight < p2.weight) return -1;
					if (p2.weight < p1.weight) return 1;
					return 0;
				}
		});
		
		/** Represents origin and dest in directed edge by creating new Node with an id and their names - a bit hacky because Node is not supposed to be used this way. */
		Node originNode = new Node(this.ORIGIN_ID, origin);
		Node destNode = new Node(this.DEST_ID, dest);

		// Priority queue starts with connection from origin to two nodes in edge containing it
		q.add(new DijPair(
			new DirEdge(
				originNode, 
				originEdge.getNode1()
			), 
			originEdge.roomDist(
				origin, 
				originEdge.getNode1()
			)
		));
		q.add(new DijPair(
			new DirEdge(
				originNode, 
				originEdge.getNode2()
			), 
			originEdge.roomDist(
				origin, 
				originEdge.getNode2()
			)
		));
		
		/** Basic directed graph of shortest path from origin to Nodes in graph (but not every). Also serves as storage for Nodes already visited by priority queue. */
		ArrayList<DirEdge> visited = new ArrayList<>();
		
		// Continues until either queue is empty or path is found
		while (q.size() > 0)
		{
			// Pop front of queue
			DijPair currPair = q.poll();
			/** Node that DijPair points to. */
			Node currNode = currPair.edge.to;
			// Check that node not already visited
			if (visitedContains(visited, currNode))
			{
				continue;
			}

			// Add directed edge to visited graph to prevent Node from being revisited
			visited.add(currPair.edge);
			// Check if current node is dest
			if (currNode == destNode)
			{
				// If current Node is dest, there exists a path in visited from origin to dest
				return makePath(visited, originNode, destNode);
			}
			
			// Iterate through each Edge that current Node is connected to
			for (final NodeConnection connection : currNode.getConnections())
			{
				// To save time, gets internal representation of Edges in Node and then converts to Edge
				Edge e = connection.getEdge();
				// Adds new connections into q
				if (e.hasRoom(dest))
				{
					// If edge contains dest, adds DijPair from current Node to dest
					q.add(new DijPair(
						new DirEdge(currNode, destNode), 
						currPair.weight + e.roomDist(dest, currNode)
					));
				}
				else
				{
					// Otherwise, add DijPair from current Node to Node on other side of Edge
					q.add(new DijPair(
						new DirEdge(currNode, e.getOther(currNode)), 
						currPair.weight + e.getWeight()
					));
				}
			}
		}
		// If q is empty, there is no path from origin to dest
		return null;
	}
	
	// Helper methods
	/**
	 * Checks if node was visited by priority queue.
	 *
	 * @param visited Visited array from algorithm.
	 * @param n Node to check if visited.
	 * @return Whether n was visited or not.
	 */
	private static boolean visitedContains(ArrayList<DirEdge> visited, Node n)
	{
		// Checks if n was visited by checking Node each directed edge in visited is pointing to
		for (DirEdge d : visited)
		{
			if (d.to == n)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates Path from origin Node to dest Node using directed graph. Assumes there is actually a path from origin to dest in graph.
	 *
	 * @param dirGraph Directed graph to create Path from.
	 * @param origin Node to create Path from.
	 * @param dest Node to create Path to.
	 * @return Path object representing path from origin to dest.
	 */
	private static Path makePath(ArrayList<DirEdge> dirGraph, Node origin, Node dest)
	{
		Path p = new Path(origin.getName(), dest.getName());
		Node currNode = getFromNode(dirGraph, dest);
		while (currNode != origin)
		{
			p.add(currNode);
			currNode = getFromNode(dirGraph, currNode);
		}
		return p;
	}

	/**
	 * Helper method for makePath. Searches directed graph for node that points to parameter node, assuming that each Node in the graph is only pointed to once.
	 *
	 * @param dirGraph Directed graph to search in.
	 * @param to Node that this method finds the 'from' Node for.
	 * @return Node that points to to Node in graph.
	 */
	private static Node getFromNode(ArrayList<DirEdge> dirGraph, Node to)
	{
		for (DirEdge e : dirGraph)
		{
			if (e.to == to)
			{
				return e.from;
			}
		}
		return null;
	}
}

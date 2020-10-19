package finalProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijPath {
	Graph g;
	final int ORIGIN_NUM = -1;
	final int DEST_NUM = -2;

	public DijPath(Graph g) {
		this.g = g;
	}
	
	// returns null if origin or dest don't exist in graph
	public Path findPath(String origin, String dest)
	{
		Edge originEdge = this.g.getEdge(origin);
		// Edge cases
		if (originEdge == null)
		{
			return null;
		}
		if (originEdge.hasRoom(dest))
		{
			return new Path(origin, dest);
		}
		
		// Create queue of DijPair that sorts ascending by weight
		PriorityQueue<DijPair> q = new PriorityQueue<>(10, new Comparator<DijPair>() {
				public int compare(DijPair p1, DijPair p2)
				{
					// Sort from least weight to greatest weight
					if (p1.weight < p2.weight) return -1;
					if (p2.weight < p1.weight) return 1;
					return 0;
				}
		});
		
		// Represents origin by creating new Node with name of origin - a bit hacky
		Node originNode = new Node(this.ORIGIN_NUM, origin);
		Node destNode = new Node(this.DEST_NUM, dest);
		// Q starts with connection from origin to surrounding nodes
		q.add(new DijPair(new DirEdge(originNode, originEdge.getNode1()), originEdge.roomDist(origin, originEdge.getNode1())));
		q.add(new DijPair(new DirEdge(originNode, originEdge.getNode2()), originEdge.roomDist(origin, originEdge.getNode2())));
		// Basic directed graph of shortest path from origin to Nodes in graph (not every)
		// Also serves as storage for Nodes already visited by q, since each node can only be pointed at once
		ArrayList<DirEdge> visited = new ArrayList<>();
		
		while (q.size() > 0)
		{
			// Pop front of q
			DijPair currPair = q.poll();
			// Current node is node that connection is pointing to
			Node currNode = currPair.edge.to;
			// Check that node not already visited
			if (visitedContains(visited, currNode))
			{
				continue;
			}
			// Add connection to visited graph to prevent Node from being revisited
			visited.add(currPair.edge);
			// Check if current node is dest
			if (currNode == destNode)
			{
				return makePath(visited, originNode, destNode);
			}
			
			// Iterates through each Edge that current Node is connected to
			for (final NodeConnection connection : currNode.getConnections())
			{
				// To save time, gets internal representation of Edges in Node and converts to Edge
				Edge e = connection.getEdge();
				// Adds new connections into q
				if (e.hasRoom(dest))
				{
					// If edge contains dest, adds connection of current Node to dest
					q.add(new DijPair(new DirEdge(currNode, destNode), currPair.weight + e.roomDist(dest, currNode)));
				}
				else
				{
					// Otherwise, add connection from current Node to Node on other side of Edge
					q.add(new DijPair(new DirEdge(currNode, e.getOther(currNode)), currPair.weight + e.getWeight()));
				}
			}
		}
		// If q is empty, there is no path from origin to dest
		return null;
	}
	
	// Helper functions
	private static boolean visitedContains(ArrayList<DirEdge> visited, Node n)
	{
		for (DirEdge d : visited)
		{
			if (d.to == n)
			{
				return true;
			}
		}
		return false;
	}
	
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

}

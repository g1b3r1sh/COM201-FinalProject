package finalProject;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;

public class Program {
	public static void main(String[] args) {
		Graph g = new Graph();
		/*
		 * Graph structure:
		 * 0
		 * |\
		 * 1—2
		 */
		ArrayList<Integer> ZO = new ArrayList<>(Arrays.asList(100, 101, 102, 103));
		ArrayList<Integer> OT = new ArrayList<>(Arrays.asList(104, 105, 106, 107));
		ArrayList<Integer> ZT = new ArrayList<>(Arrays.asList(108, 109, 110, 111));

		g.add(0);
		g.add(1);
		g.add(2);
		g.connect(g.getNode(0), g.getNode(1), 180, 0, 2, ZO);
		g.connect(g.getNode(1), g.getNode(2), 90, 270, 1, OT);
        g.connect(g.getNode(0), g.getNode(2), 135, 315, 1, ZT);
		ArrayList<Node> path = findPath(103, 111, g);
		if (path.size() == 0)
		{
			System.out.println("No path");
		}
		for (Node n : path)
		{
			System.out.println(n.getName());
		}
	}
	
	// TODO split into methods
	// returns null if origin or dest don't exist in graph
	private static ArrayList<Node> findPath(int origin, int dest, Graph g)
	{
		Edge originEdge = g.getEdge(origin);
		if (originEdge == null)
		{
			return null;
		}
		if (originEdge.hasRoom(dest))
		{
			return new ArrayList<>();
		}
		PriorityQueue<DijPair> q = new PriorityQueue<>(10, new Comparator<DijPair>() {
				public int compare(DijPair p1, DijPair p2)
				{
					// Sort from least weight to greatest weight
					if (p1.weight < p2.weight) return -1;
					if (p2.weight < p1.weight) return 1;
					return 0;
				}
		});
		
		// TODO make use of these variables
		Node originNode = new Node(origin);
		Node destNode = new Node(dest);
		// Assert name convension for Nodes and rooms are different
		assert g.getNode(origin) != null : "Origin is name of node in graph";
		assert g.getNode(dest) != null : "Dest is name of node in graph";
		// Represents origin by creating new Node with name of origin - a bit hacky
		q.add(new DijPair(new DirEdge(originNode, originEdge.getNode1()), originEdge.roomDist(origin, originEdge.getNode1())));
		q.add(new DijPair(new DirEdge(originNode, originEdge.getNode2()), originEdge.roomDist(origin, originEdge.getNode2())));
		ArrayList<DirEdge> visited = new ArrayList<>();
		
		while (q.size() > 0)
		{
			DijPair currPair = q.poll();
			Node currNode = currPair.edge.to;
			if (visitedContains(visited, currNode))
			{
				continue;
			}
			visited.add(currPair.edge);
			if (currNode == destNode)
			{
				ArrayList<Node> path = new ArrayList<>();
				currNode = getFromNode(visited, currNode);
				while (currNode != originNode)
				{
					path.add(currNode);
					currNode = getFromNode(visited, currNode);
				}
				Collections.reverse(path);
				return path;
			}
			for (NodeConnection connection : currNode.getConnections())
			{
				Edge e = connection.getEdge();
				if (e.hasRoom(dest))
				{
					q.add(new DijPair(new DirEdge(currNode, new Node(dest)), currPair.weight + e.roomDist(dest, currNode)));
				}
				else
				{
					q.add(new DijPair(new DirEdge(currNode, e.getOther(currNode)), currPair.weight + e.getWeight()));
				}
			}
		}
		// There is no path from origin to dest
		return null;
	}
	
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
    
	private static ArrayList<Node> makePath(ArrayList<DirEdge> dirGraph, Node origin, Node dest)
	{
		ArrayList<Node> path = new ArrayList<>();
		Node currNode = getFromNode(dirGraph, dest);
		while (currNode != origin)
		{
			path.add(currNode);
			currNode = getFromNode(dirGraph, currNode);
		}
		Collections.reverse(path);
		return path;
	}
}

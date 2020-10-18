package finalProject;

import java.util.ArrayList;

public class Graph {
	// Private variables
	// TODO set these variables to private later
	public ArrayList<Node> nodes;
	public ArrayList<Edge> edges;
	
	public Graph()
	{
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
	}
	
	// Returns null if Node isn't in graph
	public Node getNode(int id)
	{
		for (Node node : this.nodes)
		{
			if (node.getId() == id)
			{
				return node;
			}
		}
		return null;
	}

	public Node getNode(String name)
	{
		for (Node node : this.nodes)
		{
			if (node.getName().equals(name))
			{
				return node;
			}
		}
		return null;
	}
	
	// Returns null if room isn't in Graph
	public Edge getEdge(String room)
	{
		for (Edge edge : this.edges)
		{
			if (edge.hasRoom(room))
			{
				return edge;
			}
		}
		return null;
	}
	
	public Edge getEdge(Node n1, Node n2)
	{
		for (Edge edge : this.edges)
		{
			if (n1 == edge.getOther(n2))
			{
				return edge;
			}
		}
		return null;
	}
	
	// Returns false if node already exists
	public boolean add(int id, String name)
	{
		if (this.getNode(id) != null)
		{
			return false;
		}
		else
		{
			this.nodes.add(new Node(id, name));
			return true;
		}
	}
	
	// Returns false if Node already connected or Nodes don't exist
	public boolean connect(Node n1, Node n2, int angle1, int angle2, double weight, ArrayList<String> rooms)
	{
		if (n1 == null || n2 == null || n1.connectedTo(n2) == true || n2.connectedTo(n1) == true)
		{
			return false;
		}
		
		Edge edge = new Edge(n1, n2, weight, rooms);
		this.edges.add(edge);
		
		n1.addEdge(edge, angle1);
		n2.addEdge(edge, angle2);
		return true;
	}
}

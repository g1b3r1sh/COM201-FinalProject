package finalProject;

import java.util.ArrayList;

/**
 * Represents graph containing Nodes that are connected by Edges.
 */
public class Graph {
	// Private variables
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	/**
	 * Class constructor.
	 */
	public Graph()
	{
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
	}
	
	/**
	 * Given id, retrieves Node with that id.
	 *
	 * @param id Id to check graph for.
	 * @return Node with that id, or null if no Node in Graph exists with that id.
	 */
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

	/**
	 * Given name, retrieves Node with that name.
	 *
	 * @param name Name to check graph for.
	 * @return Node with that name, or null if no Node in Graph exists with that name.
	 */
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
	
	/**
	 * Given room, retrieves Edge with that room
	 * @param room Room to check graph for.
	 * @return First Edge in Graph with that room, or null if no Edge in Graph contains that room.
	 */
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
	
	/**
	 * Given two Nodes, retrieves Edge connecting both Nodes.
	 *
	 * @param n1 Node to check graph for.
	 * @param n2 Other node to check graph for.
	 * @return Edge connecting both Nodes, or null if no Edge connects both Nodes.
	 */
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
	
	/**
	 * Adds Node to Graph.
	 *
	 * @param id Id of Node.
	 * @param name Name of Node.
	 * @return If insertion was successful (i.e. whether or not another node exists with same id.).
	 */
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
	
	/**
	 * Connects two Nodes in Graph.
	 *
	 * @param n1 First node.
	 * @param n2 Second node.
	 * @param angle1 Angle at which Edge connects to first Node.
	 * @param angle2 Angle at which Edge connects to second Node.
	 * @param weight Distance between both Edges.
	 * @param rooms ArrayList representing rooms in Edge.
	 * @return Whether connection was successful.
	 */
	public boolean connect(Node n1, Node n2, int angle1, int angle2, double weight, ArrayList<String> rooms)
	{
		// Check for if nodes are null or if nodes are already connected to each other
		if (n1 == null || n2 == null || n1.connectedTo(n2) == true || n2.connectedTo(n1) == true)
		{
			return false;
		}
		
		// Create new Edge
		Edge edge = new Edge(n1, n2, weight, rooms);

		this.edges.add(edge);
		
		// Connect both Nodes to created Edge
		// For extendability, if this Graph were to become a directed graph, the only major modification would be to only let the Node pointing to the other Node know of the Edge.
		n1.addEdge(edge, angle1);
		n2.addEdge(edge, angle2);
		return true;
	}
}

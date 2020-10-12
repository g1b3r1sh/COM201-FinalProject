package finalProject;

import java.util.ArrayList;

public class Node {
	// Private variables
	private int name;
	private ArrayList<NodeConnection> connections;
	
	// Constructor
	public Node(int name) 
	{
		this.name = name;
		this.connections = new ArrayList<>();
	}
	
	// Public Methods
	public int getName()
	{
		return this.name;
	}
	public boolean connectedTo(Node n)
	{
		return getEdge(n) != null;
	}
	
	public Edge getEdge(Node n)
	{
		for (NodeConnection connection : this.connections)
		{
			if (connection.getEdge().getOther(this) != null)
			{
				return connection.getEdge();
			}
		}
		return null;
	}
	
	public void addEdge(Edge edge, int angle)
	{
		this.connections.add(new NodeConnection(edge, angle));
	}
	
	public int getAngle(Edge from, Edge to)
	{
		NodeConnection fromConn = this.getConnection(from);
		NodeConnection toConn = this.getConnection(to);
		return Util.subtractAngles(fromConn.getAngle(), toConn.getAngle());
	}
	
	public int getAngle(Node from, Node to)
	{
		return getAngle(this.getEdge(from), this.getEdge(to));
	}
	
	public int getAngle(Node n)
	{
		return this.getConnection(this.getEdge(n)).getAngle();
	}
	
	// Private methods
	private NodeConnection getConnection(Edge edge)
	{
		for (NodeConnection connection : this.connections)
		{
			if (connection.getEdge() == edge)
			{
				return connection;
			}
		}
		return null;
	}
}

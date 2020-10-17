package finalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
	// Private variables
	private String name;
	private ArrayList<NodeConnection> connections;
	
	// Constructor
	public Node(String name) 
	{
		this.name = name;
		this.connections = new ArrayList<>();
	}
	
	// Public Methods
	public String getName()
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
			if (connection.getEdge().getOther(this) == n)
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
	
	public int getAngle(Edge from, Edge to) throws NullPointerException
	{
		NodeConnection fromConn = this.getConnection(from);
		NodeConnection toConn = this.getConnection(to);
        if (fromConn == null)
        {
            throw new NullPointerException("Edge 'from' does not exist.");
        }
        else if (toConn == null)
        {
        	throw new NullPointerException("Edge 'to' does not exist.");
        }
		return Util.subtractAngles(fromConn.getAngle(), toConn.getAngle());
	}
	
	public int getAngle(Node from, Node to)
	{
		return this.getAngle(this.getEdge(from), this.getEdge(to));
	}
	
	public int getAngle(Node n) throws NullPointerException
	{
		NodeConnection con = this.getConnection(this.getEdge(n));
		if (con == null)
		{
			throw new NullPointerException("Connection to Node does not exist.");
		}
		return con.getAngle();
	}
	
	public List<NodeConnection> getConnections()
	{
		return Collections.unmodifiableList(this.connections);
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

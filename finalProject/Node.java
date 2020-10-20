package finalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents Node in Graph.
 */
public class Node {
	// Private variables
	private int id;
	private String name;
	/** Edges connected to Node are represented through wrapper NodeConnection objects, which in addition to containing an Edge, also contain the angle at which it connects to the Node. */
	private ArrayList<NodeConnection> connections;
	
	/**
	 * Class constructor.
	 *
	 * @param id Id of Node. Unique for each Node in Graph.
	 * @param name Name of Node. Used for outputs.
	 */
	public Node(int id, String name) 
	{
		this.id = id;
		this.name = name;
		this.connections = new ArrayList<>();
	}
	
	// Public Methods
	/**
	 * Gets id of Node.
	 *
	 * @return Id of Node.
	 */
	public int getId()
	{
		return this.id;
	}

	/**
	 * Gets name of Node.
	 *
	 * @return Name of Node.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Gets immutable viewer to NodeConnections ArrayList.
	 *
	 * @return List representing connections to Node.
	 */
	public List<NodeConnection> getConnections()
	{
		return Collections.unmodifiableList(this.connections);
	}

	/**
	 * Checks whether Node is connected to another Node.
	 *
	 * @param n Node to check if connected to.
	 */
	public boolean connectedTo(Node n)
	{
		return getEdge(n) != null;
	}
	
	/**
	 * Retrieves Edge connecting this Node and another Node.
	 *
	 * @param n Node on other side of retrieved Edge
	 * @return Edge connecting this Node and passed Node, or null if no such Edge exists in this Node.
	 */
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

	/**
	 * Retrieves Edge connecting this Node and a room.
	 *
	 * @param n Node on other side of retrieved Edge
	 * @return Edge connecting this Node and containing room, or null if no such Edge exists in this Node.
	 */
    public Edge getEdge(String room)
    {
        for (NodeConnection connection : this.connections)
        {
            if (connection.getEdge().hasRoom(room))
            {
                return connection.getEdge();
            }
        }
        return null;
    }
	
	/**
	 * Adds Edge to this Node.
	 *
	 * @param edge Edge connecting this Node.
	 * @param angle Angle at which edge is connected to this Node.
	 */
	public void addEdge(Edge edge, int angle)
	{
		this.connections.add(new NodeConnection(edge, angle));
	}
	
	/**
	 * Calculates angle between two Edges connected to Node.
	 * 
	 * @param from Edge used as base line.
	 * @param to Edge used as angle line.
	 * @return Angle between two Edges at point of Node.
	 * @throw NullPointerException if either Edge is null.
	 */
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
	
	/**
	 * Calculates angle between Edges of two Nodes connected to Node.
	 * 
	 * @param from Node who's connecting Edge used as base line.
	 * @param to Node who's connecting Edge used as angle line.
	 * @return Angle between two Edges of both Nodes at point of Node.
	 * @throw NullPointerException if either Node doesn't connect to this Node.
	 */
	public int getAngle(Node from, Node to)
	{
		return this.getAngle(this.getEdge(from), this.getEdge(to));
	}
	
	/**
	 * Gets angle of Edge of passed Node to this Node.
	 *
	 * @param n Passed Node to find angle of.
	 * @return Angle of Connection connecting this Node and passed Node.
	 * @throw NullPointerException if this Node doesn't connect to passed Node.
	 */
	public int getAngle(Node n) throws NullPointerException
	{
		NodeConnection con = this.getConnection(this.getEdge(n));
		if (con == null)
		{
			throw new NullPointerException("Connection to Node does not exist.");
		}
		return con.getAngle();
	}
	
	// Private methods
	/**
	 * Retrieves NodeConnection containing Edge.
	 * 
	 * @param edge Edge to find NodeConnection to.
	 * @return NodeConnection in this Node containing edge, or null if no such NodeConnection
	 */
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

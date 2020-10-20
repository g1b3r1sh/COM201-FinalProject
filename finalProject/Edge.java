package finalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents edge, or connection between two Nodes, in graph.
 */
public class Edge {
	// Private Variables
	private Node n1;
	private Node n2;
	private double weight;
	/** Rooms are represented as Strings in an ArrayList. */
	private ArrayList<String> rooms;

	/**
	 * Class constructor.
	 *
	 * @param n1 Node on one end.
	 * @param n2 Node on other end.
	 * @param weight Distance of edge.
	 * @param rooms ArrayList of rooms in edge from n1 to n2.
	 */
	public Edge(Node n1, Node n2, double weight, ArrayList<String> rooms) {
		this.n1 = n1;
		this.n2 = n2;
		this.weight = weight;
		if (rooms == null)
		{
			this.rooms = new ArrayList<>();
		}
		else
		{
			this.rooms = rooms;
		}
	}
	
	/**
	 * Class constructor.
	 *
	 * @param n1 Node on one end.
	 * @param n2 Node on other end.
	 * @param rooms ArrayList of rooms in edge.
	 */
	public Edge(Node n1, Node n2, ArrayList<String> rooms) {
		// If weight is unspecified, it defaults to 1
		this(n1, n2, 1, rooms);
	}
	
	// Public Methods
	/**
	 * Gets weight of Edge.
	 *
	 * @return Weight of Edge.
	 */
	public double getWeight()
	{
		return this.weight;
	}
	
	/**
	 * Gets immutable viewer to rooms ArrayList.
	 *
	 * @return List representing rooms in Edge.
	 */
	public List<String> getRooms()
	{
		return Collections.unmodifiableList(this.rooms);
	}
	
	/**
	 * Checks if Edge contains room
	 * 
	 * @param room Room to check Edge for.
	 * @return Whether or not Edge contains room.
	 */
	public boolean hasRoom(String room)
	{
		return this.rooms.contains(room);
	}
	
	/**
	 * Returns distance from room to Node using weight of Edge.
	 *
	 * @param room Room to find distance from.
	 * @param n Node to find distance to.
	 * @return Distance from room to Node, assuming rooms are evenly spaced along Edge, or -1 if Node or room are not in Edge.
	 */
	public double roomDist(String room, Node n)
	{
		if (!this.rooms.contains(room) || !this.connects(n))
		{
			return -1;
		}
		// Double between 0 and 1 representing fraction of point on edge relative to n1
		double point = ((double)this.rooms.indexOf(room) + 1) / (this.rooms.size() + 1);
		if (n == n1)
		{
			return this.weight * point;
		}
		else
		{
			// If n == n2, return distance relative to n2, meaning point is 1 - fraction from n1
			return this.weight * (1 - point);
		}
	}
	
	/**
	 * Returns distance between two rooms using weight of Edge.
	 *
	 * @param room Room to find distance from.
	 * @param room Room to find distance to.
	 * @return Distance between rooms, assuming rooms are evenly spaced along Edge, or -1 if any of rooms are not in Edge.
	 */
	public double roomDist(String room1, String room2)
	{
		if (!this.rooms.contains(room1) || !this.rooms.contains(room2))
		{
			return -1;
		}
		
		// Distance between two rooms in array
		int arrDist = Math.abs(this.rooms.indexOf(room1) - this.rooms.indexOf(room2));
		// Distance between adjacent rooms in terms of weight
		double roomWeight = this.weight / (this.rooms.size() + 1);
		return roomWeight * arrDist;
	}
	
	/**
	 * Given one Node, returns other Node that Edge connects to.
	 * 
	 * @param node Node on one side of Edge
	 * @return Node on other side of Edge or null if node isn't in Edge.
	 */
	public Node getOther(Node node)
	{
		if (this.n1 == node)
		{
			return this.n2;
		}
		else if (this.n2 == node)
		{
			return this.n1;
		}
		return null;
	}
	
	/**
	 * Returns whether given Node is connected to Edge.
	 * 
	 * @param node Node to test if in Edge.
	 * @return Whether Node is in Edge.
	 */
	public boolean connects(Node node)
	{
		return this.n1 == node || this.n2 == node;
	}
	
	/**
	 * Returns first Node of Edge.
	 *
	 * @return n1
	 */
	public Node getNode1()
	{
		return this.n1;
	}
	
	/**
	 * Returns second Node of Edge.
	 *
	 * @return n2
	 */
	public Node getNode2()
	{
		return this.n2;
	}
}

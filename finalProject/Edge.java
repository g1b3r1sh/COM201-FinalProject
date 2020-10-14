package finalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Edge {
	// Private Variables
	private double weight;
	private ArrayList<Integer> rooms;
	private Node n1;
	private Node n2;

	public Edge(Node n1, Node n2, double weight, ArrayList<Integer> rooms) {
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
	
	// By default, weight is 1
	public Edge(Node n1, Node n2, ArrayList<Integer> rooms) {
		this(n1, n2, 1, rooms);
	}
	
	// Public Methods
	public double getWeight()
	{
		return this.weight;
	}
	
	public List<Integer> getRooms()
	{
		return Collections.unmodifiableList(this.rooms);
	}
	
	public boolean hasRoom(int room)
	{
		return this.rooms.contains(room);
	}
	
	// Returns -1 if Node or room not in Edge
	public double roomDist(int room, Node n)
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
	
	// Returns -1 if any room not in Edge
	public double roomDist(int room1, int room2)
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

	// If node is in Edge, return other node
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
	
	public boolean connects(Node node)
	{
		return this.n1 == node || this.n2 == node;
	}
    
	public Node getNode1()
	{
		return this.n1;
	}
	
	public Node getNode2()
	{
		return this.n2;
	}
}

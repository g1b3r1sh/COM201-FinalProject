package finalProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Stores array of rooms in order of n1 to n2
// Also assumes that rooms are evenly spaced out along edge
public class BuildingEdge<GenNode extends Node<GenNode, GenEdge, ?>, 
							GenEdge extends BuildingEdge<GenNode, GenEdge, RoomType>,
							RoomType> 
						extends Edge<GenNode, GenEdge>{
	// If rooms are dynamic for some reason, create methods
	protected ArrayList<RoomType> rooms;

	public BuildingEdge(GenNode n1, GenNode n2, ArrayList<RoomType> rooms) {
		super(n1, n2);
		this.rooms = rooms;
	}
	
	public List<RoomType> getRooms()
	{
		return Collections.unmodifiableList(rooms);
	}
	
	public boolean containsRoom(RoomType room)
	{
		return this.rooms.contains(room);
	}
	
	public double distance(RoomType room)
	{
		int roomIndex = this.rooms.indexOf(room);
		if (roomIndex == -1)
		{
			return -1;
		}
		return (roomIndex + 1) / (this.rooms.size() + 1);
	}
}

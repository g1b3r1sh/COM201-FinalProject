package finalProject;

import java.util.ArrayList;

public class WeightedBuildingEdge<GenNode extends Node<GenNode, GenEdge, ?>, 
									GenEdge extends Edge<GenNode, GenEdge>,
									RoomType>
								extends BuildingEdge<GenNode, GenEdge, RoomType>{

	private int weight;

	public WeightedBuildingEdge(GenNode n1, GenNode n2, ArrayList<RoomType> rooms, int weight) {
		super(n1, n2, rooms);
		this.weight = weight;
	}
	
	public int getWeight()
	{
		return this.weight;
	}
	
	@Override
	public double distance(RoomType room)
	{
		return super.distance(room) * this.weight;
	}
}

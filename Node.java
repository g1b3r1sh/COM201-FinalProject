package finalProject;

import java.util.ArrayList;

// I hate generics
public class Node<GenNode extends Node<GenNode, GenEdge, NameType>, 
					GenEdge extends Edge<GenNode, GenEdge>, 
					NameType> {
	// Private variable
	private NameType name;
	protected ArrayList<GenEdge> edges;
	
	// Constructor
	public Node(NameType name)
	{
		this.name = name;
		this.edges = new ArrayList<>();
	}
	
	// Public methods
	public NameType getName() {
		return this.name;
	}
	
	@SuppressWarnings("unchecked")
	public GenEdge getEdge(NameType name) throws IllegalArgumentException
	{
		for (GenEdge edge : this.edges)
		{
			if (edge.getOther((GenNode) this).getName().equals(name))
			{
				return edge;
			}
		}
		throw new IllegalArgumentException("Node not found");
	}
	
	public void addEdge(GenEdge edge)
	{
		this.edges.add(edge);
	}
}

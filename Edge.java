package finalProject;

public class Edge<GenNode extends Node<GenNode, GenEdge, ?>, 
					GenEdge extends Edge<GenNode, GenEdge>> {
	// Private Variables
	GenNode n1;
	GenNode n2;
	
	// Constructor
	public Edge(GenNode n1, GenNode n2)
	{
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public boolean contains(GenNode node)
	{
		if (node == this.n1 || node == this.n2)
		{
			return true;
		}
		return false;
	}
	
	public GenNode getOther(GenNode node) throws IllegalArgumentException
	{
		if (node == this.n1)
		{
			return this.n2;
		}
		else if (node == this.n2)
		{
			return this.n1;
		}
		throw new IllegalArgumentException("node is not connected by edge");
	}
}

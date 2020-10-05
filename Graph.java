package finalProject;

import java.util.Map;
import java.util.HashMap;

// I love generics
abstract class Graph<GenNode extends Node<GenNode, GenEdge, NameT>, 
						GenEdge extends Edge<GenNode, GenEdge>, 
						NameT> {
	// Private variable
	// All nodes in graph
	protected Map<NameT, GenNode> nodes;
	
	// Constructors
	public Graph()
	{
		this.nodes = new HashMap<>();
	}
	
	// Public methods
	public GenNode getNode(NameT name)
	{
		return nodes.get(name);
	}
	
	// Abstract methods
	//abstract void add(NameT name);
	abstract void add(NameT name);
	abstract void connect(NameT n1, NameT n2);
}

package finalProject;

import java.util.ArrayList;

public class DirectionNode<GenNode extends DirectionNode<GenNode, GenEdge, NameType>, 
							GenEdge extends Edge<GenNode, GenEdge>, 
							NameType> 
						extends Node<GenNode, GenEdge, NameType>{
	private ArrayList<NodeConnection<GenNode>> angles;
	
	public DirectionNode(NameType name) {
		super(name);
	}
	// TODO Override constructor to add angles
}

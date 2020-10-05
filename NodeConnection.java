package finalProject;

public class NodeConnection <GenNode extends Node<GenNode, ?, ?>> {
	private GenNode node;
	private int angle;
	
	public NodeConnection(GenNode node, int angle)
	{
		this.node = node;
		this.angle = angle;
	}

	public GenNode getNode()
	{
		return this.node;
	}
	
	public int getAngle()
	{
		return this.angle;
	}
	
	public int subtract(NodeConnection<GenNode> connection)
	{
		return Util.subtractAngles(this.getAngle(), connection.getAngle());
	}
}

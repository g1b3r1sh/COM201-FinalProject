package finalProject;

public class NodeConnection {
	// Private variables
	Edge edge;
	int angle;

	public NodeConnection(Edge edge, int angle) {
		this.edge = edge;
		this.angle = angle;
	}

	public Edge getEdge() {
		return this.edge;
	}

	public int getAngle() {
		return this.angle;
	}

}

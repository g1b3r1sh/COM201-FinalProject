package finalProject;

/**
 * Represents connection of Edge to Node.
 */
public class NodeConnection {
	// Private variables
	private Edge edge;
	private int angle;

	/**
	 * Class constructor.
	 *
	 * @param edge Edge that this NodeConnection represents
	 * @param angle Angle at which this NodeConnection connects to a Node.
	 */
	public NodeConnection(Edge edge, int angle) {
		this.edge = edge;
		this.angle = angle;
	}

	/**
	 * Gets edge of NodeConnection.
	 *
	 * @return Edge of NodeConnection.
	 */
	public Edge getEdge() {
		return this.edge;
	}

	/**
	 * Gets angle of NodeConnection.
	 *
	 * @return Angle of NodeConnection.
	 */
	public int getAngle() {
		return this.angle;
	}

}

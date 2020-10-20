package finalProject;

/**
 * Represents directed edge between two nodes
 */
public class DirEdge {
	// Public variables
	public Node from;
	public Node to;
	
	/**
	 * Class constructor.
	 *
	 * @param from Node that edge comes from
	 * @param to Node that edge points to
	 */
	public DirEdge(Node from, Node to) {
		this.from = from;
		this.to = to;
	}
}

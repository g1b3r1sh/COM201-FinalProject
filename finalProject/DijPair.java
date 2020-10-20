package finalProject;

/** 
 * Represents directed edge in Graph and its weight in relation to origin in Dijkstra’s algorithm.
 * Used as wrapper class in Dijkstra’s algorithm to help priority queue select next edge to view.
 */
public class DijPair {
	// Public variables
	public DirEdge edge;
	public double weight;

	/**
	 * Class constructor
	 * 
	 * @param edge Directed edge in Graph.
	 * @param weight Distance from origin to end of edge.
	 */
	public DijPair(DirEdge edge, double weight) {
		this.edge = edge;
		this.weight = weight;
	}
}

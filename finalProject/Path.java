package finalProject;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Represents path of Nodes that connect two rooms in Graph.
 */

public class Path {
	/** For some reason, Stack iterates like a normal List (FIFO instead of FILO). Therefore, a Deque will simulate a Stack */
    private Deque<Node> nodes;
    private String origin;
    private String dest;

	/**
	 * Class constructor.
	 *
	 * @param origin Origin room.
	 * @param dest Destination room.
	 */
    public Path(String origin, String dest)
    {
        this.origin = origin;
        this.dest = dest;
        nodes = new ArrayDeque<>();
    }
	
	/**
	 * Adds node to front of currnet Path.
	 *
	 * @param n Node to add.
	 */
    public void add(Node n)
    {
        this.nodes.push(n);
    }

	/**
	 * Gets size of Path.
	 *
	 * @return Number of Nodes in Path.
	 */
    public int getSize()
    {
        return this.nodes.size();
    }

	/**
	 * Gets List representation of Nodes in Path from origin to dest.
	 *
	 * @return List representing this Path.
	 */
	public List<Node> getNodes()
	{
		return new ArrayList<Node>(this.nodes);
	}

	/**
	 * Creates array of Strings representing traversal of Path for output.
	 *
	 * @return Output.
	 */
    public ArrayList<String> makeOutput()
    {
		ArrayList<String> output = new ArrayList<String>();
		output.add(String.format("Start at %s.", origin));
		for (Node n : this.nodes)
		{
			output.add(String.format("Go to %s.", n.getName()));
		}
		output.add(String.format("Go to %s.", dest));
		return output;
	}
}
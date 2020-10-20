package finalProject;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Path {
	// For some reason, Stack is iterated like a normal List (FIFO instead of FILO)
	// Therefore, a Deque will simulate a Stack
    private Deque<Node> nodes;
    private String origin;
    private String dest;

    public Path(String origin, String dest)
    {
        this.origin = origin;
        this.dest = dest;
        nodes = new ArrayDeque<>();
    }

    public void add(Node n)
    {
        this.nodes.push(n);
    }

    public int getSize()
    {
        return this.nodes.size();
    }

	public List<Node> getNodes()
	{
		return new ArrayList<Node>(this.nodes);
	}

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
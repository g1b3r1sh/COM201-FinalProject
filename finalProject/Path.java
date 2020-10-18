package finalProject;

import java.util.Stack;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class Path {
    private Stack<Node> nodes;
    private String origin;
    private String dest;

    public Path(String origin, String dest)
    {
        this.origin = origin;
        this.dest = dest;
        nodes = new Stack<>();
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
		return Collections.unmodifiableList(this.nodes);
	}

    /*public ArrayList<String> makeOutput()
    {
        
    }*/
}
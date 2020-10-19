package finalProject;

public class Program {
	public static void main(String[] args) throws Exception{
		/*
		 * exmaple_graph structure:
		 * 0
		 * |\
		 * 1—2
		 */
		// Note: relative filepaths are relative to current dictionary in console
		// Note: use absolute filepath in Eclipse
		// There is probably another way to do this using FileLocator or something
		XMLGraph xml = new XMLGraph("finalProject/data/example_graph.xml");
		Graph g = xml.constructGraph();
		Path p = new DijPath(g).findPath("100", "107");
		if (p.getSize() == 0)
		{
			System.out.println("No path");
		}
		for (Node n : p.getNodes())
		{
			System.out.println(n.getName());
		}
	}
}

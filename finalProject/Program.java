package finalProject;

import java.io.*;
import java.util.*;
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
		Scanner kbReader = new Scanner(System.in);
		
		while(true)
		{
			System.out.print("Enter origin: ");
			String origin = kbReader.next();
			if (g.getEdge(origin) == null)
			{
				System.out.println("Origin was not a room.");
			}
			
			System.out.print("Enter destination: ");
			String dest = kbReader.next();
			if (g.getEdge(dest) == null)
			{
				System.out.println("Destination was not a room.");
			}
			Path p = new DijPath(g).findPath(origin, dest);
			if (p == null)
			{
				System.out.println("No path.");
			}
			else if (p.getSize() == 0)
			{
				System.out.println("Same hallway.");
			}
			else
			{
				for (Node n : p.getNodes())
				{
					System.out.println(n.getName());
				}
			}
		}

		kbReader.close();
	}
}

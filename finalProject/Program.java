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
		XMLGraph xml = new XMLGraph("finalProject/data/graph.xml");
		Graph g = xml.constructGraph();
		Scanner kbReader = new Scanner(System.in);
		
		while(true)
		{
			System.out.print("Command: ");
			String cmd = kbReader.nextLine();
			if (cmd.equals("quit") || cmd.equals("q"))
			{
				break;
			}
			else if (cmd.equals("path") || cmd.equals("p"))
			{
				System.out.print("Enter origin: ");
				String origin = kbReader.nextLine();
				if (g.getEdge(origin) == null)
				{
					System.out.println("Origin is not a room.");
					continue;
				}
				System.out.print("Enter destination: ");
				String dest = kbReader.nextLine();
				if (g.getEdge(dest) == null)
				{
					System.out.println("Destination is not a room.");
					continue;
				}
				Path p = new DijPath(g).findPath(origin, dest);
				if (p == null)
				{
					System.out.println(String.format("No path found from %s to %s.", origin, dest));
				}
				else
				{
					printPath(p);
				}
			}
			else
			{
				System.out.println(String.format("%s is not a command.", cmd));
			}
		}

		kbReader.close();
	}

	private static void printPath(Path p)
	{
		for (String s : p.makeOutput())
		{
			System.out.println(s);
		}
	}
}

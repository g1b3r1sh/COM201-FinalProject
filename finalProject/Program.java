package finalProject;

import java.io.*;
import java.util.*;

/**
 * Main Program.
 */
public class Program {
	/**
	 * Main method of program. Loads graph, takes in origin and destination room, and outputs shortest path between them.
	 */
	public static void main(String[] args) throws Exception{
		/** Filepath to Graph xml file. */
		// Note: relative filepaths are relative to current dictionary in console
		// Note: use absolute filepath in Eclipse
		// There is probably another way to do this using FileLocator or something
		final String GRAPH_PATH = "finalProject/data/graph.xml";
		/*
		 * example_graph.xml structure:
		 * 0
		 * |\
		 * 1—2
		 */

		// Loads graph from XML file
		XMLGraph xml = new XMLGraph(GRAPH_PATH);
		// Creates Graph object using XML file
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
				// Check if input is in g
				if (g.getEdge(origin) == null)
				{
					System.out.println("Origin is not a room.");
					continue;
				}

				System.out.print("Enter destination: ");
				String dest = kbReader.nextLine();
				// Check if input is in g
				if (g.getEdge(dest) == null)
				{
					System.out.println("Destination is not a room.");
					continue;
				}
				
				// Constructs Path object using DijPath object that finds and returns Path between two rooms
				Path p = new DijPath(g).findPath(origin, dest);
				// If DijPath object returns null, no path was found
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

	/**
	 * Helper method for main. Takes in a Path object and prints out formatted representation of Path.
	 * 
	 * @param p Path object.
	 */
	private static void printPath(Path p)
	{
		for (String s : p.makeOutput())
		{
			System.out.println(s);
		}
	}
}

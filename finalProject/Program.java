package finalProject;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;

public class Program {
	public static void main(String[] args) throws Exception{
		//Graph g = new Graph();
		/*
		 * Graph structure:
		 * 0
		 * |\
		 * 1—2
		 */
		/*ArrayList<String> ZO = new ArrayList<>(Arrays.asList("100", "101", "102", "103"));
		ArrayList<String> OT = new ArrayList<>(Arrays.asList("104", "105", "106", "107"));
		ArrayList<String> ZT = new ArrayList<>(Arrays.asList("108", "109", "110", "111"));

		g.add(0, "0");
		g.add(1, "1");
		g.add(2, "2");
		g.connect(g.getNode("0"), g.getNode("1"), 180, 0, 0.5, ZO);
		g.connect(g.getNode("1"), g.getNode("2"), 90, 270, 1, OT);
        g.connect(g.getNode("0"), g.getNode("2"), 135, 315, 1, ZT);*/
		// Note: relative filepaths are relative to current dictionary in console
		XMLGraph xml = new XMLGraph("finalProject/data/graph.xml");
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

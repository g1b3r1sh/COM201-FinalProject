package finalProject;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;

public class Program {
	public static void main(String[] args) {
		Graph g = new Graph();
		/*
		 * Graph structure:
		 * 0
		 * |\
		 * 1—2
		 */
		ArrayList<Integer> ZO = new ArrayList<>(Arrays.asList(100, 101, 102, 103));
		ArrayList<Integer> OT = new ArrayList<>(Arrays.asList(104, 105, 106, 107));
		ArrayList<Integer> ZT = new ArrayList<>(Arrays.asList(108, 109, 110, 111));

		g.add(0);
		g.add(1);
		g.add(2);
		g.connect(g.getNode(0), g.getNode(1), 180, 0, 2, ZO);
		g.connect(g.getNode(1), g.getNode(2), 90, 270, 1, OT);
        g.connect(g.getNode(0), g.getNode(2), 135, 315, 1, ZT);
		ArrayList<Node> path = new DijPath(g).findPath(103, 111);
		if (path.size() == 0)
		{
			System.out.println("No path");
		}
		for (Node n : path)
		{
			System.out.println(n.getName());
		}
	}
}

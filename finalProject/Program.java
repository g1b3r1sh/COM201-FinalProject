package finalProject;

import java.util.ArrayList;
public class Program {
	public static void main(String[] args) {
		Graph foo = new Graph();
		/*
		 * Graph structure:
		 * 0
		 * |
		 * 1—2
		 */
		ArrayList<Integer> bar = new ArrayList<>();
			bar.add(100);
			bar.add(101);
			bar.add(102);
			bar.add(103);
		foo.add(0);
		foo.add(1);
		foo.add(2);
		foo.connect(foo.getNode(0), foo.getNode(1), 180, 0, 2, null);
		foo.connect(foo.getNode(1), foo.getNode(2), 90, 270, 1, bar);
	}
}

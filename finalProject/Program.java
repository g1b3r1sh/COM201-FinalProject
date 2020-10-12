package finalProject;

public class Program {
	public static void main(String[] args) {
		Graph foo = new Graph();
		/*
		 * Graph structure:
		 * 0
		 * |
		 * 1-2
		 */
		foo.add(0);
		foo.add(1);
		foo.add(2);
		foo.connect(foo.getNode(0), foo.getNode(1), 180, 0, 2, null);
		
		// This isn't working
		foo.connect(foo.getNode(1), foo.getNode(2), 90, 270, 1, null);
		
		// TODO Figure out why angles of connections are all 0
		System.out.println(foo.getNode(1).getAngle(foo.getNode(0)));
		System.out.println(foo.getNode(1).getAngle(foo.getNode(2)));
		// TODO figure out why foo only has one edge
		System.out.println(foo.edges.size());
		
		// Should return 90
		System.out.println(foo.getNode(1).getAngle(foo.getNode(0), foo.getNode(2)));
	}

}

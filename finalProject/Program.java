package finalProject;

public class Program {
	public static void main(String[] args) {
		Graph foo = new Graph();
		/*
		 * Graph structure:
		 * 0
		 * |
		 * 1—2
		 */
		foo.add(0);
		foo.add(1);
		foo.add(2);
		foo.connect(foo.getNode(0), foo.getNode(1), 180, 0, 2, null);
		foo.connect(foo.getNode(1), foo.getNode(2), 90, 270, 1, null);
		
		System.out.println(foo.getNode(1).connectedTo(foo.getNode(2)));
		System.out.println(foo.getNode(1).getAngle(foo.getNode(0)));
		System.out.println(foo.getNode(1).getAngle(foo.getNode(2)));
		System.out.println(foo.edges.size());
		
		// Should return 90
		System.out.println(foo.getNode(1).getAngle(foo.getNode(0), foo.getNode(2)));
	}

}

package finalProject;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

/** XML representation of Graph. Documentation: https://howtodoinjava.com/java/xml/read-xml-dom-parser-example/
 * Note: In XML, everything (Elements, SubElements, Text, etc.) is referred to as a node, which could potentially cause conflict with the Node class for Graph
 */
public class XMLGraph {
	// Private variables
	/** Document variable containing representation of xml file in Java. */
	private Document doc;

	/**
	 * Class constructor.
	 *
	 * @param filePath Path to xml file.
	 * @throws Exception if document fails to load.
	 */
	public XMLGraph(String filePath) throws Exception
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			this.doc = builder.parse(new File(filePath));
			this.doc.getDocumentElement().normalize();
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Assuming current xml file is in a Graph structure, constructs and returns Graph object from xml file.
	 * 
	 * @return Graph object made from document.
	 * @throws Exception if Graph-building process goes wrong.
	 */
	public Graph constructGraph() throws Exception
	{
		Graph g = new Graph();
		this.addNodes(g);
		this.connectNodes(g);
		return g;
	}

	/**
	 * Adds Nodes from document to Graph.
	 *
	 * @param g Graph to add Nodes to.
	 */
	private void addNodes(Graph g)
	{
		// Retrieves list of elements with tag 'node' in document.
		NodeList nodes = this.doc.getElementsByTagName("node");
		// Iterates through each 'node' element
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			// Takes 'id' and 'name' tags inside element and uses them to add Node to g
			g.add(
				Integer.parseInt(this.getElementInPath(n, "id").getTextContent()), 
				this.getElementInPath(n, "name").getTextContent()
			);
		}
	}

	/**
	 * Connects Nodes in Graph using document.
	 *
	 * @param g Graph to connect Nodes in.
	 * @throws Exception if connection goes wrong
	 */
	private void connectNodes(Graph g) throws Exception
	{
		// Retrieves list of elements with tag 'edge' in document.
		NodeList edges = this.doc.getElementsByTagName("edge");
		// Iterates through each 'edge' element
		for (int i = 0; i < edges.getLength(); i++)
		{
			Element e = (Element) edges.item(i);
			// Takes inside of element and uses them to connect Nodes in g
			boolean success = g.connect(
				g.getNode(Integer.parseInt(this.getElementInPath(e, "left", "id").getTextContent())), 
				g.getNode(Integer.parseInt(this.getElementInPath(e, "right", "id").getTextContent())), 
				Integer.parseInt(this.getElementInPath(e, "left", "angle").getTextContent()), 
				Integer.parseInt(this.getElementInPath(e, "right", "angle").getTextContent()),
				Double.parseDouble(this.getElementInPath(e, "weight").getTextContent()),
				this.getTagArray(this.getElementInPath(e, "rooms"), "room")
			);
			// If connect method returns false, connection failed
			if (!success)
			{
				throw new Exception("Edge connection failed");
			}
		}
	}

	/**
	 * Helper method to traverse xml file, assuming that each parameter in path is connected in a path.
	 *
	 * @param start Starting element.
	 * @param path Path to traverse.
	 * @return Element at end of path.
	 */
	private Element getElementInPath(Element start, String ... path)
	{
		Element e = start;
		// Iterates through tags in path
		for (String s : path)
		{
			// Takes first instance of tag in current element and sets current element to that
		    e = (Element) e.getElementsByTagName(s).item(0);
		}
		return e;
	}

	/**
	 * Helper method to retrieve list from xml file.
	 *
	 * @param e Element containing list.
	 * @param tag Tag name of elements inside e to retrieve text contents from.
	 * @return ArrayList of retrieved list.
	 */
	private ArrayList<String> getTagArray(Element e, String tag)
	{
		ArrayList<String> a = new ArrayList<>();
		// Iterates through child nodes of e
		NodeList es = e.getChildNodes();
		for (int i = 0; i < es.getLength(); i++)
		{
			// If name of child node matches tag, add its content to array
		    if (es.item(i).getNodeName().equals(tag))
		    {
			    a.add(es.item(i).getTextContent());
		    }
		}
		return a;
	}
}
package finalProject;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

// XML representation of Graph
// https://howtodoinjava.com/java/xml/read-xml-dom-parser-example/
// Note: In XML, everything is referred to as a node, which could potentially cause conflict with the Node class for Graph
public class XMLGraph {
	Document doc;

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

	public Graph constructGraph()
	{
		Graph g = new Graph();
		this.addNodes(g);
		this.connectNodes(g);
		return g;
	}

	private void addNodes(Graph g)
	{
		NodeList nodes = this.doc.getElementsByTagName("node");
		for (int i = 0; i < nodes.getLength(); i++)
		{
			Element n = (Element) nodes.item(i);
			g.add(this.getElementInPath(n, "name").getTextContent());
		}
	}
	
	private void connectNodes(Graph g)
	{
		NodeList edges = this.doc.getElementsByTagName("edge");
		for (int i = 0; i < edges.getLength(); i++)
		{
			Element e = (Element) edges.item(i);
			g.connectNodes(
				g.getNode(this.getElementInPath(e, "left", "name").getTextContent()), 
				g.getNode(this.getElementInPath(e, "right", "name").getTextContent()), 
				this.getElementInPath(e, "left", "angle").getTextContent(), 
				this.getElementInPath(e, "right", "angle").getTextContent(),
				this.getElementInPath(e, "weight"),
				this.getTagIntArray(this.getElementInPath(e, "rooms"), "room")
			);
		}
	}

	private Element getElementInPath(Element start, String ... path)
	{
		Element e = start;
		for (String s : path)
		{
		    e = (Element) e.getElementsByTagName(s).item(0);
		}
		return e;
	}

	private ArrayList<Integer> getTagIntArray(Element e, String tag)
	{
		ArrayList<Integer> a = new ArrayList<>();
		NodeList es = e.getChildNodes();
		for (int i = 0; i < es.getLength(); i++)
		{
		    if (es.item(i).getNodeName() == tag)
		    {
			    a.add(Integer.parseInt(es.item(i).getTextContent()));
		    }
		}
		return a;
	}
}
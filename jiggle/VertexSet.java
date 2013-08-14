package jiggle;

import java.util.Vector;
import java.util.Enumeration;

public class VertexSet extends JiggleObject {

	private Vector<Vertex> vertices;

	VertexSet () {vertices = new Vector<Vertex> ();}

	VertexSet (Vertex v) {vertices = new Vector<Vertex> (); vertices.addElement (v);}

	void add (Vertex v) {vertices.addElement (v);}

	Enumeration elements () {return vertices.elements ();}
}
package jiggle;

// Class for graphs. */

import java.util.*;

public class Graph extends Cell {
  
	public int numberOfVertices = 0, numberOfEdges = 0;
	public Vertex vertices [] = new Vertex [1];
	public Edge edges [] = new Edge [1];

	/* NOTE: the above are made publicly accessible for reasons of
	efficiency.  They should NOT, however, be modified except by
	insertVertex, deleteVertex, insertEdge, and deleteEdge methods
	below. */

	public Graph () {}
	public Graph (int d) {setDimensions (d);}

	public Vertex insertVertex () {
		Vertex v = new Vertex (this);
		vertices = DynamicArray.add (vertices, numberOfVertices++, v);
		return v;
	}

	public Edge insertEdge (Vertex from, Vertex to) {
		return insertEdge (from, to, false);
	}

	public Edge insertEdge (Vertex from, Vertex to, boolean dir) {
		Edge e = new Edge (this, from, to, dir);
		from.insertNeighbor (e); to.insertNeighbor (e);
		edges = DynamicArray.add (edges, numberOfEdges++, e);
		return e;
	}
	
	public void deleteVertex (Vertex v) {
		try {
			for (int i = 0; i < v.inDegree; i++) {
				Edge e = v.undirectedEdges [i];
				v.undirectedNeighbors [i].deleteNeighbor (e);
				DynamicArray.remove (edges, numberOfEdges--, e);
			}
			for (int i = 0; i < v.inDegree; i++) {
				Edge e = v.inEdges [i];
				v.inNeighbors [i].deleteNeighbor (e);
				DynamicArray.remove (edges, numberOfEdges--, e);
			}
			for (int i = 0; i < v.outDegree; i++) {
				Edge e = v.outEdges [i];
				v.outNeighbors [i].deleteNeighbor (e);
				DynamicArray.remove (edges, numberOfEdges--, e);
			}
			DynamicArray.remove (vertices, numberOfVertices--, v);
		} catch (NotFoundException exc) {throw new Error (v + " not found");}
	}

	public void deleteEdge (Edge e) {
		try {
			e.getFrom ().deleteNeighbor (e); e.getTo ().deleteNeighbor (e);
			DynamicArray.remove (edges, numberOfEdges--, e);
		} catch (NotFoundException exc) {throw new Error (e + " not found");}
	}

	void recomputeBoundaries () {
		int d = getDimensions ();
		double lo [] = getMin (), hi [] = getMax ();
		for (int i = 0; i < d; i++) {
			lo [i] = Double.MAX_VALUE; hi [i] = -Double.MAX_VALUE;
		}
		for (int i = 0; i < numberOfVertices; i++) {
			Vertex v = vertices [i]; double c [] = v.getCoords ();
				for (int j = 0; j < d; j++) {
					lo [j] = Math.min (lo [j], c [j]);
					hi [j] = Math.max (hi [j], c [j]);
				}
		}
		recomputeSize ();
	}

	// The isConnected method tests whether a graph is connected.
	// An empty graph is considered to be not connected.

	boolean isConnected () {
		if (numberOfVertices == 0) return false;
		for (int i = 0; i < numberOfVertices; i++)
			vertices [i].booleanField = false;
		numberOfMarkedVertices = 0;
		dft (vertices [0]);
		return (numberOfMarkedVertices == numberOfVertices);
	}
	private int numberOfMarkedVertices = 0;
	private void dft (Vertex v) {
		v.booleanField = true; ++numberOfMarkedVertices;
		for (int i = 0; i < v.undirectedDegree; i++) {
			Vertex neighbor = v.undirectedNeighbors [i];
			if (! neighbor.booleanField) dft (neighbor);
		}
		for (int i = 0; i < v.undirectedDegree; i++) {
			Vertex neighbor = v.inNeighbors [i];
			if (! neighbor.booleanField) dft (neighbor);
		}
		for (int i = 0; i < v.undirectedDegree; i++) {
			Vertex neighbor = v.outNeighbors [i];
			if (! neighbor.booleanField) dft (neighbor);
		}
	}
}

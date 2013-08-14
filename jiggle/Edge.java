package jiggle;

/* Class for edges of a graph.  NOTE: the only mutable characteristics
of an edge are its label, directedness, and preferred length. */

public class Edge extends JiggleObject {

	private Vertex from, to; /* endpoints of the edge */
	private EdgeLabel label = null; /* label of edge */
	private boolean directed = false; /* is the edge directed? */
	private double preferredLength = 0; /* preferred length of edge */

	Edge (Graph g, Vertex f, Vertex t) {from = f; to = t; setContext (g);}

	Edge (Graph g, Vertex f, Vertex t, boolean dir) {
		from = f; to = t; setContext (g); directed = dir;
	}

	public Vertex getFrom () {return from;}
	public Vertex getTo () {return to;}

	EdgeLabel getLabel () {return label;}
	void setLabel (EdgeLabel lbl) {label = lbl;}

	boolean getDirected () {return directed;}
	void setDirected (boolean d) {directed = d;}

	double getPreferredLength () {return preferredLength;}
	void setPreferredLength (double len) {preferredLength = len;}

	double getLengthSquared () {return Vertex.getDistanceSquared (from, to);}
	double getLength () {return Vertex.getDistance (from, to);}

	public String toString () {
		return "(Edge: " + from + ", " + to + ", " +
			 (directed ? "directed" : "undirected") + ")";
	}
}
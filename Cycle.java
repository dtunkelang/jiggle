import jiggle.*;

public class Cycle extends Graph {

	public Cycle (int n) {super (); initialize (n);}
	public Cycle (int n, int d) {super (d); initialize (n);}

	private void initialize (int n) {
		Vertex V [] = new Vertex [n];
		for (int i = 0; i < n; i++) V [i] = insertVertex ();
		for (int i = 1; i < n; i++) insertEdge (V [i-1], V [i]);
		insertEdge (V [0], V [n - 1]);
	}
}

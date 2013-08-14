import jiggle.*;

public class CompleteBipartiteGraph extends Graph {

	public CompleteBipartiteGraph (int n) {super (); initialize (n);}
	public CompleteBipartiteGraph (int n, int d) {super (d); initialize (n);}

	private void initialize (int n) {
		Vertex V [] = new Vertex [2 * n];
		for (int i = 0; i < 2 * n; i++) V [i] = insertVertex ();
		for (int i = 0; i < n; i++)
			for (int j = n; j < 2 * n; j++) insertEdge (V [i], V [j]);
	}
}

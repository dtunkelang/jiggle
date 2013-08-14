import jiggle.*;

public class CompleteGraph extends Graph {

	public CompleteGraph (int n) {super (); initialize (n);}
	public CompleteGraph (int n, int d) {super (d); initialize (n);}

	private void initialize (int n) {
		Vertex V [] = new Vertex [n];
		for (int i = 0; i < n; i++) V [i] = insertVertex ();
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				insertEdge (V [i], V [j]);
			}
		}
	}
}

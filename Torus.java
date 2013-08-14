import jiggle.*;

public class Torus extends Graph {

	public Torus (int n) {super (); initialize (n);}
	public Torus (int n, int d) {super (d); initialize (n);}

	private void initialize (int n) {
		Vertex grid [] [] = new Vertex [n] [n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid [i] [j] = insertVertex ();
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				insertEdge (grid [i] [j], grid [i] [j+1]);
				insertEdge (grid [j] [i], grid [j+1] [i]);
			}
			insertEdge (grid [0] [i], grid [n-1] [i]);
			insertEdge (grid [i] [0], grid [i] [n-1]);
		}
	}
}

import jiggle.*;

public class SquareMesh extends Graph {

	public SquareMesh (int n) {super (); initialize (n);}
	public SquareMesh (int n, int d) {super (d); initialize (n);}

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
		}
	}
}

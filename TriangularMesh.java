import jiggle.*;

public class TriangularMesh extends Graph {

	public TriangularMesh (int h) {super (); initialize (h);}
	public TriangularMesh (int h, int d) {super (d); initialize (h);}

	private void initialize (int h) {
		int n = (h * (h + 1)) / 2;
		Vertex V [] = new Vertex [n];
		for (int i = 0; i < n; i++) V [i] = insertVertex ();
		int cur = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j <= i; j++) {
				if (j < i) insertEdge (V [cur], V [cur + 1]);
				if (i < h - 1) {
					insertEdge (V [cur], V [cur + i + 1]);
					insertEdge (V [cur], V [cur + i + 2]);
				}
				++cur;
			}
		}
	}
}

import jiggle.*;

public class Hypercube extends Graph {

	public Hypercube (int n) {super (); initialize (n);}
	public Hypercube (int n, int d) {super (d); initialize (n);}
 
	private void initialize (int n) {
		int twoToTheN = (int) Math.pow (2, n);
		Vertex V [] = new Vertex [twoToTheN];
		for (int i = 0; i < twoToTheN; i++)	{
			V [i] = insertVertex ();
			int j = i;
		}
		for (int i = 0; i < twoToTheN; i++) {
			for (int j = 1; j < twoToTheN; j*=2) {
				if ((i & j) == 0)
					insertEdge (V [i], V [i+j]);
			}
		}
	}
}

import jiggle.*;

public class CompleteBinaryTree extends Graph {

	public CompleteBinaryTree (int h) {super (); initialize (h);}
	public CompleteBinaryTree (int h, int d) {super (d); initialize (h);}

	private void initialize (int h) {
		int twoToTheHMinusOne = (int) Math.pow (2, h) - 1;
		Vertex V [] = new Vertex [twoToTheHMinusOne];
		for (int i = 0; i < twoToTheHMinusOne; i++)
			V [i] = insertVertex ();
		for (int i = 1; i < twoToTheHMinusOne; i++)
			insertEdge (V [(i-1)/2], V [i]);
	}
}

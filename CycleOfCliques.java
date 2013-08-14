import jiggle.*;

public class CycleOfCliques extends Graph {

	public CycleOfCliques (int numberOfCliques, int sizeOfClique) {
		super (); initialize (numberOfCliques, sizeOfClique);
	}
	public CycleOfCliques (int numberOfCliques, int sizeOfClique, int d) {
		super (d); initialize (numberOfCliques, sizeOfClique);
	}

	private void initialize (int numberOfCliques, int sizeOfClique) {
		int n = numberOfCliques * sizeOfClique;
		Vertex V [] = new Vertex [n];
		for (int i = 0; i < n; i++) V [i] = insertVertex ();
		for (int i = 0; i < numberOfCliques ; i++) {
			int cur = i * sizeOfClique;
			if (i < numberOfCliques - 1)
				insertEdge (V [cur], V [cur + sizeOfClique]);
			else insertEdge (V [cur], V [0]);
			for (int j = cur + 1; j < cur + sizeOfClique; j++) {
				for (int k = cur; k < j; k++) {
					insertEdge (V [k], V [j]);
				}
			}
		}
	}
}

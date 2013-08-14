package jiggle;

public abstract class SpringLaw extends ForceLaw {

	protected double preferredEdgeLength;

	protected SpringLaw (Graph g, double k) {
		super (g); preferredEdgeLength = k;
	}

	void apply (double [][] negativeGradient) {
		int m = graph.numberOfEdges, d = graph.getDimensions ();
		for (int i = 0; i < m; i++) {
			Edge e = graph.edges [i];
			Vertex from = e.getFrom (), to = e.getTo ();
			double fromWeight = from.getWeight (), toWeight = to.getWeight ();
			int f = from.intField, t = to.intField;
			double w = Math.min (springAttraction (e), cap / e.getLength ());
			double fromCoords [] = from.getCoords ();
			double toCoords [] = to.getCoords ();
			for (int j = 0; j < d; j++) {
				double force = (toCoords [j] - fromCoords [j]) * w;
				negativeGradient [f] [j] += force * toWeight;
				negativeGradient [t] [j] -= force * fromWeight;
			}
		}
	}

	abstract double springAttraction (Edge e);
}


package jiggle;

public class ProjectionConstraint extends Constraint {

	private int dimensions = 0;

	public ProjectionConstraint (Graph g, int d) {
		super (g); dimensions = d;
	}
	
	void apply (double [][] penalty) {
		int d = graph.getDimensions ();
		int n = graph.numberOfVertices;
		for (int i = 0; i < n; i++) {
			double coords [] = graph.vertices [i].getCoords ();
			for (int j = dimensions; j < d; j++) {
				penalty [i] [j] += (- coords [j]);
			}
		}
	}
}


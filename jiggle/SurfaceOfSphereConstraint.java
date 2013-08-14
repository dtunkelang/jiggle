package jiggle;

public class SurfaceOfSphereConstraint extends Constraint {

	private double radius;

	public SurfaceOfSphereConstraint (Graph g) {
		super (g); radius = 0;
	}

	public SurfaceOfSphereConstraint (Graph g, double r) {
		super (g); radius = r;
	}
	
	void apply (double [][] penalty) {
		int d = graph.getDimensions ();
		int n = graph.numberOfVertices;
		double center [] = new double [d], sum [] = new double [d];
		for (int i = 0; i < d; i++) center [i] = sum [i] = 0;
		for (int i = 0; i < n; i++) {
			double coords [] = graph.vertices [i].getCoords ();
			for (int j = 0; j < d; j++) center [j] += coords [j] / n;
		}
		double r = radius;
		if (r == 0) {
			for (int i = 0; i < n; i++) {
				double coords [] = graph.vertices [i].getCoords ();
				double distanceSquared = 0;
				for (int j = 0; j < d; j++) {
					distanceSquared += square (coords [j] - center [j]);
				}
				r += Math.sqrt (distanceSquared);
			}
			r = r / n;
		}

		for (int i = 0; i < n; i++) {
			double coords [] = graph.vertices [i].getCoords ();
			double distanceSquared = 0;
			for (int j = 0; j < d; j++) {
				distanceSquared += square (coords [j] - center [j]);
			}
			double p = r - Math.sqrt (distanceSquared);
			for (int j = 0; j < d; j++) {
				penalty [i] [j] += p * (coords [j] - center [j]);
				sum [j] += p * (coords [j] - center [j]);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				penalty [i] [j] -= sum [j] / n;
			}
		}

	}
}


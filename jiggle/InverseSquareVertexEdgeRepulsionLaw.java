package jiggle;

public class InverseSquareVertexEdgeRepulsionLaw extends VertexEdgeRepulsionLaw {

	public InverseSquareVertexEdgeRepulsionLaw (Graph g, double k) {
		super (g, k, 1);
	}

	public InverseSquareVertexEdgeRepulsionLaw (Graph g, double k, double s) {
		super (g, k, s);
	}

	double pairwiseRepulsion (Cell c1, Cell c2) {
		double k = preferredEdgeLength + Cell.sumOfRadii (c1, c2);
		double d = Cell.getDistance (c1, c2);
		if (d >= k) return 0; else return cube (k / d) - k / d;
	}

	double pairwiseRepulsion (Cell cell, double [] coords) {
		double k = preferredEdgeLength + Cell.radius (cell, coords);
		double d = Cell.getDistance (cell, coords);
		if (d >= k) return 0; else return cube (k / d) - k / d;
	}
}
package jiggle;

public class InverseVertexEdgeRepulsionLaw extends VertexEdgeRepulsionLaw {

	public InverseVertexEdgeRepulsionLaw (Graph g, double k) {
		super (g, k, 1);
	}

	public InverseVertexEdgeRepulsionLaw (Graph g, double k, double s) {
		super (g, k, s);
	}

	double pairwiseRepulsion (Cell c1, Cell c2) {
		double k = preferredEdgeLength + Cell.sumOfRadii (c1, c2);
		double dSquared = Cell.getDistanceSquared (c1, c2);
		if (dSquared >= square (k)) return 0; 
		else return k * k / dSquared - k / Math.sqrt (dSquared);
	}

	double pairwiseRepulsion (Cell cell, double [] coords) {
		double k = preferredEdgeLength + Cell.radius (cell, coords);
		double dSquared = Cell.getDistanceSquared (cell, coords);
		if (dSquared >= square (k)) return 0; 
		else return k * k / dSquared - k / Math.sqrt (dSquared);
	}
}
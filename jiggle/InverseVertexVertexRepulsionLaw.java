package jiggle;

public class InverseVertexVertexRepulsionLaw extends VertexVertexRepulsionLaw {

	public InverseVertexVertexRepulsionLaw (Graph g, double k) {
		super (g, k);
	}

	double pairwiseRepulsion (Cell c1, Cell c2) {
		double k = preferredEdgeLength + Cell.sumOfRadii (c1, c2);
		return k * k / Cell.getDistanceSquared (c1, c2);
	}
}
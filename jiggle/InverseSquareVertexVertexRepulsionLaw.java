package jiggle;

public class InverseSquareVertexVertexRepulsionLaw extends VertexVertexRepulsionLaw {

	public InverseSquareVertexVertexRepulsionLaw (Graph g, double k) {
		super (g, k);
	}

	double pairwiseRepulsion (Cell c1, Cell c2) {
		double k = preferredEdgeLength + Cell.sumOfRadii (c1, c2);
		return cube (k / Cell.getDistance (c1, c2));
	}
}
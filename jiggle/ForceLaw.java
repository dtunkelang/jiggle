package jiggle;

public abstract class ForceLaw extends JiggleObject {

	abstract void apply (double [][] negativeGradient);

	protected Graph graph;

	protected ForceLaw (Graph g) {graph = g;}

	protected double cap = Double.MAX_VALUE / 1000;
	double getCap () {return cap;}
	void setCap (double c) {cap = c;}
}


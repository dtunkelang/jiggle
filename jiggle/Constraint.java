package jiggle;

public abstract class Constraint extends JiggleObject {

	protected Graph graph;

	protected Constraint (Graph g) {graph = g;}

	abstract void apply (double [][] penalty);
}


package jiggle;

public abstract class ForceDirectedOptimizationProcedure extends JiggleObject {

	protected Graph graph;
	protected ForceModel forceModel;

	protected boolean constrained = false;
	public boolean getConstrained () {return constrained;}
	public void setConstrained (boolean c) {constrained = c;}

	ForceDirectedOptimizationProcedure (Graph g, ForceModel fm) {
		graph = g; forceModel = fm;
	}

	public abstract double improveGraph ();
}

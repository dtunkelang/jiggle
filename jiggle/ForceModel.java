package jiggle;

import java.util.Vector;
import java.util.Enumeration;

public class ForceModel {

	protected Graph graph = null;
	protected double preferredEdgeLength;

	private Vector<ForceLaw> forceLaws = new Vector<ForceLaw> ();
	private Vector<Constraint> constraints = new Vector<Constraint> ();

	public ForceModel (Graph g) {graph = g;}

	double getPreferredEdgeLength () {return preferredEdgeLength;}
	void setPreferredEdgeLength (double k) {preferredEdgeLength = k;}

	public void addForceLaw (ForceLaw fl) {forceLaws.addElement (fl);}
	public void removeForceLaw (ForceLaw fl) {forceLaws.removeElement (fl);}

	public void addConstraint (Constraint c) {constraints.addElement (c);}
	public void removeConstraint (Constraint c) {constraints.removeElement (c);}
	
	void getNegativeGradient (double [] [] negativeGradient) {
		int n = graph.numberOfVertices, d = graph.getDimensions ();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				negativeGradient [i] [j] = 0;
			}
			graph.vertices [i].intField = i;
		}
		for (Enumeration en = forceLaws.elements (); en.hasMoreElements ();)
			((ForceLaw) (en.nextElement ())).apply (negativeGradient);
	}

	void getPenaltyVector (double [] [] penaltyVector) {
		int n = graph.numberOfVertices, d = graph.getDimensions ();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < d; j++) {
				penaltyVector [i] [j] = 0;
			}
			graph.vertices [i].intField = i;
		}
		for (Enumeration en = constraints.elements (); en.hasMoreElements ();)
			((Constraint) (en.nextElement ())).apply (penaltyVector);
	}
}
package jiggle;

public class LinearSpringLaw extends SpringLaw {

	public LinearSpringLaw (Graph g, double k) {super (g, k);}

	double springAttraction (Edge e) {
		double r = Cell.sumOfRadii (e.getFrom (), e.getTo ());
		if (r == 0) return 1; else return 1 - r / e.getLength ();
	}
}

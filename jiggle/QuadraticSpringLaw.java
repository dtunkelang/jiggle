package jiggle;

public class QuadraticSpringLaw extends SpringLaw {

	public QuadraticSpringLaw (Graph g, double k) {super (g, k);}		

	double springAttraction (Edge e) {
		double r = Cell.sumOfRadii (e.getFrom (), e.getTo ());
		double len = e.getLength ();
		return (len - r) / preferredEdgeLength;
	}
}

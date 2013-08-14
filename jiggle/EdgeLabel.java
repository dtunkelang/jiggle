package jiggle;

/* Class for edge labels. */

public class EdgeLabel extends Cell {

	String name;

	EdgeLabel (Edge e, String str) {setContext (e); name = str;}

	String getName () {return name;}
	void setName (String str) {name = str;}

	public String toString () {return "(EdgeLabel: " + name + ")";}
}
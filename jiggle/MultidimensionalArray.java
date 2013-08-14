package jiggle;

public class MultidimensionalArray extends JiggleObject {

	private int dimensions = 0;
	private int size [] = null;
	private int numberOfCells = 0;
	private Object cells [] = null;

	MultidimensionalArray (int d, int [] s) {
		dimensions = d; size = new int [d]; numberOfCells = 1;
		for (int i = 0; i < d; i++) {
			numberOfCells *= (size [i] = s [i]);
		}
		cells = new Object [numberOfCells];
	}

	int getDimensions () {return dimensions;}

	Object get (int [] index) {return cells [rankOf (index)];}

	void set (int [] index, Object obj) {cells [rankOf (index)] = obj;}

	private int rankOf (int [] index) {
		int rank = 0, column = 1;
		for (int i = 0; i < dimensions; i++) {
			rank += index [i] * column;
			column *= size [i];
		}
		return rank;
	}
}

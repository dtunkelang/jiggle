package jiggle;

/* Methods for manipulating dynamic arrays of JiggleObjects. */

public abstract class DynamicArray {

	public static Vertex [] add (Vertex [] arr, int size, Vertex elem) {
		if (size == arr.length) {
			Vertex newArr [] = new Vertex [2 * size];
			for (int i = 0; i < size; i++) newArr [i] = arr [i];
			arr = newArr;
		}
		arr [size] = elem; return arr;
	}

	public static Edge [] add (Edge [] arr, int size, Edge elem) {
		if (size == arr.length) {
			Edge newArr [] = new Edge [2 * size];
			for (int i = 0; i < size; i++) newArr [i] = arr [i];
			arr = newArr;
		}
		arr [size] = elem; return arr;
	}

	public static void remove (JiggleObject [] arr, int size, JiggleObject elem)
			throws NotFoundException {
		for (int i = 0; i < size; i++)
			if (arr [i] == elem) {arr [i] = arr [size - 1]; return;}
		throw new NotFoundException ();
	}
}

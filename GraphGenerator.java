import jiggle.*;
import java.awt.*; import java.awt.event.*;

public class GraphGenerator extends Dialog
                            implements ActionListener {

	public static final long serialVersionUID = 1L;

	JiggleFrame parent = null;
	CheckboxGroup graphType = new CheckboxGroup ();
	Checkbox path = new Checkbox ("Path");
	Checkbox cycle = new Checkbox ("Cycle");
	Checkbox binaryTree = new Checkbox ("Complete Binary Tree");
	Checkbox randomTree = new Checkbox ("Random Tree");
	Checkbox cycleOfCliques = new Checkbox ("Cycle of Cliques");
	Checkbox triangularMesh = new Checkbox ("Triangular Mesh");
	Checkbox squareMesh = new Checkbox ("Square Mesh");
	Checkbox torus = new Checkbox ("Torus");
	Checkbox hypercube = new Checkbox ("Hypercube");
	Checkbox completeBipartiteGraph = new Checkbox ("Complete Bipartite Graph");
	Checkbox completeGraph = new Checkbox ("Complete Graph");
	Checkbox randomGraph = new Checkbox ("Random Connected Graph");
	TextField nField = new TextField ();
	TextField mField = new TextField ();
	TextField dField = new TextField ("2");
	Button OK = new Button ("OK");
	Button CANCEL = new Button ("CANCEL");

	public GraphGenerator (JiggleFrame p, int x, int y) {
		super (p, "Automatically Generate Graph", true); parent = p;
		setLayout (new GridLayout (15, 3, 20, 15));
		nField.addActionListener (this);
		mField.addActionListener (this);
		dField.addActionListener (this);
		OK.addActionListener (this);
		CANCEL.addActionListener (this);
		nField.setEditable (true); mField.setEditable (true); dField.setEditable (true); 
		path.setCheckboxGroup (graphType);
		cycle.setCheckboxGroup (graphType);
		binaryTree.setCheckboxGroup (graphType);
		randomTree.setCheckboxGroup (graphType);
		cycleOfCliques.setCheckboxGroup (graphType);
		triangularMesh.setCheckboxGroup (graphType);
		squareMesh.setCheckboxGroup (graphType);
		torus.setCheckboxGroup (graphType);
		hypercube.setCheckboxGroup (graphType);
		completeBipartiteGraph.setCheckboxGroup (graphType);
		completeGraph.setCheckboxGroup (graphType);
		randomGraph.setCheckboxGroup (graphType);
		add (path); add (new Label ("")); add (new Label ("n vertices")); 
		add (cycle); add (new Label ("")); add (new Label ("n vertices")); 
		add (binaryTree); add (new Label ("")); add (new Label ("2^n - 1 vertices, 2^n - 2 edges"));
		add (randomTree); add (new Label ("")); add (new Label ("n vertices, n - 1 edges"));
		add (cycleOfCliques); add (new Label ("")); add (new Label ("n * m vertices")); 
		add (triangularMesh); add (new Label ("")); add (new Label ("n(n-1)/2 vertices")); 
		add (squareMesh); add (new Label ("")); add (new Label ("n^2 vertices")); 
		add (torus); add (new Label ("")); add (new Label ("n^2 vertices")); 
		add (hypercube); add (new Label ("")); add (new Label ("2^n vertices"));
		add (completeBipartiteGraph); add (new Label ("")); add (new Label ("2n vertices")); 
		add (completeGraph); add (new Label ("")); add (new Label ("n vertices")); 
		add (randomGraph); add (new Label ("")); add (new Label ("n vertices, m edges"));
		add (new Label ("n:")); add (new Label ("m:")); add (new Label ("Dimensions (at least 2):"));
		add (nField); add (mField); add (dField);
		add (OK); add (new Label ("")); add (CANCEL);
		int w = 800, h = 600;
		setSize (w, h);
		setLocation (Math.max (0, x - w/2), Math.max (0, y - h/2));
	}

	private int stringToInt (String s) {
		try {return new Integer (s).intValue ();} catch (Exception exc) {return 0;}
	}

	public void actionPerformed (ActionEvent evt) {
		Object source = evt.getSource ();
		if (source == CANCEL) {setVisible (false); return;}
		int n = stringToInt (nField.getText ());
		int m = stringToInt (mField.getText ());
		int d = stringToInt (dField.getText ());
		if (n <= 0) {setVisible (false); return;}
		if (d < 2) {setVisible (false); return;}
		if (cycle.getState () && (n < 3)) {
			setVisible (false); return;
		}
		if (cycleOfCliques.getState () && ((n < 3) || (m < 1))) {
			setVisible (false); return;
		}
		if (randomGraph.getState () && ((m < n - 1) || (m > (n * (n - 1)) / 2))) {
			setVisible (false); return;
		}
		Graph g = null;
		if (path.getState ()) g = new Path (n, d);
		else if (cycle.getState ()) g = new Cycle (n, d);
		else if (binaryTree.getState ()) g = new CompleteBinaryTree (n, d);
		else if (randomTree.getState ()) g = new RandomConnectedGraph (n, n - 1, d);
		else if (cycleOfCliques.getState ()) g = new CycleOfCliques (n, m, d);
		else if (triangularMesh.getState ()) g = new TriangularMesh (n, d);
		else if (squareMesh.getState ()) g = new SquareMesh (n, d);
		else if (torus.getState ()) g = new Torus (n, d);
		else if (hypercube.getState ()) g = new Hypercube (n, d);
		else if (completeBipartiteGraph.getState ()) g = new CompleteBipartiteGraph (n, d);
		else if (completeGraph.getState ()) g = new CompleteGraph (n, d);
		else if (randomGraph.getState ()) g = new RandomConnectedGraph (n, m, d);
		else {setVisible (false); return;}
		parent.setGraph (g); parent.scramble (); setVisible (false);
	}
}

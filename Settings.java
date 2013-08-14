import jiggle.*;
import java.awt.*; import java.awt.event.*;

public class Settings extends Dialog implements ActionListener {

	public static final long serialVersionUID = 1L;

	JiggleFrame parent = null;

	Panel edgeLengthPanel = new Panel ();
	Label edgeLengthLabel = new Label ("Edge Length:");
	Slider edgeLength = new Slider (0, 200, 50);

	Panel sizePanel = new Panel ();
	Label vertexWidthLabel = new Label ("Vertex Width:");
	Slider vertexWidth = new Slider (0, 50, 10);
	Label vertexHeightLabel = new Label ("Vertex Height:");
	Slider vertexHeight = new Slider (0, 50, 10);
	Checkbox useVertexSize = new Checkbox ("Consider Vertex Size for Layout");

	Panel optimizationProcedurePanel1 = new Panel ();
	Label optimizationProcedureLabel = new Label ("Optimization Procedure:");
	CheckboxGroup optimizationProcedure = new CheckboxGroup ();
	Checkbox steepestDescent = new Checkbox ("steepest descent");
	Checkbox conjugateGradients = new Checkbox ("conjugate gradients");

	Panel optimizationProcedurePanel2 = new Panel ();
	Label accuracyLabel = new Label ("Accuracy of Line Search:");
	Slider accuracyOfLineSearch = new Slider (1, 0, 0.5);
	Label restartLabel = new Label ("Restart Threshold for CG:");
	Slider restartThreshold = new Slider (0, 1, 0.2);

	Panel springPanel = new Panel ();
	Label springLabel = new Label ("Springs:");
	CheckboxGroup springLaw = new CheckboxGroup ();
	Checkbox linearSpring = new Checkbox ("linear");
	Checkbox quadraticSpring = new Checkbox ("quadratic");

	Panel vvRepulsionPanel = new Panel ();
	Label vvRepulsionLabel = new Label ("Vertex-Vertex Repulsion:");
	CheckboxGroup vertexVertexRepulsionLaw = new CheckboxGroup ();
	Checkbox vvInverse = new Checkbox ("inverse");
	Checkbox vvInverseSquare = new Checkbox ("inverse-square");
	Checkbox vvHybrid = new Checkbox ("hybrid");

	Panel barnesHutPanel = new Panel ();
	Checkbox useBarnesHut = new Checkbox ("Use Barnes-Hut for Vertex-Vertex Repulsion");
	Label thetaLabel = new Label ("Theta:");
	Slider theta = new Slider (0, 1, 0.9);

	Panel constraintPanel = new Panel ();
	Label constrainDimensions1 = new Label ("Constrain to");
	TextField projDimField = new TextField (" ");
	Label constrainDimensions2 = new Label ("Dimensions");
	Checkbox constrainToSphere = new Checkbox ("Constrain to Surface of Sphere");

	Panel okCancelPanel = new Panel ();
	Button ok = new Button ("OK");
	Button cancel = new Button ("CANCEL");

	public Settings (JiggleFrame p, int x, int y) {
		super (p, "Settings", true); parent = p;

		int w = 800, h = 600;
		setSize (w, h);
		setLocation (Math.max (0, x - w/2), Math.max (0, y - h/2));

		setLayout (new GridLayout (9, 1));

		edgeLengthLabel.setAlignment (Label.CENTER);
		edgeLengthPanel.add (edgeLengthLabel);
		edgeLengthPanel.add (edgeLength);
		add (edgeLengthPanel);

		vertexWidthLabel.setAlignment (Label.CENTER);
		vertexHeightLabel.setAlignment (Label.CENTER);
		useVertexSize.setState (true);
		sizePanel.add (vertexWidthLabel);
		sizePanel.add (vertexWidth);
		sizePanel.add (vertexHeightLabel);
		sizePanel.add (vertexHeight);
		sizePanel.add (new Label ("          "));
		sizePanel.add (useVertexSize);
		add (sizePanel);

		optimizationProcedureLabel.setAlignment (Label.CENTER);
		optimizationProcedurePanel1.add (optimizationProcedureLabel);
		steepestDescent.setCheckboxGroup (optimizationProcedure);
		conjugateGradients.setCheckboxGroup (optimizationProcedure);
		conjugateGradients.setState (true);
		optimizationProcedurePanel1.add (steepestDescent);
		optimizationProcedurePanel1.add (conjugateGradients);
		add (optimizationProcedurePanel1);

		accuracyLabel.setAlignment (Label.CENTER);
		restartLabel.setAlignment (Label.CENTER);
		optimizationProcedurePanel2.add (accuracyLabel);
		optimizationProcedurePanel2.add (accuracyOfLineSearch);
		optimizationProcedurePanel2.add (restartLabel);
		optimizationProcedurePanel2.add (restartThreshold);
		add (optimizationProcedurePanel2);

		springLabel.setAlignment (Label.CENTER);
		springPanel.add (springLabel);
		linearSpring.setCheckboxGroup (springLaw);
		quadraticSpring.setCheckboxGroup (springLaw);
		quadraticSpring.setState (true);
		springPanel.add (linearSpring);
		springPanel.add (quadraticSpring);
		add (springPanel);

		vvRepulsionLabel.setAlignment (Label.CENTER);
		vvRepulsionPanel.add (vvRepulsionLabel);
		vvInverse.setCheckboxGroup (vertexVertexRepulsionLaw);
		vvInverseSquare.setCheckboxGroup (vertexVertexRepulsionLaw);
		vvHybrid.setCheckboxGroup (vertexVertexRepulsionLaw);
		vvInverseSquare.setState (true);
		vvRepulsionPanel.add (vvInverse);
		vvRepulsionPanel.add (vvInverseSquare);
		vvRepulsionPanel.add (vvHybrid);
		add (vvRepulsionPanel);

		thetaLabel.setAlignment (Label.CENTER);
		barnesHutPanel.add (useBarnesHut);
		barnesHutPanel.add (thetaLabel);
		barnesHutPanel.add (theta);
		add (barnesHutPanel);

		constraintPanel.add (constrainDimensions1);
		constraintPanel.add (projDimField);
		constraintPanel.add (constrainDimensions2);
		constraintPanel.add (constrainToSphere);
		add (constraintPanel);

		ok.addActionListener (this);
		cancel.addActionListener (this);
		okCancelPanel.add (ok);
		okCancelPanel.add (new Label ("          "));
		okCancelPanel.add (cancel);
		add (okCancelPanel);

	}

	private int stringToInt (String s) {
		try {return new Integer (s.trim ()).intValue ();} catch (Exception exc) {return 0;}
	}

	public void actionPerformed (ActionEvent evt) {
		Object source = evt.getSource ();
		if (parent.graph == null) {setVisible (false); return;}
		if (source == ok) {applySettings (); setVisible (false);}
		if (source == cancel) {setVisible (false);}
	}

	public void applySettings () {
		Graph g = parent.graph;
		int d = g.getDimensions ();
		double k = Math.max (1, edgeLength.getValue ());
		SpringLaw springLaw = null;
		if (linearSpring.getState ())
			springLaw = new LinearSpringLaw (g, k);
		if (quadraticSpring.getState ())
			springLaw = new QuadraticSpringLaw (g, k);
		VertexVertexRepulsionLaw vvRepulsionLaw = null;
		if (vvInverse.getState ())
			vvRepulsionLaw = new InverseVertexVertexRepulsionLaw (g, k);
		if (vvInverseSquare.getState ())
			vvRepulsionLaw = new InverseSquareVertexVertexRepulsionLaw (g, k);
		if (vvHybrid.getState ())
			vvRepulsionLaw = new HybridVertexVertexRepulsionLaw (g, k);
		if (useBarnesHut.getState ())
			vvRepulsionLaw.setBarnesHutTheta (theta.getValue ());
		ForceModel fm = new ForceModel (g);
		fm.addForceLaw (springLaw);
		fm.addForceLaw (vvRepulsionLaw);
		int projDim = stringToInt (projDimField.getText ());
		if (projDim > 0) {
			fm.addConstraint (new ProjectionConstraint (g, projDim));
		}
		if (constrainToSphere.getState ()) {
			fm.addConstraint (new SurfaceOfSphereConstraint (g));
		}
		parent.forceModel = fm;
		FirstOrderOptimizationProcedure opt = null;
		double acc = Math.max (accuracyOfLineSearch.getValue (), 0.00000001);
		double rt = restartThreshold.getValue ();
		if (steepestDescent.getState ())
			opt = new SteepestDescent (g, fm, acc);
		if (conjugateGradients.getState ())
			opt = new ConjugateGradients (g, fm, acc, rt);
		opt.setConstrained ((projDim > 0) ||
		                    constrainToSphere.getState ());
		parent.optimizationProcedure = opt;
		parent.vertexWidth = vertexWidth.getValue ();
		parent.vertexHeight = vertexHeight.getValue ();
		parent.useVertexSize = useVertexSize.getState ();
		for (int i = 0; i < g.numberOfVertices; i++) {
			double size [] = g.vertices [i].getSize ();
			if (parent.useVertexSize) {
				size [0] = parent.vertexWidth;
				size [1] = parent.vertexHeight;
				if (d > 2) {
					double avg = (size [0] + size [1]) / 2;
					for (int j = 2; j < d; j++) size [j] = avg;
				}
			}
			else {
				for (int j = 0; j < d; j++) size [j] = 0;
			}
		}
	}
}

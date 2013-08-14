// IMPORT COMMANDS

import jiggle.*;
import java.awt.*; import java.applet.Applet;
import java.io.*; import java.net.*; import java.util.*;
import java.awt.event.*;

// JIGGLEFRAME CLASS

public class JiggleFrame extends Frame implements ActionListener {

	public static final long serialVersionUID = 1L;

	int frameWidth = 800, frameHeight = 600;
	JiggleApplet parentApplet = null; int PORT;
	MenuBar menuBar; Menu menu;
	GraphGenerator generator = new GraphGenerator (this, frameWidth / 2, frameHeight / 2);
	Settings settings = new Settings (this, frameWidth / 2, frameHeight / 2);

	public Graph graph = null;
	public ForceModel forceModel = null;
	public FirstOrderOptimizationProcedure optimizationProcedure = null;
	public double vertexWidth = 10, vertexHeight = 10;
	public boolean useVertexSize = false;

	boolean isIterating = false, iteratingNow = false;

	Image offScreenImage = null;
	Graphics offScreenGraphics = null;
	int imageWidth = 0, imageHeight = 0;

	JiggleFrame () {
		super ("JIGGLE");
		setLayout (null);
		setBackground (Color.white);
		setFont (new Font ("TimesRoman", Font.BOLD, 16));
		menuBar = new MenuBar (); menu = new Menu ("Commands");
		menu.add ("Generate Graph"); 
		menu.add ("Run"); 
		menu.add ("Stop"); 
		menu.add ("Scramble"); 
		menu.add ("Settings"); 
		menu.add ("Quit"); 
		menuBar.add (menu); setMenuBar (menuBar);
		for (int i = 0; i < menu.getItemCount (); i++)
			menu.getItem (i).addActionListener (this);
		setSize (frameWidth, frameHeight);
		setVisible (true);
		imageWidth = getSize ().width; imageHeight = getSize ().height;
		offScreenImage = createImage (imageWidth, imageHeight);
		offScreenGraphics = offScreenImage.getGraphics ();
		long time = System.currentTimeMillis () / 1000;
		while (true) {
			Thread.yield ();
			if (graph == null) continue;
			int n = graph.numberOfVertices;
			if (isIterating) {
				iteratingNow = true;
				optimizationProcedure.improveGraph ();
				iteratingNow = false;
			}
			if (System.currentTimeMillis () / 1000 > time) {
				repaint ();
				time = System.currentTimeMillis () / 1000;
			}
		}
	}

	public void actionPerformed (ActionEvent evt) {
		Object source = evt.getSource ();
		if (source == menu.getItem (0)) {
			isIterating = false;
			while (iteratingNow) try {Thread.sleep (100);} catch (Exception e) {}
			generator.setVisible (true);
		}
		if ((source == menu.getItem (1)) && (graph != null)) {
			isIterating = false;
			while (iteratingNow) try {Thread.sleep (100);} catch (Exception e) {}
			settings.applySettings ();
			isIterating = true;
		}
		if ((source == menu.getItem (2)) && (graph != null)) {
			isIterating = false;
		}
		if ((source == menu.getItem (3)) && (graph != null)) {
			isIterating = false;
			while (iteratingNow) try {Thread.sleep (100);} catch (Exception e) {}
			scramble ();
		}
		if (source == menu.getItem (4)) {
			isIterating = false;
			while (iteratingNow) try {Thread.sleep (100);} catch (Exception e) {}
			settings.setVisible (true);
		}
		if (source == menu.getItem (5)) {
			try {System.exit (0);} catch (Exception exc) {}
			dispose ();
			return;
		}
		repaint ();
	}

	public void setGraph (Graph g) {
		isIterating = false;
		while (iteratingNow)
			try {Thread.sleep (50);} catch (Exception e) {}
		graph = g;
		settings.applySettings ();
	}

	void scramble () {
		int n = graph.numberOfVertices;
		double w = getSize ().width, h = getSize ().height;
		int d = graph.getDimensions ();
		for (int i = 0; i < n; i++) {
			double coords [] = graph.vertices [i].getCoords ();
			for (int j = 0; j < d; j++) coords [j] = Math.random () * w;
		}
		double sumX = 0, sumY = 0;
		for (int i = 0; i < n; i++) {
			double coords [] = graph.vertices [i].getCoords ();
			sumX += coords [0]; sumY += coords [1];
		}
		for (int i = 0; i < n; i++) {
			Vertex v = graph.vertices [i];
			double coords [] = graph.vertices [i].getCoords ();
			coords [0] += (w / 2) - (sumX / n);
			coords [1] += (h / 2) - (sumY / n);
		}
	}


	public void update (Graphics g) {
		if (graph == null) return;
		int width = getSize ().width, height = getSize ().height;
		if ((width != imageWidth) || (height != imageHeight)) {
			imageWidth = width; imageHeight = height;
			offScreenImage = createImage (width, height);
			offScreenGraphics = offScreenImage.getGraphics ();
		}
		offScreenGraphics.setColor (Color.white);
		offScreenGraphics.fillRect (0, 0, width, height);
		offScreenGraphics.setColor (Color.black);
		for (int i = 0; i < graph.numberOfVertices; i++) {
			Vertex v = graph.vertices [i];
			double coords [] = v.getCoords ();
			int x = (int) coords [0], y = (int) coords [1];
			int w = (int) vertexWidth, h = (int) vertexHeight;
			offScreenGraphics.fillRect (x - w / 2, y - h / 2, w, h);
		}
		for (int i = 0; i < graph.numberOfEdges; i++) {
			Edge e = graph.edges [i];
			Vertex from = e.getFrom (), to = e.getTo ();
			double f [] = from.getCoords (), t [] = to.getCoords ();
			int x1 = (int) f [0], y1 = (int) f [1];
			int x2 = (int) t [0], y2 = (int) t [1];
			offScreenGraphics.drawLine (x1, y1, x2, y2);
		}
		g.drawImage (offScreenImage, 0, 0, this);
	}
}

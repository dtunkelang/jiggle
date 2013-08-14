// IMPORT COMMANDS

import jiggle.*;
import java.awt.*; import java.applet.Applet;
import java.io.*; import java.net.*; import java.util.*;

// JIGGLEAPPLET CLASS

public class JiggleApplet extends Applet {

  public static final long serialVersionUID = 1L;

  JiggleFrame f = null;
  
  public void init () {
	f = new JiggleFrame ();
  }
}

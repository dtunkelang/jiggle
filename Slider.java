import java.awt.*; import java.awt.event.*;

public class Slider extends Panel implements AdjustmentListener {

	public static final long serialVersionUID = 1L;

	Scrollbar scrollbar = null;
	double minimum, maximum, value;
	Label valueLabel = null;

	public Slider (double min, double max, double initialValue) {
		setLayout (new GridLayout (2, 1, 0, 0));
		value = initialValue; minimum = min; maximum = max;
		int iVal = (int) (((value - minimum) / (maximum - minimum)) * 100);
		scrollbar = new Scrollbar (Scrollbar.HORIZONTAL, iVal, 0, 0, 101);
		scrollbar.setSize (100, 5);
		scrollbar.addAdjustmentListener (this);
		valueLabel = new Label (value + "");
		valueLabel.setAlignment (Label.CENTER);
		valueLabel.setFont (new Font ("TimesNewRoman", Font.PLAIN, 8));
		add (scrollbar);
		add (valueLabel);
	}

	public double getValue () {return value;}

	public void adjustmentValueChanged (AdjustmentEvent e) {
		int val = scrollbar.getValue ();
		value = minimum + val * (maximum - minimum) / 100.0;
		valueLabel.setText (value + "");
	}
}
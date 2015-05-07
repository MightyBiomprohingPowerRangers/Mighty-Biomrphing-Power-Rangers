package MBPR;

import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A JPanel which offers the functionality of a colour picker
 * @author MBPR
 *
 */
public class ColourPicker extends JPanel{

	private JButton select;
	private Color c1;
	private JPanel panel;


	/**
	 * Initialises the variables and creates the Colour Picker
	 * @param initialColour
	 */
	public ColourPicker(int initialColour)
	{
		int[] array = Canvas.getARGBarray(initialColour);
		c1 = new Color(array[1], array[2], array[3], array[0]);
		panel = new JPanel();
		panel.setBackground(c1);
		panel.setPreferredSize(new Dimension(20,20));

		select = new JButton("Choose colour");
		select.addActionListener(
				new ActionListener(){

					public void actionPerformed(ActionEvent event){
						Color c2 = c1;
						c2 = JColorChooser.showDialog(null, "pick", c2);
						if (c2 != null)
						{
							c1 = c2;
						}
						panel.setBackground(c1);
					}
				});
		add(panel, BorderLayout.NORTH);
		add(select, BorderLayout.CENTER);
		setVisible(true);
	}

	/**
	 * Returns the colour as an ARGB value
	 * @return ARGB
	 */
	public int getColour(){
		return Canvas.getARGBValue(c1.getAlpha(), c1.getRed(), c1.getGreen(), c1.getBlue());
	}

}
package MBPR;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GUI extends Component
{
	private Canvas c;
	
	public GUI() 
	{
		c = new Canvas();
	}
	
	public static void main(String[] args) 
	{
		JFrame f = new JFrame("Load Image Sample");

		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.add(new GUI());
		f.pack();
		f.setVisible(true);
	}
	
	public void paint(Graphics g) 
	{
		g.drawImage(c.getImg(), 0, 0, null);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension(500, 500);
	}
}

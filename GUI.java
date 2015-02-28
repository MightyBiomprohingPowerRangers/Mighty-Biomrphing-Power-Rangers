package MBPR;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GUI extends Component
{
	private Canvas canvas;
	
	public GUI() 
	{
		canvas = new Canvas();
	}
	
	public static void main(String[] args) 
	{
		JFrame f = new JFrame("Load Image Sample");

		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		GUI gui = new GUI();
		
		f.add(gui);
		f.pack();
		f.setVisible(true);
		Canvas c = new Grow().Grow(gui.getC());
		f.update(gui.getGraphics());
	}
	
	public Canvas getC() {
		return canvas;
	}

	public void setC(Canvas c) {
		this.canvas = c;
	}

	public void paint(Graphics g) 
	{
		g.drawImage(canvas.getImg(), 0, 0, null);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension(500, 500);
	}
}

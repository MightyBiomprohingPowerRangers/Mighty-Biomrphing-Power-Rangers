package MBPR;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

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
		long startTime = (new Date()).getTime();


		JFrame f = new JFrame("Load Image Sample");

		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		GUI gui = new GUI();
		f.setBackground(new Color(gui.canvas.getBgColour()));

		f.add(gui);
		f.pack();
		f.setVisible(true);

		for (int i = 0; i < 10; i++)
		{
			Canvas temp = gui.getCanvas();
			Grow grow = new Grow();
			gui.setCanvas(grow.Grow(temp));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			f.update(gui.getGraphics());
			gui.setCanvas(new Canvas());
		}
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		//System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas c) {
		this.canvas = c;
	}

	public void paint(Graphics g) 
	{
		g.drawImage(canvas.getImg(), 0, 0, null);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension(canvas.getxLength(), canvas.getyLength());
	}
}

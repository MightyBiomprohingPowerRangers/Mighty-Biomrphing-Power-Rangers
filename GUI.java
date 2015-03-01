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
		Grow grow = new Grow();
		Canvas first = grow.Grow(gui.getC());
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		f.update(gui.getGraphics());
		Canvas second = grow.Grow(first);
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		f.update(gui.getGraphics());
		first = grow.Grow(second);
		try {
		    Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		f.update(gui.getGraphics());
		second = grow.Grow(first);
		first = grow.Grow(second);
		second = grow.Grow(first);
		first = grow.Grow(second);
		second = grow.Grow(first);
		first = grow.Grow(second);
		second = grow.Grow(first);
		
//		for (int i = 1; i < 500; i++)
//		{
//			if (i % 2 ==0)
//				second = grow.Grow(first);
//			else
//				first = grow.Grow(second);
//			try {
//			    Thread.sleep(1000);                 //1000 milliseconds is one second.
//			} catch(InterruptedException ex) {
//			    Thread.currentThread().interrupt();
//			}
//			f.update(gui.getGraphics());
//			
//		}
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
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
		return new Dimension(canvas.getxLength(), canvas.getyLength());
	}
}

package MBPR;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;

public class GUI extends Component
{
	private Canvas canvas;
	private int rgb = Canvas.getARGBValue(200, 0, 0, 200);

	public GUI() 
	{
		generate();
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
		f.setBackground(new Color(Canvas.getARGBValue(200, 0, 0, 200)));

		f.add(gui);
		f.pack();
		f.setVisible(true);
		
		long startTime = (new Date()).getTime();
		
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}
	
	public void generate()
	{
		long startTime = (new Date()).getTime();
		canvas = new Canvas();
		for (int i = 0; i < 15; i++)
		{
			Canvas temp = getCanvas();
			Grow grow = new Grow(temp);
			setCanvas(grow.grow());
			if (getRGB() == Canvas.getARGBValue(200, 0, 0, 100))
			{
				temp.setBrushColour(Canvas.getARGBValue(200, 0, 0, 200));
				setRGB(Canvas.getARGBValue(201, 0, 0, 200));
				setCanvas(temp);
			}
			else if (getRGB() == Canvas.getARGBValue(200, 0, 0, 200))
			{
				temp.setBrushColour(Canvas.getARGBValue(200, 0, 0, 100));
				setRGB(Canvas.getARGBValue(200, 0, 0, 100));
				setCanvas(temp);
			}
			else if (getRGB() == Canvas.getARGBValue(201, 0, 0, 200))
			{
				temp.setBrushColour(Canvas.getARGBValue(200, 0, 0, 200));
				setRGB(Canvas.getARGBValue(200, 0, 0, 200));
				setCanvas(temp);
			}
		}
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
//		System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public int getRGB() {
		return rgb;
	}

	public void setRGB(int rgb) {
		this.rgb = rgb;
	}

	public void setCanvas(Canvas c) {
		this.canvas = c;
	}

	public void paint(Graphics g) 
	{
		g.drawImage(canvas.getImg(), 0, 0, null);
	}

	public Dimension getPreferredSize(int x, int y) 
	{
		return new Dimension(x, y);
	}
	
	public void pause(int ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

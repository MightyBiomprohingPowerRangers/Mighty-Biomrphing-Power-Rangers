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

		for (int i = 0; i < 50; i++)
		{
			Canvas temp = gui.getCanvas();
			f.update(gui.getGraphics());
			gui.pause(1000);
			Grow grow = new Grow(temp);
			gui.setCanvas(grow.grow());
			gui.pause(1000);
			f.update(gui.getGraphics());
			if (gui.getRGB() == Canvas.getARGBValue(200, 200, 0, 50))
			{
				temp.setBrushColour(Canvas.getARGBValue(200, 0, 0, 200));
				gui.setRGB(Canvas.getARGBValue(201, 0, 0, 200));
				gui.setCanvas(temp);
			}
			else if (gui.getRGB() == Canvas.getARGBValue(200, 0, 0, 200))
			{
				temp.setBrushColour(Canvas.getARGBValue(200, 200, 0, 50));
				gui.setRGB(Canvas.getARGBValue(200, 200, 0, 50));
				gui.setCanvas(temp);
			}
			else if (gui.getRGB() == Canvas.getARGBValue(201, 0, 0, 200))
			{
				temp.setBrushColour(Canvas.getARGBValue(200, 0, 0, 200));
				gui.setRGB(Canvas.getARGBValue(200, 0, 0, 200));
				gui.setCanvas(temp);
			}
		}
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
		//System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
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

	public Dimension getPreferredSize() 
	{
		return new Dimension(550, 550);
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

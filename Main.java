package MBPR;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main extends Component 
{
	BufferedImage img;
	boolean isRunning = true;
	int xx = 200;
	int yy = 200;

	public Main() 
	{
		img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);

		setBackground();
		setOrigin(xx,yy);
	}

	public void setBackground()
	{
		for (int x = 0; x < img.getWidth(); x++)
			for (int y = 0; y < img.getHeight(); y++)
			{
				img.setRGB(x, y, getColour(200, 0, 0, 255));
			}
	}

	public void setOrigin(int xcoord, int ycoord)
	{
		if (xcoord < 494 && xcoord > 6 && ycoord < 494 && ycoord > 6)
			for (int x = -5; x < 5; x++)
				for (int y = -5; y < 5; y++)
				{
					img.setRGB(x + xcoord, y + ycoord, getColour(200, 0, 255, 0));
				}
	}

	public void paint(Graphics g) 
	{
		g.drawImage(img, 0, 0, null);
	}

	public Dimension getPreferredSize() 
	{
		return new Dimension(500, 500);
	}

	private int getColour(int alpha, int red, int green, int blue)
	{
		alpha = alpha << 24;
		red = red << 16;
		green = green << 8;
		return alpha + red + green + blue;
	}

	public static void main(String[] args) 
	{
		JFrame f = new JFrame("Load Image Sample");

		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.add(new Main());
		f.pack();
		f.setVisible(true);
	}

}

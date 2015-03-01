package MBPR;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Grow 
{
	private Canvas c;
	private Random r;
	private int x;
	private int y;
	

	public Canvas Grow(Canvas c) 
	{
		this.c = c;
		x = c.getxOrigin();
		y = c.getyOrigin();
		r = new Random();
		moveBrush();
		return c;
	}

	public void moveBrush(/*int angle, int magnitude*/)
	{
		drawSLines();
	}

	public void drawLine(int cycles)
	{	
		for (int i = 1; i < cycles + 1; i++)
		{
			if (x > 10 && x < c.getxLength() - 10 && y > 10 && y < c.getyLength() - 10)
			{
				x+= r.nextInt(11) - 5;
				y+= r.nextInt(11) - 5;
			}
			c.drawPoint(x, y);
		}
	}

	public void drawSLines()
	{
		drawLine(1000);
	}


}

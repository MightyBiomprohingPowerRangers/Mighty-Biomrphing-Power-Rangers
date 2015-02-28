package MBPR;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Grow 
{
	Canvas c;
	Random r;
	
	public Canvas Grow(Canvas c) 
	{
		this.c = c;
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
		int x = 250;
		int y = 250;
		for (int i = 1; i < cycles + 1; i++)
		{
			x+= r.nextInt(11) - 5;
			y+= r.nextInt(11) - 5;
			c.drawPoint(x, y);
		}
	}
	
	public void drawSLines()
	{
		drawLine(5000);
	}
	

}

package MBPR;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Grow 
{
	private Canvas c;
	private Random r;
	private int x;
	private int y;
	private Unique cluster;
	

	public Canvas Grow(Canvas c) 
	{
		this.c = c;
		cluster = new Unique();
		x = c.getxOrigin();
		y = c.getyOrigin();
		r = new Random();
		moveBrush();
		//makeSymmetrical();
		return c;
	}

	public void moveBrush(/*int angle, int magnitude*/)
	{
		drawSLines();
		for (ClusterPoint p : cluster.points)
		{
			makeSymmetrical(p.getX(), p.getY());
		}
	}
	
	public void makeSymmetrical(int x, int y)
	{
		c.drawPoint(x, y);
		c.drawPoint(x, c.getyLength() -y);
		c.drawPoint(c.getxLength() -x, y);
		c.drawPoint(c.getxLength() -x, c.getyLength() -y);
	}

	public void drawLine(int cycles)
	{	
		for (int i = 1; i < cycles + 1; i++)
		{
			if (x > 10 && x < c.getxLength() - 10 && y > 10 && y < c.getyLength() - 10)
			{
				x+= r.nextInt(13) - 6;
				y+= r.nextInt(13) - 6;
			}
			cluster.addPoint(x, y);
		}
	}

	public void drawSLines()
	{
		drawLine(500);
	}


}

package MBPR;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Grow 
{
	private Canvas c;
	private Random r;
	private int x;
	private int y;
	private Cluster cluster;
	

	public Grow(Canvas c) 
	{
		this.c = c;
		cluster = new XMirror(new FullyRandom(500, 500));
		x = c.getxOrigin();
		y = c.getyOrigin();
		r = new Random();
	}
	
	public Canvas grow()
	{
		moveBrush();
		return c;
	}

	public void moveBrush(/*int angle, int magnitude*/)
	{
		c.drawCentredCluster(cluster);
	}
	
	public void makeSymmetrical(int x, int y)
	{
		c.drawPoint(x, y);
		c.drawPoint(x, c.getyLength() -y);
		c.drawPoint(c.getxLength() -x, y);
		c.drawPoint(c.getxLength() -x, c.getyLength() -y);
	}
}

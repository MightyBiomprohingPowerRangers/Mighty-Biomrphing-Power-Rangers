package MBPR;

import java.util.Random;

/**
 * Cluster implementation that can generate any cluster
 * @author MBPR
 *
 */
public class Unique extends Cluster 
{
	protected int maxX;
	protected int maxY;
	protected int length;
	protected int height;
	protected int width;
	protected int vector;
	protected double angle;
	protected double minAngle;
	protected double maxAngle;
	protected Random r;
	
	public Unique(int maxX, int maxY, int length, int height, int width, long seed) {
		this.maxX = maxX;
		this.maxY = maxY;
		this.length = length;
		this.height = height;
		this.width = width;
		r = new Random(seed);
		initialStyle();
	}
	
	public Unique(int maxX, int maxY, int length, int vector, double angle, double minAngle, double maxAngle, long seed) {
		this.maxX = maxX;
		this.maxY = maxY;
		this.length = length;
		this.vector = vector;
		this.angle = angle;
		this.minAngle = minAngle;
		this.maxAngle = maxAngle;
		r = new Random(seed);
		angleStyle();
	}
	
	private void initialStyle()
	{
		int x = 0;
		int y = 0;
		for (int i = 0; i < length*100; i++)
		{
			if (x > -(maxX/2 - 10) && x < (maxX/2 - 10) && y > -(maxY/2 - 10) && y < (maxY/2 - 10))
			{
				x+= r.nextInt(13) - 6 + height - 5;
				y+= r.nextInt(13) - 6 + width - 5;
			}
			points.add(new ClusterPoint(x, y));
		}
	}
	
	private void angleStyle()
	{
		int x = 0;
		int y = 0;
		for (int i = 0; i < length*100; i++)
		{
			if (x > -(maxX/2 - 10) && x < (maxX/2 - 10) && y > -(maxY/2 - 10) && y < (maxY/2 - 10))
			{
				
				double xD = vector*Math.sin(angle + 10*r.nextDouble() - 5);
				double yD = vector*Math.cos(angle + 10*r.nextDouble() - 5);
				x += (int) xD;
				y += (int) yD;
			}
			points.add(new ClusterPoint(x, y));
		}
	}
}

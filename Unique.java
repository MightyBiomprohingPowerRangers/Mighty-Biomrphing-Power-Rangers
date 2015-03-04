package MBPR;

import java.util.Random;

public abstract class Unique extends Cluster 
{
	protected int maxX;
	protected int maxY;
	public Unique(int maxX, int maxY) 
	{
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	public void addPoint(int x, int y)
	{
		points.add(new ClusterPoint(x, y));
	}

}

package MBPR;

import java.util.ArrayList;

public abstract class Cluster 
{
	protected ArrayList<ClusterPoint> points = new ArrayList<ClusterPoint>();
	protected ClusterPoint centre;
	protected int counter = 0;
	
	public Cluster() 
	{
		
	}
	
	public ClusterPoint next()
	{
		if (hasNext())
		{
			int counter2 = this.counter;
			counter++;
			return points.get(counter2);
		}
		return null;
	}
	
	public boolean hasNext()
	{
		if (this.counter < points.size())
			return true;
		return false;
	}
}
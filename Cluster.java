package MBPR;

import java.util.ArrayList;

public abstract class Cluster 
{
	ArrayList<ClusterPoint> points = new ArrayList<ClusterPoint>();
	ClusterPoint centre;
	int counter = 0;
	
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
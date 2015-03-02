package MBPR;

public class Unique extends Cluster 
{

	public Unique() 
	{

	}
	
	public void addPoint(int x, int y)
	{
		points.add(new ClusterPoint(x, y));
	}

}

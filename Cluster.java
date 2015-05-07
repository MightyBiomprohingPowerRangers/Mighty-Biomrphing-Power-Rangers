package MBPR;

import java.util.ArrayList;

/**
 * Abstract class that defines a set of points to be drawn
 * @author MBPR
 *
 */
public abstract class Cluster 
{
	/**
	 * A list of CLusterPoints within the Cluster
	 */
	protected ArrayList<ClusterPoint> points = new ArrayList<ClusterPoint>();
	
	/**
	 * Counts through the points when next() is called
	 */
	protected int counter = 0;
	
	/**
	 * Returns the next counter stored within the list and increments the counter
	 * @return ClusterPoint
	 */
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
	
	/**
	 * Returns true if there are more points in the list to draw
	 * @return Boolean
	 */
	public boolean hasNext()
	{
		if (this.counter < points.size())
			return true;
		return false;
	}
	
}
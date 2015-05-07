package MBPR;

/**
 * Defines a point to be drawn using an x and y coordinate
 * @author MBPR
 *
 */
public class ClusterPoint 
{
	private int x;
	private int y;
	
	/**
	 * Initialises the coordinates
	 * @param x
	 * @param y
	 */
	public ClusterPoint(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the x coordinate
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y coordinate
	 * @return y
	 */
	public int getY() {
		return y;
	}
}
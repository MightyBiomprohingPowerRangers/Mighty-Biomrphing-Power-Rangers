package MBPR;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Defines where the drawing should occur and creates a brush based off the Cluster implementation to draw to the BufferedImage
 * @author MBPR
 *
 */
public class Canvas
{
	/**
	 * The image to be drawn to
	 */
	private BufferedImage img;
	/**
	 * The width of the BufferedImage
	 */
	private int xLength;
	/**
	 * The height of the BufferedImage
	 */
	private int yLength;
	/**
	 * The x location of the most central point and starting position of the brush
	 */
	private int xOrigin;
	/**
	 * The y location of the most central point and starting position of the brush
	 */
	private int yOrigin;
	/**
	 * Colour of the brush as an ARGB
	 */
	private int brushColour;
	/**
	 * Colour of the background as an ARGB
	 */
	private int bgColour;

	/**
	 * Constructor initialises all the variables, draws the background and the origin
	 * @param xLength
	 * @param yLength
	 */
	public Canvas(int xLength, int yLength) 
	{
		xOrigin = xLength/2;
		yOrigin = yLength/2;
		this.xLength = xLength;
		this.yLength = yLength;
		img = new BufferedImage(xLength, yLength, BufferedImage.TYPE_INT_ARGB);
		bgColour = getARGBValue(200, 200, 0, 50);
		drawBackground(bgColour);
		brushColour = getARGBValue(200, 0, 0, 200);
		drawPoint(xOrigin,yOrigin);
	}
	
	/**
	 * Sets the colour of all the pixels to the parameter
	 * @param rgba
	 */
	private void drawBackground(int rgba)
	{
		for (int x = 0; x < img.getWidth(); x++)
			for (int y = 0; y < img.getHeight(); y++)
			{
				img.setRGB(x, y, rgba);
			}
	}

	/**
	 * Sets the brush colour
	 * @param brushColour
	 */
	public void setBrushColour(int brushColour) {
		this.brushColour = brushColour;
	}

	/**
	 * Draws a circular point at the specified x and y
	 * @param xcoord
	 * @param ycoord
	 */
	private void drawPoint(int xcoord, int ycoord)
	{
		Cluster cluster = new Circle();
		while (cluster.hasNext())
		{
			ClusterPoint point = cluster.next();
			img.setRGB(point.getX() + xcoord, point.getY() + ycoord, brushColour);
		}
	}
	
	/**
	 * Draws a Cluster at the origin
	 * @param cluster to draw
	 */
	public void drawCentredCluster(Cluster cluster)
	{
		while (cluster.hasNext())
		{
			ClusterPoint point = cluster.next();
			drawPoint(point.getX() + xOrigin, point.getY() + yOrigin);
//			img.setRGB(point.getX() + xOrigin, point.getY() + yOrigin, brushColour);
		}
	}

	/**
	 * Returns a single integer from 4 components
	 * @param alpha
	 * @param red
	 * @param green
	 * @param blue
	 * @return argb
	 */
	public static int getARGBValue(int alpha, int red, int green, int blue)
	{
		alpha = alpha << 24;
		red = red << 16;
		green = green << 8;
		return alpha + red + green + blue;
	}
	
	/**
	 * Converts an argb value into an rgba value
	 * @param argb
	 * @return rgba
	 */
	public static int[] getARGBarray(Integer argb)
	{
		String argbString = Integer.toBinaryString(argb);
		String str = "";
		if (argbString.length() < 32)
		{
			int n = 32 - argbString.length();
			str = "0";
			for (int i = 0; i < n-1; i++ )
			{
				str += "0";
			}
		}
		str += argbString;
		int a = Integer.parseInt(str.substring(0, 8),2);
		int r = Integer.parseInt(str.substring(8, 16),2);
		int g = Integer.parseInt(str.substring(16, 24),2);
		int b = Integer.parseInt(str.substring(24, 32),2);
		int[] array = {a,r,g,b};
		return array;
	}

	/**
	 * Returns the BufferedImage
	 * @return BufferedImage
	 */
	public BufferedImage getImg() {
		return img;
	}

	/**
	 * Returns Width of the BufferedImage
	 * @return xLength
	 */
	public int getxLength() {
		return xLength;
	}

	/**
	 * Returns Height of the BufferedImage
	 * @return yLength
	 */
	public int getyLength() {
		return yLength;
	}

	/**
	 * Returns a s scaled instance of the BufferedImage
	 * @param x - width
	 * @param y - height
	 * @return Image
	 */
	public Image getScaledImage(int x, int y)
	{
		return img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
	}
}

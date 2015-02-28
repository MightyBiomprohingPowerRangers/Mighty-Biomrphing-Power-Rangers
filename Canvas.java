package MBPR;

import java.awt.image.BufferedImage;

public class Canvas
{
	private BufferedImage img;
	private int xOrigin = 250;
	private int yOrigin = 250;
	private int brushColour;

	public Canvas() 
	{
		img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
		setBackground(200,200,0,50);
		brushColour = getARGBValue(200, 0, 200, 200);
		drawPoint(xOrigin,yOrigin);
		brushColour = getARGBValue(200, 0, 0, 200);
	}

	public void setOrigin(int x, int y)
	{
		xOrigin = x;
		yOrigin = y;
	}
	
	public void setBackground(int a, int r, int g, int b)
	{
		for (int x = 0; x < img.getWidth(); x++)
			for (int y = 0; y < img.getHeight(); y++)
			{
				img.setRGB(x, y, getARGBValue(a, r, g, b));
			}
	}

	public void drawPoint(int xcoord, int ycoord)
	{
		Cluster cluster = new Circle(); //currently how to change the shape of the brush
		while (cluster.hasNext())
		{
			ClusterPoint point = cluster.next();
			img.setRGB(point.getX() + xcoord, point.getY() + ycoord, brushColour);
		}
	}

	private int getARGBValue(int alpha, int red, int green, int blue)
	{
		alpha = alpha << 24;
		red = red << 16;
		green = green << 8;
		return alpha + red + green + blue;
	}

	public BufferedImage getImg() {
		return img;
	}

	public int getxOrigin() {
		return xOrigin;
	}

	public int getyOrigin() {
		return yOrigin;
	}
}

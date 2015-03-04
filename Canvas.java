package MBPR;

import java.awt.image.BufferedImage;

public class Canvas
{
	private BufferedImage img;
	private int xLength = 500;
	private int yLength = 500;
	private int xOrigin;
	private int yOrigin;
	private int brushColour;
	private int bgColour;

	public Canvas() 
	{
		xOrigin = xLength/2;
		yOrigin = yLength/2;
		img = new BufferedImage(xLength, yLength, BufferedImage.TYPE_INT_ARGB);
		bgColour = getARGBValue(200, 200, 0, 50);
		drawBackground(bgColour);
		brushColour = getARGBValue(200, 0, 0, 200);
//		drawOutline();
		drawPoint(xOrigin,yOrigin);
//		brushColour = getARGBValue(200, 0, 0, 200);
	}

	public int getBgColour() {
		return bgColour;
	}

	public void setOrigin(int x, int y)
	{
		xOrigin = x;
		yOrigin = y;
	}
	
	public void drawOutline()
	{
		for (int x = 6; x < xLength-5; x++)
		{
			drawPoint(x, 6);
			drawPoint(x, yLength-6);
		}
		for (int y = 6; y < xLength-5; y++)
		{
			drawPoint(6, y);
			drawPoint(xLength-6, y);
		}
	}
	
	public void drawBackground(int rgba)
	{
		for (int x = 0; x < img.getWidth(); x++)
			for (int y = 0; y < img.getHeight(); y++)
			{
				img.setRGB(x, y, rgba);
			}
	}

	public void setBrushColour(int brushColour) {
		this.brushColour = brushColour;
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
	
	public void drawCentredCluster(Cluster cluster)
	{
		while (cluster.hasNext())
		{
			ClusterPoint point = cluster.next();
			drawPoint(point.getX() + xOrigin, point.getY() + yOrigin);
//			img.setRGB(point.getX() + xOrigin, point.getY() + yOrigin, brushColour);
		}
	}

	public static int getARGBValue(int alpha, int red, int green, int blue)
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

	public int getxLength() {
		return xLength;
	}

	public int getyLength() {
		return yLength;
	}
}

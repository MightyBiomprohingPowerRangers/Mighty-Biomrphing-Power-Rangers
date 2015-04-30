package MBPR;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Canvas
{
	private BufferedImage img;
	private int xLength;
	private int yLength;
	private int xOrigin;
	private int yOrigin;
	private int brushColour;
	private int bgColour;

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

	private void setOrigin(int x, int y)
	{
		xOrigin = x;
		yOrigin = y;
	}
	
	private void drawOutline()
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
	
	private void drawBackground(int rgba)
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

	private void drawPoint(int xcoord, int ycoord)
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
	
	public static int[] getARGBarray(Integer argb)
	{
		String argbString = Integer.toBinaryString(argb);
		int a = Integer.parseInt(argbString.substring(0, 8),2);
		int r = Integer.parseInt(argbString.substring(8, 16),2);
		int g = Integer.parseInt(argbString.substring(16, 24),2);
		int b = Integer.parseInt(argbString.substring(24, 32),2);
		int[] array = {a,r,g,b};
		return array;
	}

	public BufferedImage getImg() {
		return img;
	}

	public int getxLength() {
		return xLength;
	}

	public int getyLength() {
		return yLength;
	}

	public Image getScaledImage(int x, int y)
	{
		return img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
	}
}

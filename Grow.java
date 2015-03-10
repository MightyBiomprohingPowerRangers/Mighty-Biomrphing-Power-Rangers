package MBPR;

import java.util.Date;
import java.util.Random;

public class Grow
{
	private Canvas canvas;
	private int rgb = Canvas.getARGBValue(200, 0, 0, 200);
	private int complexity = 5;
	private int length = 5;
	private int height = 5;
	private int width = 5;
	private long initialSeed = 5;
	Random r;

	public Grow() 
	{
		generate();
		r = new Random(initialSeed);
	}
	
	public void generate()
	{
		long startTime = (new Date()).getTime();
		canvas = new Canvas();
		for (int i = 0; i < complexity; i++)
		{
			grow();
			if (getRGB() == Canvas.getARGBValue(200, 0, 0, 100))
			{
				canvas.setBrushColour(Canvas.getARGBValue(200, 0, 0, 200));
				setRGB(Canvas.getARGBValue(201, 0, 0, 200));
				grow();
			}
			else if (getRGB() == Canvas.getARGBValue(200, 0, 0, 200))
			{
				canvas.setBrushColour(Canvas.getARGBValue(200, 0, 0, 100));
				setRGB(Canvas.getARGBValue(200, 0, 0, 100));
				grow();
			}
			else if (getRGB() == Canvas.getARGBValue(201, 0, 0, 200))
			{
				canvas.setBrushColour(Canvas.getARGBValue(200, 0, 0, 200));
				setRGB(Canvas.getARGBValue(200, 0, 0, 200));
				grow();
			}
		}
		long endTime = (new Date()).getTime();
		long elapsedTime = endTime - startTime;
//		System.out.println("(" + String.format("%.3f", elapsedTime / 1000.0) + "s) ");
	}

	public Canvas getCanvas() {
		return canvas;
	}

	private int getRGB() {
		return rgb;
	}

	private void setRGB(int rgb) {
		this.rgb = rgb;
	}
	
	public void setComplexity(int complexity) 
	{
		this.complexity = complexity;
	}
	
	public void setLength(int length) 
	{
		this.length = length;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}

	public void setWidth(int width) 
	{
		this.width = width;
	}
	
	public void setSeed(long seed)
	{
		r.setSeed(seed);
	}
	
	private void grow()
	{
		try {
			canvas.drawCentredCluster(new XMirror(new FullyRandom(500,500,length, height, width, r.nextLong())));
		} catch (NullPointerException name) {

		}
//		canvas.drawCentredCluster(new XMirror(new FullyRandom(500,500,length, height, width, r.nextLong())));
	}
	
	public static void pause(int ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

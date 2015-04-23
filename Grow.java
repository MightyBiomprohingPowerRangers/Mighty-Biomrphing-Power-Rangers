package MBPR;

import java.util.Date;
import java.util.Random;

public class Grow
{
	private Canvas canvas;
	private int rgb1 = Canvas.getARGBValue(200, 0, 0, 200);
	private int rgb2 = Canvas.getARGBValue(200, 0, 0, 100);

	private int complexity = 10;
	private int length = 5;
	private int height = 5;
	private int width = 5;
	private long seed = 5;
	private double angle = 1.5;
	private double minAngle = 0.1;
	private double maxAngle = 1.2;
	private int mutateCounter = 0;
	private int[] currentGenes = {complexity, length, height, width, (int) seed, rgb1, rgb2};
	private int[] newGenes;
	Random r;

	public Grow() 
	{
		r = new Random(seed);
		generate();
		
	}

	public void generate()
	{
		long startTime = (new Date()).getTime();
		canvas = new Canvas(1000, 1000);
		for (int i = 0; i < complexity; i++)
		{
			if (i%2 == 0)
			{
				canvas.setBrushColour(rgb1);
				grow();
			}
			else
			{
				canvas.setBrushColour(rgb2);
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

//	private int getRGB() {
//		return rgb;
//	}
//
//	private void setRGB(int rgb) {
//		this.rgb = rgb;
//	}

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
		this.angle = width;
	}

	public void setSeed(long seed)
	{
		r.setSeed(seed);
	}

	public void setGenes(int[] genes)
	{
		complexity = genes[0];
		length = genes[1];
		height = genes[2];
		width = genes[3];
		seed = genes[4];
		rgb1 = genes[5];
		rgb2 = genes[6];
	}

	public void setCurrentGenes(int[] currentGenes) {
		this.currentGenes = currentGenes;
	}

	private void grow()
	{
		try {
			//			canvas.drawCentredCluster(new XMirror(new Unique(canvas.getxLength(),canvas.getyLength(),length, height, angle, minAngle, maxAngle, r.nextLong())));
			canvas.drawCentredCluster(new XMirror(new Unique(canvas.getxLength(),canvas.getyLength(),length, height, width, r.nextLong())));
		} catch (NullPointerException name) {

		}
		//		canvas.drawCentredCluster(new XMirror(new Unique(500,500,length, height, width, r.nextLong())));
	}

	public void mutate()
	{
		Random r = new Random();
		int rand = r.nextInt(2)*2 - 1;
		if (mutateCounter == 0)
		{
			complexity += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 1)
		{
			length += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 2)
		{
			height += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 3)
		{
			width += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 4)
		{
			seed += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 5)
		{
			if (rand == -1)
			{
				rgb1 = Canvas.getARGBValue(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
			}
			else if (rand == 1)
			{
				rgb2 = Canvas.getARGBValue(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
			}
			mutateCounter++;
		}
		else if (mutateCounter == 6)
		{
			mutateCounter++;
		}
		else if (mutateCounter == 7)
		{
			mutateCounter = 0;
		}
		
		int[] changedGenes = {complexity, length, height, width, (int) seed, rgb1, rgb2};
		newGenes = changedGenes;
		generate();
		setGenes(currentGenes);
	}

	public int[] getNewGenes() {
		return newGenes;
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

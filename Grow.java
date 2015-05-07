package MBPR;

import java.util.Date;
import java.util.Random;

/**
 * Handles any changes to the images and the gene system
 * @author MBPR
 *
 */
public class Grow
{
	private Canvas canvas;
	private int canvasX;
	private int canvasY;

	/**
	 * gene values
	 */
	private int complexity;
	private int length;
	private int height;
	private int width;
	private long seed;
	private int rgb1;
	private int rgb2;

	private int mutateCounter = 0;
	private int[] initialGenes = {10, 5, 5, 5, (int) 5, Canvas.getARGBValue(200, 0, 0, 200), Canvas.getARGBValue(200, 0, 0, 100)};
	private int[] currentGenes;
	private int[] newGenes;
	private Random r;

	public Grow() 
	{
		setGenes(initialGenes);
		currentGenes = initialGenes;
		canvasX = 1000;
		canvasY = 1000;
		r = new Random(seed);
		generate();
	}
	
	public Grow(int[] tempGenes) 
	{
		setGenes(tempGenes);
		currentGenes = tempGenes;
		canvasX = 1000;
		canvasY = 1000;
		r = new Random(seed);
		generate();
		
	}

	/**
	 * Draws an image based on the current genes
	 */
	public void generate()
	{
		canvas = new Canvas(canvasX, canvasY);
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
	}

	/**
	 * returns the current genes
	 * @return int[] genes
	 */
	public int[] getCurrentGenes() {
		return currentGenes;
	}

	/**
	 * returns the canvas
	 * @return canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * sets the complexity gene
	 * @param complexity
	 */
	public void setComplexity(int complexity) 
	{
		this.complexity = complexity;
	}

	/**
	 * sets the length gene
	 * @param length
	 */
	public void setLength(int length) 
	{
		this.length = length;
	}

	/**
	 * sets the height gene
	 * @param height
	 */
	public void setHeight(int height) 
	{
		this.height = height;
	}

	/**
	 * sets the width gene
	 * @param width
	 */
	public void setWidth(int width) 
	{
		this.width = width;
	}

	/**
	 * sets the seed gene
	 * @param seed
	 */
	public void setSeed(long seed)
	{
		r.setSeed(seed);
		this.seed = seed;
		int[] changedGenes = {complexity, length, height, width, (int) this.seed, rgb1, rgb2};
		setCurrentGenes(changedGenes);
	}

	/**
	 * sets all the genes
	 * @param genes
	 */
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

	/** 
	 * sets the current genes
	 * @param currentGenes
	 */
	public void setCurrentGenes(int[] currentGenes) {
		this.currentGenes = currentGenes;
	}

	/**
	 * draws a new image based on the current genes
	 */
	private void grow()
	{
		try {
			canvas.drawCentredCluster(new XMirror(new Unique(canvas.getxLength(),canvas.getyLength(),length, height, width, r.nextLong())));
		} catch (NullPointerException name) {}
	}

	/**
	 * presents a set of children which are variations of the parent
	 */
	public void mutate()
	{
		setSeed(currentGenes[4]);
		setGenes(currentGenes);
		Random r = new Random();
		int rand = r.nextInt(2)*2 - 1;
		if (mutateCounter == 0)
		{
			if (complexity == 1)
				complexity += 1;
			else if (complexity == 20)
				complexity -= 1;
			else
				complexity += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 1)
		{
			if (length == 1)
				length += 1;
			else if (length == 10)
				length -= 1;
			else
				length += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 2)
		{
			if (height == 2)
				height += 1;
			else if (height == 8)
				height -= 1;
			else
				height += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 3)
		{
			if (complexity == 1)
				complexity += 1;
			else
				complexity += rand;
			rgb1 = Canvas.getARGBValue(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
			mutateCounter++;
		}
		else if (mutateCounter == 4)
		{
			seed += rand;
			setSeed(seed);
			mutateCounter++;
		}
		else if (mutateCounter == 5)
		{
			if (length == 1)
				length += 1;
			else if (length == 10)
				length -= 1;
			else
				length += rand;
			rgb2 = Canvas.getARGBValue(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
			mutateCounter++;
		}
		else if (mutateCounter == 6)
		{
			if (width == 2)
				width += 1;
			else if (width == 8)
				width -= 1;
			else
				width += rand;
			mutateCounter++;
		}
		else if (mutateCounter == 7)
		{
			if (rand == -1)
			{
				rgb1 = Canvas.getARGBValue(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
			}
			else if (rand == 1)
			{
				rgb2 = Canvas.getARGBValue(r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256));
			}
			mutateCounter = 0;
		}
		
		int[] changedGenes = {complexity, length, height, width, (int) seed, rgb1, rgb2};
		newGenes = changedGenes;
		generate();
		setGenes(currentGenes);
	}

	/**
	 * returns a string containing gene information
	 * @return String - gene
	 */
	public String getCurrentGenesString() {
		String geneString = "";
		for (int gene:currentGenes)
		{
			geneString += gene + ", ";
		}
		return "Genes: " + geneString;
	}

	/**
	 * returns new genes
	 * @return int[] genes
	 */
	public int[] getNewGenes() {
		return newGenes;
	}
	
	/**
	 * Returns the image x size
	 * @return int
	 */
	public int getCanvasX() {
		return canvasX;
	}

	/**
	 * returns the image y size
	 * @return int
	 */
	public int getCanvasY() {
		return canvasY;
	}
	
	/**
	 * Sets the first colour
	 * @param rgb1
	 */
	public void setRgb1(int rgb1) {
		this.rgb1 = rgb1;
		int[] changedGenes = {complexity, length, height, width, (int) this.seed, rgb1, rgb2};
		setCurrentGenes(changedGenes);
	}

	/**
	 * sets the second colour
	 * @param rgb2
	 */
	public void setRgb2(int rgb2) {
		this.rgb2 = rgb2;
		int[] changedGenes = {complexity, length, height, width, (int) this.seed, rgb1, rgb2};
		setCurrentGenes(changedGenes);
	}
	
	/**
	 * returns the initial genes
	 * @return int[]
	 */
	public int[] getInitialGenes() {
		return initialGenes;
	}

}

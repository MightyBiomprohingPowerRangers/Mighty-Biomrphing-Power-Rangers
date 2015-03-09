package MBPR;

import java.util.Date;

public class Grow
{
	private Canvas canvas;
	private int rgb = Canvas.getARGBValue(200, 0, 0, 200);

	public Grow() 
	{
		generate();
	}
	
	public void generate()
	{
		long startTime = (new Date()).getTime();
		canvas = new Canvas();
		for (int i = 0; i < 15; i++)
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
	
	private void grow()
	{
		canvas.drawCentredCluster(new XMirror(new FullyRandom(500,500)));
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

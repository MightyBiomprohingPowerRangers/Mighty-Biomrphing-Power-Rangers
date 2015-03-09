package MBPR;

import java.util.Random;

public class FullyRandom extends Unique {

	public FullyRandom(int maxX, int maxY, int length) {
		super(maxX, maxY);
		Random r = new Random();
		int x = 0;
		int y = 0;
		for (int i = 1; i < length + 1; i++)
		{
			if (x > -(maxX/2 - 10) && x < (maxX/2 - 10) && y > -(maxY/2 - 10) && y < (maxY/2 - 10))
			{
				x+= r.nextInt(13) - 6;
				y+= r.nextInt(13) - 6;
			}
			points.add(new ClusterPoint(x, y));
		}
	}

}

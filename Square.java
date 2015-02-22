package MBPR;

public class Square extends Cluster 
{
	public Square() 
	{
		for (int xcoord = -5; xcoord < 6; xcoord++ )
			for (int ycoord = -5; ycoord < 6; ycoord++)
				points.add(new ClusterPoint(xcoord, ycoord));
	}

}

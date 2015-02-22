package MBPR;

public class Circle extends Cluster 
{
	public Circle() 
	{
		for (int xcoord = -5; xcoord < 6; xcoord++ )
		{
			if (xcoord == -5 || xcoord == 5)
			{
				for (int ycoord = -1; ycoord < 2; ycoord++ )
				points.add(new ClusterPoint(xcoord, ycoord));
			}
			else if (xcoord == -4 || xcoord == 4)
			{
				for (int ycoord = -3 ; ycoord < 4; ycoord++ )
				points.add(new ClusterPoint(xcoord, ycoord));
			}
			else if (xcoord == -3 || xcoord == 3 || xcoord == -2 || xcoord == 2)
			{
				for (int ycoord = -4 ; ycoord < 5; ycoord++ )
				points.add(new ClusterPoint(xcoord, ycoord));
			}
			else if (xcoord == -2 || xcoord == 2 || xcoord == -1 || xcoord == 1 || xcoord == 0)
			{
				for (int ycoord = -5 ; ycoord < 6; ycoord++ )
				points.add(new ClusterPoint(xcoord, ycoord));
			}
		}
	}
}

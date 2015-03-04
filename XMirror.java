package MBPR;

public class XMirror extends Cluster {

	public XMirror(Unique unique) {
		while (unique.hasNext())
		{
			ClusterPoint point = unique.next();
			if (point.getX() > -(unique.maxX/2 - 10) && point.getX() < (unique.maxX/2 - 10)
					&& point.getY() > -(unique.maxY/2 - 10) && point.getY() < (unique.maxY/2 - 10))
			{
				points.add(new ClusterPoint(point.getX(), point.getY()));
				points.add(new ClusterPoint(-(point.getX()), point.getY()));
			}
		}
	}

}

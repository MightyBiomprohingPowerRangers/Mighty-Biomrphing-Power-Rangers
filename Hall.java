package MBPR;

import java.util.ArrayList;

public class Hall {

	private static ArrayList<int[]> hof;
	
	public Hall() {
		hof = new ArrayList<int[]>();
	}
	
	public static void setHof(ArrayList<int[]> list)
	{
		hof = list;
	}
	
	public static ArrayList<int[]> getHof()
	{
		return hof;
	}
	
	public static void addToHof(int[] gene)
	{
		
	}

}

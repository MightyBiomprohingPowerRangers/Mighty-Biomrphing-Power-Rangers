package MBPR;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Store 
{

	public Store(ArrayList<int[]> genes) throws IOException
	{
		File f = new File("hof.txt");
		if (!f.exists())
		{
			f.createNewFile();
		}
		PrintWriter writer;
		writer = new PrintWriter("hof.txt", "UTF-8");
		for (int[] gene:genes)
		{
			String temp = "b" + gene[0] + "a" + gene[1] + "a" + gene[2] + "a" + gene[3] + "a" + gene[4] + "a" + gene[5] + "a" + gene[6] + "b";
			writer.println(temp);
		}
		writer.close();
	}
}

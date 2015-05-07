package MBPR;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Handles saving of images to hall of fame
 * @author MBPR
 *
 */
public class Store 
{

	public Store(ArrayList<int[]> genes) throws IOException
	{
		File f = new File("hof.txt");
		if (!f.exists())
		{
			f.createNewFile();
		}
		if (genes.size() < 11)
		{
			PrintWriter writer;
			writer = new PrintWriter("hof.txt", "UTF-8");
			for (int i = 0; i < genes.size(); i++)
			{
				int[] gene = genes.get(i);
				String temp = "b" + gene[0] + "a" + gene[1] + "a" + gene[2] + "a" + gene[3] + "a" + gene[4] + "a" + gene[5] + "a" + gene[6] + "b";
				writer.println(temp);
			}	
			writer.close();
		}

	}
}

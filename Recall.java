package MBPR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recall {

	private ArrayList<int[]> list;

	public Recall() throws IOException {
		list = new ArrayList<int[]>();
		File f = new File("hof.txt");
		if (!f.exists())
		{
			f.createNewFile();
		}
		BufferedReader r = new BufferedReader(new FileReader(f));
		boolean finished = false;
		while (finished == false)
		{
			String line = r.readLine();
			if (line != null)
			{
				line.trim();
				if (line.startsWith("b") && line.endsWith("b"))
				{
					String[] strings = line.split("[ab]");
					int[] ints = {Integer.parseInt(strings[1]),Integer.parseInt(strings[2]),Integer.parseInt(strings[3]),Integer.parseInt(strings[4]),
							Integer.parseInt(strings[5]),Integer.parseInt(strings[6]),Integer.parseInt(strings[7])};
					list.add(ints);
				}
			}
			else 
				finished = true;
		}
		r.close();
	}

	public ArrayList<int[]> getList() 
	{
//		for (int i = list.size())
		return list;
	}
}
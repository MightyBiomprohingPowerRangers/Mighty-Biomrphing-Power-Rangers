package MBPR;

import java.io.IOException;
import java.util.ArrayList;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
//		int[] array = Canvas.getARGBarray(Canvas.getARGBValue(200, 4, 5, 100));
//		System.out.println(array[0]);
//		System.out.println(array[1]);
//		System.out.println(array[2]);
//		System.out.println(array[3]);
		ArrayList<int[]> a = new ArrayList<int[]>();
		a.add(new int[] {10, 5, 5, 5, (int) 5, Canvas.getARGBValue(200, 0, 0, 200), Canvas.getARGBValue(200, 0, 0, 100)});
		a.add(new int[] {8, 5, 5, 5, (int) 5, Canvas.getARGBValue(200, 0, 0, 200), Canvas.getARGBValue(200, 0, 0, 100)});
		a.add(new int[] {10, 5, 5, 5, (int) 5, Canvas.getARGBValue(200, 0, 0, 200), Canvas.getARGBValue(200, 0, 0, 100)});
		Store s = new Store(a);
		Recall r = new Recall();
	}
}

package applicationDAG;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

/*
 * Comments:
 * Type of problem longest path problem on
 *  directed acyclic graph(DAG).
 * Solution type:Depth first search + Dynamic programming (memoization)
 * 
 */
public class DirectedAcyclicGraph {
	@Value("${path}")
	String path;
	static int peak[][] = new int[101][101];
	static int dpArray[][] = new int[101][101];
	static int R;
	static int C;

	public static int longesRunStartingAt(int x, int y, int height_of_last_visited_peak) {

		if (x < 0 || y < 0 || x >= R || y >= C)
			return 0;
		if (height_of_last_visited_peak <= peak[x][y])
			return 0;
		if (dpArray[x][y] == -1) {
			int longest = 1;
			longest = Math.max(longest, 1 + longesRunStartingAt(x - 1, y, peak[x][y]));
			longest = Math.max(longest, 1 + longesRunStartingAt(x, y - 1, peak[x][y]));
			longest = Math.max(longest, 1 + longesRunStartingAt(x + 1, y, peak[x][y]));
			longest = Math.max(longest, 1 + longesRunStartingAt(x, y + 1, peak[x][y]));
			dpArray[x][y] = longest;
		}
		return dpArray[x][y];
	}

	public static void main(String args[]) throws Exception {
		BufferedReader reader = new DirectedAcyclicGraph().readFile();
		String line = null;
		line = reader.readLine();
		int N = Integer.parseInt(line);

		for (int i = 0; i < N; i++) {
			int longest = 0;
			for (int[] row : dpArray)
				Arrays.fill(row, -1);

			String s = reader.readLine();
			String str[] = s.split(" ");
			String countryName = str[0];
			R = Integer.parseInt(str[1]);
			C = Integer.parseInt(str[2].trim());

			for (int r = 0; r < R; r++) {
				String s1 = reader.readLine();
				String str2[] = s1.split(" ");
				for (int c = 0; c < C; c++) {
					peak[r][c] = Integer.parseInt(str2[c]);

				}
			}
//			for (int r = 0; r < R; r++)
//				for (int c = 0; c < C; c++)
//					longest = Math.max(longest, longesRunStartingAt(r, c, Integer.MAX_VALUE));
//			System.out.println(countryName + ": " + longest);
		}
		reader.close();

	}

	private BufferedReader readFile() throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(new DirectedAcyclicGraph().path));
		return reader;
	}
}

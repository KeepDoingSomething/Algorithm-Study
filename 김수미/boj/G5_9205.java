/**
 * Author    : Kim Su Mi
 * Date      : 2024.09.26(Tue)
 * Runtime   : 128 ms
 * Memory    : 14928 KB
 * Algorithm : BFS
 */

package 김수미.boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_9205 {

	static int t, n, homeX, homeY, festivalX, festivalY;
	static List<int[]> convenienceStores;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		t = Integer.parseInt(br.readLine());

		for (int i = 1; i <= t; i++) {
			n = Integer.parseInt(br.readLine());
			convenienceStores = new ArrayList<>();

			st = new StringTokenizer(br.readLine());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());

			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				convenienceStores.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}

			st = new StringTokenizer(br.readLine());
			festivalX = Integer.parseInt(st.nextToken());
			festivalY = Integer.parseInt(st.nextToken());

			visited = new int[n];
			Arrays.fill(visited, 0);

			bw.write(bfs() ? "happy\n" : "sad\n");
		}

		// bw flush, close 해줘야함.
		bw.flush();
		bw.close();
	}

	static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{homeX, homeY});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0], y = cur[1];
			if (Math.abs(festivalX - x) + Math.abs(festivalY - y) <= 1000) {
				return true;
			}

			for (int i=0; i<n ; i++){
				if (visited[i] == 0) {
					int newX = convenienceStores.get(i)[0], newY = convenienceStores.get(i)[1];
					if (Math.abs(newX - x) + Math.abs(newY - y) <= 1000) {
						visited[i] = 1;
						q.add(new int[]{newX, newY});
					}
				}
			}
		}
		return false;
	}

}

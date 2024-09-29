package 김수미.boj;

/**
 * Author    : Kim Su Mi
 * Date      : 2024.09.28(SAT)
 * Runtime   : 476 ms
 * Memory    : 58456 KB
 * Algorithm : Floyd-Warshall
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_1956 {

	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		int[][] graph = new int[v+1][v+1];
		for (int[] row : graph) Arrays.fill(row, INF);

		while (e-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a][b] = c;
		}

		for(int i=1; i<v+1; i++){
			for(int a=1; a<v+1; a++){
				for(int b=1; b<v+1; b++){
					graph[a][b] = Math.min(graph[a][b], graph[a][i] + graph[i][b]);
				}
			}
		}

		int result = INF;
		for(int i=1; i<v+1; i++){
			result = Math.min(result, graph[i][i]);
		}

		System.out.println(result == INF ? -1 : result);

	}
}

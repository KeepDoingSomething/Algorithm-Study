/**
 * Author    : Lee In Bok
 * Date      : 2024.06.20(Thu)
 * Runtime   : 17404 KB
 * Memory    : 188 ms
 * Algorithm : Graph
 */

package com.Week06.S2_1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class S2_1012 {

    public static final int[] dx = {0, 1, 0, -1};
    public static final int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            Set<Point> bugLocation = new HashSet<>();

            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            for(int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                bugLocation.add(new Point(x, y));
            }  // Input End

            int ans = 0;

            while(!bugLocation.isEmpty()) {  // 벌래가 존재하는 밭이 남아있는가?
                Point p = bugLocation.iterator().next();
                Queue<Point> queue = new LinkedList<>();
                ans++;

                queue.add(p);

                while(!queue.isEmpty()){  // 벌래가 살고있는 밭 기준으로 탐색
                    Point curP = queue.poll();
                    bugLocation.remove(curP);

                    for(int j = 0; j < 4; j++) {
                        int nextX = curP.x + dx[j];
                        int nextY = curP.y + dy[j];
                        Point nextP = new Point(nextX, nextY);

                        if(isValid(M, N, nextX, nextY) && bugLocation.contains(nextP)) {
                            bugLocation.remove(nextP);
                            queue.add(nextP);
                        }
                    }
                }
            }

            System.out.println(ans);
        }
    }

    public static boolean isValid(int M, int N, int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;

            return this.x == p.x && this.y == p.y;
        }
    }
}
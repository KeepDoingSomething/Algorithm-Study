/**
 * Author    : Lee In Bok
 * Date      : 2024.06.05(Wed)
 * Runtime   : 15700 KB
 * Memory    : 152 ms
 * Algorithm : Math
 */

package com.Week04.S3_1004;

import com.self.Solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_1004 implements Solution {

    @Override
    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            int ans = 0;
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(br.readLine());  // 행성의 개수

            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int powRadius = (int) Math.pow(r, 2);

                int dist1 = (int) Math.pow(x1 - cx, 2) + (int) Math.pow(y1 - cy, 2);
                int dist2 = (int) Math.pow(x2 - cx, 2) + (int) Math.pow(y2 - cy, 2);

                if((dist1 < powRadius && dist2 > powRadius)
                || (dist1 > powRadius && dist2 < powRadius)) {
                    ans++;
                }
            }

            System.out.println(ans);
        }
    }
}

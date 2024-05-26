package com.Week01.S3_1913;

import com.Solution;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q1913 implements Solution {

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    @Override
    public void solution() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int target = Integer.parseInt(br.readLine());

        boolean end = true;
        int x = N / 2;
        int y = N / 2;
        int num = 1;  // 좌표에 위치하는 자연수(매 이동마다 1증가)
        int dist = 1;  // 이동 해야하는 거리 (2번 이동하면 1증가)
        int changeDirection = 0;
        int direction = 0;  // 방향
        int ansX = 0;
        int ansY = 0;

        while(end) {
            int movedDist = 0;  // 이동한 거리

            while(movedDist != dist) {
                if(num == target) {  // 찾고자 하는 자연수 찾은 케이스
                    ansX = x + 1;
                    ansY = y + 1;
                }

                if(0 > x || x >= N || 0 > y || y >= N) {  // 배열 인덱스 넘어가면 종료
                    end = false;
                    break;
                }

                map[x][y] = num++;
                x += dx[direction];
                y += dy[direction];
                movedDist++;

                if(movedDist == dist) {  // 해당 방향으로 정해진 거리만큼 이동한 경우
                    direction = direction == 3 ? 0 : direction + 1;  // 방향 전환
                    changeDirection++;  // 방향 전환 회수 증가

                    if(changeDirection % 2 == 0) {  // 방향 전환 2번 했다면 이동 거리 증가
                        dist++;
                    }
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int[] mx : map) {
            for(int my : mx) {
                sb.append(my + " ");
            }
            sb.deleteCharAt(sb.length() - 1).append("\n");
        }
        sb.append(ansX + " " + ansY);
        System.out.println(sb);
    }
}
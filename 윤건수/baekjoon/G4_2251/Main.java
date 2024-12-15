package baekjoon.G4_2251;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static boolean[] result;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] bottleSize = br.readLine().split(" ");

        int bottleSize1 = Integer.parseInt(bottleSize[0]);
        int bottleSize2 = Integer.parseInt(bottleSize[1]);
        int bottleSize3 = Integer.parseInt(bottleSize[2]);

        result = new boolean[bottleSize3 + 1];
        visited = new boolean[bottleSize3 + 1][bottleSize3 + 1];
        visited[0][0] = true;

        Bottle bottle1 = new Bottle(bottleSize1, 0);
        Bottle bottle2 = new Bottle(bottleSize2, 0);
        Bottle bottle3 = new Bottle(bottleSize3, bottleSize3);
        result[bottleSize3] = true;

        bfs(bottle1, bottle2, bottle3);

        for (int i = 0; i < result.length; i++) {
            if (result[i])
                bw.write(i + " ");
        }

        bw.flush();
        bw.close();
        br.close();

    }

    public static void bfs(Bottle bottle1, Bottle bottle2, Bottle bottle3){

        Queue<Bottle[]> que = new LinkedList<>();
        Bottle[] bottle = new Bottle[4];
        bottle[1] = bottle1;
        bottle[2] = bottle2;
        bottle[3] = bottle3;

        que.add(bottle);

        while(!que.isEmpty()){

            Bottle[] now = que.poll();
            visited[now[1].water][now[2].water] = true;

            for (int i = 1; i <= 3; i++) {  // i는 주는 물통, j는 받는 물통
                if ((now[i].water) == 0) continue;
                for (int j = 1; j <= 3; j++) {
                    if (i == j) continue;
                    Bottle[] next = {null,
                            new Bottle(now[1].size, now[1].water),
                            new Bottle(now[2].size, now[2].water),
                            new Bottle(now[3].size, now[3].water)
                    };

                    if (now[i].water > now[j].size - now[j].water) { // 주는 물이 받는 물통의 크기보다 많은경우
                        next[j].water = now[j].size;
                        next[i].water = now[i].water - (now[j].size - now[j].water);
                    } else { // 주는 물이 받는 물통의 크기보다 적어 모두 주게 되는경우
                        next[j].water += now[i].water;
                        next[i].water = 0;
                    }

                    if (!visited[next[1].water][next[2].water]) {
                        visited[next[1].water][next[2].water] = true;
                        que.add(next);

                        if (next[1].water == 0) result[next[3].water] = true;
                    }
                }
            }
        }
    }

    private static class Bottle{

        int size;
        int water;

        Bottle(int size, int water){
            this.size = size;
            this.water = water;
        }
    }

}


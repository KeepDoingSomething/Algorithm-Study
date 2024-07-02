package baekjoon.G4_20056;

// 마법사 상어와 파이어볼 (Gold 4)
// https://www.acmicpc.net/problem/20056

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int fireBallNums = Integer.parseInt(st.nextToken());
        int spellNums = Integer.parseInt(st.nextToken());
        ArrayList<Fireball>[][] maps = new ArrayList[n][n];

        String line;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            int velocity = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            maps[row][col] = new ArrayList<>();
            maps[row][col].add(new Fireball(weight, velocity, direction));
        }

        while(spellNums-- > 0){
            maps = castingFireBall(maps);
        }

        int totalWeight = calcTotalWeight(maps);

        System.out.println(totalWeight);
    }

    private static ArrayList<Fireball>[][] castingFireBall(ArrayList<Fireball>[][] maps) {
        ArrayList<Fireball>[][] afterMove = moveFireball(maps);
        ArrayList<Fireball>[][] afterCombine = combineFireball(afterMove);
        return afterCombine;
    }

    private static ArrayList<Fireball>[][] moveFireball(ArrayList<Fireball>[][] maps) {
        ArrayList<Fireball>[][] afterMove = new ArrayList[maps.length][maps.length];
        for(int row = 0; row < maps.length; row++){
            for(int col = 0; col < maps.length; col++){
                if(maps[row][col] != null){
                    ArrayList<Fireball> fireballs = maps[row][col];
                    for (Fireball fireball : fireballs) {
                        int direction = fireball.direction;
                        int nextRow = (row + (dr[direction] * fireball.velocity)) % maps.length;
                        int nextCol = (col + (dc[direction] * fireball.velocity)) % maps.length;

                        nextRow = nextRow < 0 ? maps.length + nextRow : nextRow;
                        nextCol = nextCol < 0 ? maps.length + nextCol : nextCol;

                        if (afterMove[nextRow][nextCol] == null) {
                            afterMove[nextRow][nextCol] = new ArrayList<>();
                        }
                        ArrayList<Fireball> nextPosition = afterMove[nextRow][nextCol];
                        nextPosition.add(fireball);
                    }
                }
            }
        }
        return afterMove;
    }


    private static ArrayList<Fireball>[][] combineFireball(ArrayList<Fireball>[][] maps) {
        for(int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps.length; col++) {
                if (maps[row][col] != null) {
                    ArrayList<Fireball> fireballs = maps[row][col];
                    if(fireballs.size() == 1) continue;

                    int totalWeight = 0;
                    int totalVelocity = 0;
                    int fireballCnt = fireballs.size();
                    Set<Integer> directionCheck = new HashSet<>();

                    for (Fireball fireball : fireballs) {
                        totalWeight += fireball.weight;
                        totalVelocity += fireball.velocity;
                        directionCheck.add(fireball.direction % 2);
                    }

                    int weight = totalWeight / 5;
                    if(weight == 0){
                        maps[row][col] = null;
                        continue;
                    }
                    int velocity = totalVelocity / fireballCnt;
                    int directionInit = directionCheck.size() == 1 ? 0 : 1;

                    ArrayList<Fireball> afterCombine = new ArrayList<>();
                    for(int direction = directionInit; direction < 8; direction = direction + 2){
                        afterCombine.add(new Fireball(weight, velocity, direction));
                    }
                    maps[row][col] = afterCombine;
                }
            }
        }
        return maps;
    }

    private static int calcTotalWeight(ArrayList<Fireball>[][] maps) {
        int totalWeight = 0;
        for(int row = 0; row < maps.length; row++){
            for(int col = 0; col < maps.length; col++){
                if(maps[row][col] != null){
                    for (Fireball fireball : maps[row][col]) {
                        totalWeight += fireball.weight;
                    }
                }
            }
        }

        return totalWeight;
    }

    private static class Fireball{
        int weight;
        int velocity;
        int direction;

        Fireball(int weight, int velocity, int direction){
            this.weight = weight;
            this.velocity = velocity;
            this.direction = direction;
        }
    }

}

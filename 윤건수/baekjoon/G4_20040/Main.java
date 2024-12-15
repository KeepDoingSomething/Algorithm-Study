package baekjoon.G4_20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] gameInfo = br.readLine().split(" ");
        int pointCnt = Integer.parseInt(gameInfo[0]);
        int turn = Integer.parseInt(gameInfo[1]);

        String line;
        parent = new int[pointCnt];
        for(int i = 0; i < pointCnt; i++){
            parent[i] = i;
        }

        int result = 0;
        boolean isCycle = false;
        while((line = br.readLine()) != null){
            result++;
            String[] points = line.split(" ");
            int point1 = Integer.parseInt(points[0]);
            int point2 = Integer.parseInt(points[1]);
            if(union(point1, point2)) {
                isCycle = true;
                break;
            }
        }

        System.out.println(isCycle ? result : 0);
    }

    private static boolean union(int point1, int point2) {
        int parent1 = find(point1);
        int parent2 = find(point2);
        if(parent1 == parent2){
            return true;
        }else{
            if(parent1 <= parent2){
                parent[parent2] = parent1;
            }else{
                parent[parent1] = parent2;
            }

            return false;
        }
    }

    private static int find(int a) {
        if(parent[a] == a) return a;
        else return find(parent[a]);
    }

}

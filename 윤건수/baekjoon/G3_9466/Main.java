package baekjoon.G3_9466;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static boolean[] checked;
    static int result;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0) {
            bw.write(String.valueOf(solution()));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static int solution() throws IOException {
        int studentNum = Integer.parseInt(br.readLine());
        int[] team = new int[studentNum + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= studentNum; i++){
            team[i] = Integer.parseInt(st.nextToken());
        }

        checked = new boolean[studentNum + 1];
        int[] visited = new int[studentNum + 1];
        result = studentNum;
        for(int i = 1; i <= studentNum; i++){
            if(checked[i]) continue;
            findTeam(team, i, 0, visited);
        }

        return result;
    }

    private static void findTeam(int[] team, int student, int seq, int[] visited){
        if(checked[student]) return;
        seq++;
        checked[student] = true;
        visited[student] = seq;

        int next = team[student];
        if(visited[next] != 0){
            result -= (seq - visited[next] + 1);
        }else{
            findTeam(team, next, seq, visited);
        }

        visited[student] = 0;
    }

}
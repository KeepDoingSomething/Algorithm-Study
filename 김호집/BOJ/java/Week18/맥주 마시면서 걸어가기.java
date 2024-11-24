/**
 * Author    : HojipKim
 * Date      : 2024.09.27(Fri)
 * Runtime   : 92 ms
 * Memory    : 12704 KB
 * Algorithm : 너비 우선 탐색, 그래프 이론, 그래프 탐색
 */

import java.io.*;
import java.util.*;

class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Main {

    static int[][] direction = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            // 편의점 갯수 n
            int n = Integer.parseInt(br.readLine());
            List<Node> list = new ArrayList<>();
            isVisited = new boolean[n+2];

            for (int j = 0; j < n+2; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.add(new Node(x,y));
            }

            Node home = list.get(0);
            Node festival = list.get(list.size()-1);

            String answer = "";
            answer = bfs(list, home, festival) ? "happy" : "sad";

            bw.write(answer + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static boolean bfs(List<Node> list, Node home, Node festival){
        Queue<Node> q = new LinkedList<>();
        Node first = home, target = festival;

        int targetX = festival.getX();
        int targetY = festival.getY();

        q.offer(first);
        isVisited[0] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            int x = cur.getX();
            int y = cur.getY();

            if(Math.abs(targetX - x) + Math.abs(targetY - y) <= 1000){
                return true;
            }

            for (int i = 0; i < list.size(); i++) {
                if(!isVisited[i]){
                    Node next = list.get(i);
                    int nextX = next.getX();
                    int nextY = next.getY();
                    if(Math.abs(nextX - x) + Math.abs(nextY - y) <= 1000){
                        isVisited[i] = true;
                        q.offer(next);
                    }
                }
            }
        }
        return false;
    }
}
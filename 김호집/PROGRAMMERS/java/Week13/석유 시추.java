import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution(new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}}));
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


class Solution {

    public static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int[] dimensions = new int[50_000];

    public static int order = 2;

    public static boolean[] isVisitedDim = new boolean[50_000];

    public static int[] rowList = new int[50_000];

    public static List<Node> nodeList;


    public int solution(int[][] land) {
        int answer = 0;

        boolean[][] isVisited = new boolean[land.length][land[0].length];

        for (int i = 0; i < land[0].length; i++) { // x축
            nodeList = new ArrayList<>();
            for (int j = 0; j < land.length; j++) { // y축
                if(land[j][i] != 0 && !isVisited[j][i]){
                    if(land[j][i] > 1 && !isVisitedDim[land[j][i]]){
                        rowList[i] += dimensions[land[j][i]];
                        isVisitedDim[land[j][i]] = true;
                    }else{
                        bfs(land, new Node(i, j), isVisited);
                    }
                }
            }
            Arrays.fill(isVisitedDim, false);

            for (int j = 0; j < nodeList.size(); j++) {
                isVisited[nodeList.get(j).y][nodeList.get(j).x] = false;
            }
//            for (int k = 0; k < isVisited.length; k++) {
//                Arrays.fill(isVisited[k], false);
//            }
        }

        for (int i = 0; i < rowList.length; i++) {
            if(rowList[i] != 0){
                answer = Math.max(answer, rowList[i]);
            }
        }

        return answer;
    }

    public void bfs(int[][] land, Node start, boolean[][] isVisited){
        Queue<Node> q = new LinkedList<>();
        isVisited[start.y][start.x] = true;
        nodeList.add(start);
        if(isVisitedDim[land[start.y][start.x]]){
            return;
        }
        q.offer(start);
        while(!q.isEmpty()){
            Node cur = q.poll();
            rowList[start.x]++;

            dimensions[order] += 1;

            for (int i = 0; i < directions.length; i++) {
                int x = cur.x + directions[i][0];
                int y = cur.y + directions[i][1];

                if(0 <= x && x < land[0].length && 0 <= y && y < land.length && !isVisited[y][x] && land[y][x] != 0){
                    isVisited[y][x] = true;
                    nodeList.add(new Node(x, y));
                    land[y][x] = order;
                    q.offer(new Node(x, y));
                }
            }
        }
        order++;
    }
}
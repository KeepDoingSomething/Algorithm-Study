import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static Queue<int[]>[] que;
    static int size;
    static int count;
    static int func = 0;
    public int solution(int[][] points, int[][] routes) {

        size = routes.length;

        que = new LinkedList[size];

        for(int i = 0; i < size; i++) {
            que[i] = new LinkedList<>();
        }

        calculate(points, routes);
        checkMap();

        return count;

    }
    public void calculate(int points[][], int routes[][]) {

        for(int i =0; i < size; i++) {
            int arr[] = points[routes[i][0]-1];

            int dx = arr[0];
            int dy = arr[1];
            que[i].add(new int[]{dx,dy});

            for(int j = 1; j < routes[i].length; j++) {
                int arr_nx[] = points[routes[i][j]-1];

                int nx = arr_nx[0];
                int ny = arr_nx[1];

                while(dx != nx) {
                    dx += (dx < nx)? 1 : -1;
                    que[i].add(new int[] {dx,dy});
                }

                while(dy != ny) {
                    dy += (dy < ny)? 1 : -1;
                    que[i].add(new int[] {dx,dy});
                }

            }
        }


    }
    public void checkMap() {

        while(func != size) {
            int map[][] = new int[101][101];

            func = 0 ;

            for(int i = 0; i < size; i++) {
                if(que[i].isEmpty()) {
                    func ++;
                    continue;
                }
                int arr[] =que[i].poll();

                int x = arr[0];
                int y = arr[1];

                map[x][y] ++;

            }

            for(int i = 0 ; i <101; i ++) {
                for(int j = 0; j < 101; j++) {
                    if(map[i][j] > 1) count++;
                }
            }


        }


    }


}



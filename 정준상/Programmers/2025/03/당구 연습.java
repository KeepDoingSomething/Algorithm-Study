import java.util.*;

/**
 * Author           :   정준상 (jbw9964)
 * Date             :   2025-03-25
 * Runtime          :   12.37 ms
 * Memory           :   94.3 MB
 * Used algorithm   :   Math, Implementation
 */
class Solution {

    private static int M, N;

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {

        M = m;
        N = n;

        return Arrays.stream(balls)
                .mapToInt(arr -> solve(startX, startY, arr[0], arr[1]))
                .toArray();
    }

    /*
    쿠션 (벽) 위치
                        (쿠션 1)
            y (최대 N)
            ^
            |
            |                           (쿠션 2)
    (쿠션 4) |
            |
            |
            o----------------------->   x (최대 M)
                    (쿠션 3)
    */

    private int solve(int x1, int y1, int x2, int y2) {
        int minima = Integer.MAX_VALUE;

        // 쿠션 1
        if (x1 != x2 || y1 > y2)    {
            minima = Math.min(
                    minima, pythagorean(x1, reflectYN(y1), x2, y2)
            );
        }

        // 쿠션 2
        if (y1 != y2 || x1 > x2)    {
            minima = Math.min(
                    minima, pythagorean(reflectXM(x1), y1, x2, y2)
            );
        }

        // 쿠션 3
        if (x1 != x2 || y1 < y2)    {
            minima = Math.min(
                    minima, pythagorean(x1, reflectY0(y1), x2, y2)
            );
        }

        // 쿠션 4
        if (y1 != y2 || x1 < x2)    {
            minima = Math.min(
                    minima, pythagorean(reflectX0(x1), y1, x2, y2)
            );
        }

        return minima;
    }

    private static int reflectX0(int x)    {
        return -x;
    }
    private static int reflectY0(int y)    {
        return -y;
    }
    private static int reflectXM(int x)    {
        return x + 2 * (M - x);
    }
    private static int reflectYN(int y)    {
        return y + 2 * (N - y);
    }

    private static int pythagorean(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
        return dx * dx + dy * dy;
    }
}

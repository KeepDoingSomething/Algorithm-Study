/**
 * Author    : Lee In Bok
 * Date      : 2025.02.02(Sun)
 * Runtime   : 11.65 ms
 * Memory    : 65.3 MB
 * Algorithm : Dynamic Programming
 */

package com.year2025.Week33;

public class LV3_43105 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                sol.solution(new int[][]{
                        {7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}
                })
        );
    }

    static class Solution {
        public int solution(int[][] triangle) {
            if(triangle.length == 1) {
                return triangle[0][0];
            }

            triangle[1][0] += triangle[0][0];
            triangle[1][1] += triangle[0][0];
            int max = Math.max(triangle[1][0], triangle[1][1]);

            for(int row = 2; row < triangle.length; row++) {
                for(int col = 0; col < triangle[row].length; col++) {
                    if(col == 0) {
                        triangle[row][0] += triangle[row - 1][0];
                        max = Math.max(max, triangle[row][0]);
                        continue;
                    }

                    if(col == triangle[row].length - 1) {
                        triangle[row][col] += triangle[row - 1][col - 1];
                        max = Math.max(max, triangle[row][col]);
                        continue;
                    }

                    triangle[row][col] += Math.max(triangle[row - 1][col - 1], triangle[row - 1][col]);
                    max = Math.max(max, triangle[row][col]);
                }
            }

            for (int[] ints : triangle) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.println();
            }

            return max;
        }
    }
}

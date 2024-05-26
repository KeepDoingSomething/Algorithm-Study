package com.Week03.LV2_81303;

import java.util.Arrays;
import java.util.Stack;

public class Q81303 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(8, 2, new String[]{
                    //"D 2","C","U 3","C","D 4","C","U 2","Z","Z"
                    "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"
            })
        );
    }

    static class Solution {

        public static boolean DELETE = true;
        public static boolean ROLLBACK = false;

        public static int FIRST_ROW = 0;
        public static int CUR_ROW = Integer.MAX_VALUE;
        public static int LAST_ROW = Integer.MAX_VALUE;

        public String solution(int n, int k, String[] cmd) {
            boolean[] table = new boolean[n];  // false: 표 존재, true: 표 삭제
            Stack<Integer> deleted = new Stack<>();
            CUR_ROW = k;
            LAST_ROW = n - 1;

            for(String c : cmd) {
                String[] cs = Arrays.stream(c.split(" ")).toArray(String[]::new);

                if("U".equals(cs[0])) {
                    up(Integer.parseInt(cs[1]), table);
                } else if("D".equals(cs[0])) {
                    down(Integer.parseInt(cs[1]), table);
                } else if("C".equals(cs[0])) {
                    delete(deleted, table);
                } else {
                    rollback(deleted, table);
                }
            }

            StringBuilder sb = new StringBuilder();

            for(boolean row : table) {
                sb.append(row ? "X" : "O");
            }

            return sb.toString();
        }

        public static void rollback(Stack<Integer> deleted, boolean[] table) {
            int rollbackIdx = deleted.pop();

            table[rollbackIdx] = ROLLBACK;

            if(rollbackIdx > LAST_ROW) {
                LAST_ROW = rollbackIdx;
            } else if(rollbackIdx < FIRST_ROW) {
                FIRST_ROW = rollbackIdx;
            }
        }

        public static void delete(Stack<Integer> deleted, boolean[] table) {
            table[CUR_ROW] = DELETE;
            deleted.push(CUR_ROW);

            if(CUR_ROW == LAST_ROW) {
                up(1, table);
                LAST_ROW = CUR_ROW;
            } else {
                boolean firstChange = false;

                if(CUR_ROW == FIRST_ROW) {
                    firstChange = true;
                }

                down(1, table);

                if(firstChange) {
                    FIRST_ROW = CUR_ROW;
                }
            }
        }
        
        public static void down(int dist, boolean[] table) {
            if(CUR_ROW == LAST_ROW) return;

            for(int i = 0; i < dist; i++) {
                while(table[++CUR_ROW]) {}
            }
        }

        public static void up(int dist, boolean[] table) {
            if(CUR_ROW == FIRST_ROW) return;

            for(int i = 0; i < dist; i++) {
                while(table[--CUR_ROW]) {}
            }
        }
    }
}
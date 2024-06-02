package com.Week03.LV3_81303;

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

        public static char DELETE = 'X';
        public static char ROLLBACK = 'O';

        public String solution(int n, int k, String[] cmd) {
            char[] table = new char[n];  // false: 표 존재, true: 표 삭제
            Stack<Integer> deleted = new Stack<>();

            Arrays.fill(table, 'O');
            int moveCnt = 0;

            for(String c : cmd) {
                char op = c.charAt(0);

                if('U' == op) {
                    moveCnt -= Integer.parseInt(c.substring(2));
                } else if('D' == op) {
                    moveCnt += Integer.parseInt(c.substring(2));
                } else {
                    k = move(k, moveCnt, table);
                    moveCnt = 0;
                }

                if('C' == op) {
                    k = delete(k, deleted, table);
                } else if('Z' == op){
                    rollback(deleted, table);
                }
            }

            return new String(table);
        }

        public static void rollback(Stack<Integer> deleted, char[] table) {
            table[deleted.pop()] = ROLLBACK;
        }

        public static int delete(int k, Stack<Integer> deleted, char[] table) {
            table[k] = DELETE;  // 삭제
            deleted.push(k);  // 롤백 준비

            int backup = k;

            while(k < table.length && table[k] == DELETE) {
                k++;
            }

            if(k < table.length) return k;

            return k == table.length - 1 ? down(backup, 1, table) : up(backup, -1, table);
        }

        public static int move(int k, int dist, char[] table) {
            return dist > 0 ? down(k, dist, table) : up(k, dist, table);
        }
        
        public static int down(int k, int dist, char[] table) {
            while(dist > 0) {
                if(table[++k] == ROLLBACK) {
                    dist--;
                }
            }

            return k;
        }

        public static int up(int k, int dist, char[] table) {
            while(dist < 0) {
                if(table[--k] == ROLLBACK) {
                    dist++;
                }
            }

            return k;
        }
    }
}
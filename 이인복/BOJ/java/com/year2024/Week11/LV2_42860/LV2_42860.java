package com.year2024.Week11.LV2_42860;

import java.util.*;

public class LV2_42860 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution("JEROEN")
        );
    }

    static class Solution {

        public static int[] cntArr;

        public int solution(String name) {
            cntArr = new int[name.length()];

            for(int idx = 0; idx < name.length(); idx++){
                cntArr[idx] = setCntArr(idx, name);
            }

            return Arrays.stream(cntArr).sum() + bfs();  // [ A -> ? 까지의 계산 ] + 이동 거리
        }

        public int bfs(){
            long cntNotA = cntArr.length - Arrays.stream(cntArr).filter(e -> e == 0).count();
            Queue<Node> queue = new LinkedList<>();

            queue.add(new Node(0, 0, 0, new HashSet<>()));

            while(!queue.isEmpty()){
                Node curNode = queue.poll();

                if(!curNode.set.contains(curNode.idx)){  // 방문하지 않음
                    curNode.set.add(curNode.idx);  // 방문 처리

                    if(cntArr[curNode.idx] != 0) {  // A 가 아닌 경우
                        curNode.cnt += 1;
                    }
                }

                if(curNode.cnt == cntNotA){
                    return curNode.dist;
                }

                int next = curNode.idx == cntArr.length - 1 ? 0 : curNode.idx + 1;
                int prev = curNode.idx == 0 ? cntArr.length - 1 : curNode.idx - 1;

                queue.add(new Node(next, curNode.dist + 1, curNode.cnt, new HashSet<>(curNode.set)));
                queue.add(new Node(prev, curNode.dist + 1, curNode.cnt, new HashSet<>(curNode.set)));
            }

            return -1;   // 여기로 넘어가면 로직 망한거
        }

        private static int setCntArr(int idx, String name){
            char ch = name.charAt(idx);

            return Math.min(91 - ch, ch - 65);
        }
    }

    static class Node{
        int idx;
        int dist;
        int cnt;
        Set<Integer> set;

        Node(int idx, int dist, int cnt, Set<Integer> set){
            this.idx = idx;
            this.dist = dist;
            this.cnt = cnt;
            this.set = set;
        }
    }
}

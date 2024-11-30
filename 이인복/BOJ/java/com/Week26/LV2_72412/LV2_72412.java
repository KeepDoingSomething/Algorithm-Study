/**
 * Author    : Lee In Bok
 * Date      : 2024.11.27(Wed)
 * Runtime   : 728.57 ms
 * Memory    : 258 MB
 * Algorithm : Trie, Binary Search, Graph Search
 */

package com.Week26.LV2_72412;

import java.util.*;

public class LV2_72412 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
                Arrays.toString(sol.solution(
                        new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                        new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"}
                ))
        );
    }

    static class Solution {
        public int[] solution(String[] info, String[] query) {
            Trie trie = new Trie();
            List<Integer> ans = new ArrayList<>();

            // Trie 구조 생성
            for(String result: info) {
                trie.insert(result);
            }

            // Query 질의에 해당하는 결과 반환
            for(String term: query) {
                ans.add(trie.find(term));
            }

            return ans.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    static class Trie {
        Node root = new Node(0);  // 루트 노드
        StringTokenizer st;

        /**
         * BFS 를 통해서 Trie 자료 구조 탐색
         * @param query 질의문 ex) java and backend and junior and pizza 100
         * @return query 조건을 만족하는 지원자 수 반환
         */
        public int find(String query) {
            Queue<Node> q = new LinkedList<>();
            // java and backend and junior and pizza 100 => java backend junior pizza 100 로 변환
            String[] queries = query.replace(" and", "").split(" ");
            Node tmp = root;
            int target = Integer.parseInt(queries[queries.length - 1]);
            int scoreCnt = 0;

            q.add(tmp);

            while(!q.isEmpty()) {
                Node curNode = q.poll();

                if(Objects.isNull(curNode)) continue;

                // 말단 노드
                if(curNode.isLeaf) {
                    // 말단 노드가 갖는 지원자 리스트 정렬을 한 번도 안함
                    if(!curNode.isSorted) {
                        curNode.setIsSorted();
                    }

                    // 말단 노드가 갖는 리스트에서 이분 탐색을 통해서 점수 이상의 사람을 구함
                    scoreCnt += binarySearch(curNode.scores, target);
                    continue;
                }

                String keyword = queries[curNode.level];

                if(keyword.equals("-")) {
                    // - 는 모든 조건이기 때문에 현재 노드가 갖는 모든 자식을 탐색 범위에 추가 ex) - -> backend / frontend 노드들
                    q.addAll(curNode.children.values());
                } else {
                    // query 가 요구하는 조건에 해당하는 자식 노드 반환 ex) backend -> backend 노드
                    q.add(curNode.children.get(keyword));
                }
            }

            return scoreCnt;
        }

        /**
         * 이분 탐색을 통해 특정 점수 이상을 갖는 사람의 수를 구한다.
         * @param scores 트라이 말단 노드가 갖고있는 점수 리스트
         * @param target 기준 점수
         * @return 조건에 해당하는 사람 수
         */
        public int binarySearch(List<Integer> scores, int target) {
            int l = 0;
            int r = scores.size() - 1;

            while(l <= r) {
                int mid = (l + r) / 2;

                if(scores.get(mid) >= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            return scores.size() - l;
        }

        /**
         * 지원자들을 Trie 구조의 노드로 변환 및 삽입
         * @param info 지원자 정보 ex) java backend junior pizza 150
         */
        public void insert(String info) {
            Node tmp = root;
            st = new StringTokenizer(info);

            for(int i = 0; i < 4; i++) {
                String key = st.nextToken();
                int level = i + 1;

                // 아직 노드가 만들어지지 않았다면 노드 생성
                tmp = tmp.children.computeIfAbsent(key, k -> new Node(level));
            }

            // 위 반복문에서 4번 실행했기 때문에 st.nextToken() 는 점수에 해당하고, 마지막 노드라는 의미
            tmp.setIsLeaf(Integer.parseInt(st.nextToken()));
        }
    }

    static class Node {
        Map<String, Node> children = new HashMap<>();
        List<Integer> scores;  // 마지막 노드만 갖는 리스트로 중간 노드들은 null 값을 갖고 있음
        int level;
        boolean isSorted = false;  // 리스트 정렬이 이미 되었는지
        boolean isLeaf = false;    // 마지막 노드인지

        public Node(int level) {
            this.level = level;
        }

        /**
         * 마지막 노드 표시
         * @param score 지원자의 점수
         */
        public void setIsLeaf(int score) {
            // 이미 마지막 노드인 경우 리스트에 값만 넣는다.
            if(isLeaf) {
                scores.add(score);
                return;
            }

            isLeaf = true;  // 마지막 노드 표시
            scores = new ArrayList<>();  // 리스트 처음 초기화
            scores.add(score);  // 초기화 된 리스트에 지원자 점수 넣기
        }

        /**
         * 리스트를 정렬하는 메소드
         * 플래그를 이용하는 이유는 매번 이분 탐색 실행시 정렬한다면 같은 노드에 대해서 정렬을 여러번
         * 실행할 수 있기 때문에 시간 초과 방지 (볼륨이 크기 떄문에 필수)
         */
        public void setIsSorted() {
            isSorted = true;
            Collections.sort(scores);
        }
    }
}
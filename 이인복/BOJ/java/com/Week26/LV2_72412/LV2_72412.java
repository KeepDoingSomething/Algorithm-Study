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

            for(String result: info) {
                trie.insert(result);
            }

            for(String term: query) {
                ans.add(trie.find(term));
            }

            return ans.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    static class Trie {
        Node root = new Node(0);
        StringTokenizer st;

        public int find(String query) {
            Queue<Node> q = new LinkedList<>();
            String[] queries = query.replace(" and", "").split(" ");
            Node tmp = root;
            int target = Integer.parseInt(queries[queries.length - 1]);
            int scoreCnt = 0;

            q.add(tmp);

            while(!q.isEmpty()) {
                Node curNode = q.poll();

                if(Objects.isNull(curNode)) continue;

                if(curNode.isLeaf) {
                    if(!curNode.isSorted) {
                        curNode.setIsSorted();
                    }

                    scoreCnt += binarySearch(curNode.scores, target);
                    continue;
                }

                String keyword = queries[curNode.level];

                if(keyword.equals("-")) {
                    q.addAll(curNode.children.values());
                } else {
                    q.add(getChild(curNode, keyword));
                }
            }

            return scoreCnt;
        }

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

        public Node getChild(Node node, String key) {
            return node.children.get(key);
        }

        public void insert(String info) {
            Node tmp = root;
            st = new StringTokenizer(info);

            for(int i = 0; i < 4; i++) {
                String key = st.nextToken();
                int level = i + 1;

                tmp = tmp.children.computeIfAbsent(key, k -> new Node(level));
            }

            tmp.setIsLeaf(Integer.parseInt(st.nextToken()));
        }
    }

    static class Node {
        Map<String, Node> children = new HashMap<>();
        List<Integer> scores;
        int level;
        boolean isSorted = false;
        boolean isLeaf = false;

        public Node(int level) {
            this.level = level;
        }

        public void setIsLeaf(int score) {
            if(isLeaf) {
                scores.add(score);
                return;
            }

            isLeaf = true;
            scores = new ArrayList<>();
            scores.add(score);
        }

        public void setIsSorted() {
            isSorted = true;
            Collections.sort(scores);
        }
    }
}
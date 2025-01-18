/**
 * Author    : Lee In Bok
 * Date      : 2025.01.18(Sat)
 * Runtime   : 128 ms
 * Memory    : 14576 KB
 * Algorithm : Dijkstra
 * Ref       : https://cs.baylor.edu/~hamerly/icpc/qualifier_2014/
 */

package com.year2025.Week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G2_10473 {

    // 각 노드 까지의 최소 시간 배열
    public static double[] times;
    public static List<Node> graph = new ArrayList();
    public static Queue<Node> pq = new PriorityQueue<>(Node::compareTo);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double srtX = Double.parseDouble(st.nextToken());
        double srtY = Double.parseDouble(st.nextToken());
        st = new StringTokenizer(br.readLine());
        double endX = Double.parseDouble(st.nextToken());
        double endY = Double.parseDouble(st.nextToken());
        int n = Integer.parseInt(br.readLine());

        times = new double[n + 2];
        Arrays.fill(times, Double.MAX_VALUE);

        for(int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            // 모든 노드 리스트에 저장
            graph.add(new Node(i, x, y, 0));
        }

        Node srtNode = new Node(0, srtX, srtY, 0);

        // 도착점 노드 리스트에 저장
        graph.add(new Node(n + 1, endX, endY, 0));

        // 시작 위치에는 대포가 없기 때문에 다음 대포 까지는 걸어서 이동 해야함
        for(Node node : graph) {
            double time = srtNode.getTimeFromStartByWalking(node);

            pq.add(new Node(node.nodeNo, node.x, node.y, time));
            times[node.nodeNo] = time;
        }

        dijkstra();

        System.out.println(times[n + 1]);
    }

    public static void dijkstra() {
        while(!pq.isEmpty()) {
            Node curNode = pq.poll();

            for(Node adjNode: graph) {
                if(curNode.equals(adjNode)) {
                    continue;
                }

                double time = curNode.getTimeMovingBetweenTwoNode(adjNode) + curNode.time;

                if(times[adjNode.nodeNo] > time) {
                    pq.add(new Node(adjNode.nodeNo, adjNode.x, adjNode.y, time));
                    times[adjNode.nodeNo] = time;
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        final double walkSpeed = 5.0;
        final double cannonDist = 50.0;
        final double cannonSpeed = 2.0;

        int nodeNo;
        double x;
        double y;
        double time;

        public Node(int nodeNo, double x, double y, double time) {
            this.nodeNo = nodeNo;
            this.x = x;
            this.y = y;
            this.time = time;
        }

        public double getWalkingTime(double dist) {
            return dist / walkSpeed;
        }

        public double getTimeFromStartByWalking(Node node) {
            return getWalkingTime(getDist(node));
        }

        public double getDist(Node node) {
            return Math.sqrt((x - node.x) * (x - node.x) + (y - node.y) * (y - node.y));
        }

        public double getTimeMovingBetweenTwoNode(Node node) {
            double dist = getDist(node);
            double walkTime = getWalkingTime(dist);
            double cannonTime = 2 + Math.abs(dist - cannonDist) / walkSpeed;

            return Math.min(walkTime, cannonTime);
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.time, o.time);
        }

        @Override
        public boolean equals(Object o) {
            Node n = (Node) o;

            return x == n.x && y == n.y;
        }
    }
}
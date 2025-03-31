package baekjoon.G4_22856;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeCnt = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[nodeCnt + 1];
        for (int i = 1; i < nodeCnt + 1; i++) {
            nodes[i] = new Node();
            nodes[i].num = i;
        }


        Node emptyNode = new Node();
        emptyNode.isVisited = true;
        String line = "";
        while ((line = br.readLine()) != null) {
            int[] info = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int nodeNum = info[0];
            int leftNodeNum = info[1];
            int rightNodeNum = info[2];
            Node parentNode = nodes[nodeNum];

            if(leftNodeNum != -1){
                Node leftNode = nodes[leftNodeNum];
                parentNode.left = leftNode;
                leftNode.parent = parentNode;
            }else{
                parentNode.left = emptyNode;
            }

            if(rightNodeNum != -1){
                Node rightNode = nodes[rightNodeNum];
                parentNode.right = rightNode;
                rightNode.parent = parentNode;
            }else {
                parentNode.right = emptyNode;
            }
        }

        int root = 1;
        int moveCnt = 0;
        Node currentNode = nodes[root];
        if(currentNode.left.isVisited && currentNode.right.isVisited){
            System.out.println(0);
            return;
        }

        Set<Integer> visitedNum = new HashSet<>();
        while (true) {
            if (currentNode.left.isVisited) {
                currentNode.isVisited = true;
                visitedNum.add(currentNode.num);

                if(visitedNum.size() == nodeCnt) break;
            }

            if(!currentNode.left.isVisited){
                currentNode = currentNode.left;
            }else if(!currentNode.right.isVisited){
                currentNode = currentNode.right;
            }else {
                if(currentNode.parent != null) {
                    currentNode = currentNode.parent;
                }

            }
            moveCnt++;
        }

        System.out.println(moveCnt);
    }

    private static class Node{
        int num;
        Node parent;
        Node left;
        Node right;
        boolean isVisited = false;
    }

}

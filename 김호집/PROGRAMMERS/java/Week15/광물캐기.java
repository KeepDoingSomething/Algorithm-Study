/**
 * Author    : HojipKim
 * Date      : 2024.09.07(Sat)
 * Runtime   : 7.13 ms
 * Memory    : 82.9 MB
 * Algorithm : Greedy
 */

import java.util.*;

class Main{
    public static void main(String[] args) {
        Solution solution = new Solution();

        int ans =  solution.solution(new int[]{0, 1, 1}, new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"});
        System.out.println(ans);
    }
}

class Node {
    private String element;
    private int weight;

    Node(String elem){
        this.element = elem;

        if(elem.equals("diamond")){
            this.weight = 25;
        }else if(elem.equals("iron")){
            this.weight = 5;
        }
        else{
            this.weight = 1;
        }
    }

    public String getElement(){
        return this.element;
    }

    public int getWeight() {
        return this.weight;
    }
}


class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        Queue<Node> q = new ArrayDeque<>();

        Queue<Integer> pickQ = new ArrayDeque<>();

        for (String mineral : minerals) {
            Node node = new Node(mineral);
            q.offer(node);
        }
        int pickNum = 1;

        //pick : 1 - diamond, 2 - iron, 3 - stone
        for(int i = 0; i < picks.length; i++){
            if(picks[i] != 0){
                for(int j = 0 ; j< picks[i]; j++){
                    pickQ.offer(pickNum);
                }
            }
            pickNum++;
        }

        int cnt = 0;

        int pickSum = 0;
        for (int i = 0; i < picks.length; i++) {
            pickSum += picks[i];
        }

        Node[][] arr = new Node[pickSum][5];

        int idx = 0;
        // queue에 node들을 5개씩 잘라서 넣는다
        while (!q.isEmpty() && idx < arr.length) {
            Node node = q.poll();
            arr[idx][cnt] = node;
            cnt++;
            if (cnt == 5) {
                idx++;
                cnt = 0;
            }
        }

        // 5개마다 쪼갠 array의 weight들을 관리하는 map입니다.
        Map<Integer, Integer> weightMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int weightSum = 0;
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != null) {
                    weightSum += arr[i][j].getWeight();
                }else{
                    // null이 뽑혀나왔다는것은 index가 종료되었음을 의미합니다.
                    // 따라서, 인자가 1개인데도 불구하고, 이 앞의 index의 weight와 값이 겹칠 시 우선순위를 주기위함입니다.
                    weightSum += 1;
                    break;
                }
            }
            weightMap.put(i, weightSum);
        }

        // 각각 index의 [0]은 원래 index, [1]은 weight, [2]는 pick number 입니다.
        int[][] pickMatchArr = new int[arr.length][3];

        for (int i = 0; i < pickMatchArr.length; i++) {
            // 원래 index 지정
            pickMatchArr[i][0] = i;
            // weight 지정
            pickMatchArr[i][1] = weightMap.get(i);
        }

        Arrays.sort(pickMatchArr, (a, b) -> Integer.compare(b[1], a[1]));

        for (int i = 0; i < pickMatchArr.length; i++) {
            // pick 지정
            if(!pickQ.isEmpty()) {
                pickMatchArr[i][2] = pickQ.poll();
            }
        }

        Arrays.sort(pickMatchArr, (a, b) -> Integer.compare(b[0], a[0]));


        for (int i = 0; i < pickMatchArr.length; i++) {

            if(pickMatchArr[i][2] != 0){
                for(Node node : arr[pickMatchArr[i][0]]){
                    String element;

                    if(node == null){
                        break;}
                    element = node.getElement();
                    int pickNumber = pickMatchArr[i][2];


                    switch(element){
                        case "diamond" :
                            if(pickNumber == 1){
                                answer += 1;
                                break;
                            } else if (pickNumber == 2) {
                                answer += 5;
                                break;
                            } else{
                                answer += 25;
                                break;
                            }
                        case "iron" :
                            if(pickNumber == 1){
                                answer += 1;
                                break;
                            } else if (pickNumber == 2) {
                                answer += 1;
                                break;
                            } else{
                                answer += 5;
                                break;
                            }
                        case "stone" :
                            if(pickNumber == 1){
                                answer += 1;
                                break;
                            } else if (pickNumber == 2) {
                                answer += 1;
                                break;
                            } else{
                                answer += 1;
                                break;
                            }
                    }
                }
            }
        }

        return answer;
    }
}
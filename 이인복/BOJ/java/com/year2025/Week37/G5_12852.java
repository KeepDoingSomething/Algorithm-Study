/**
 * Author    : Lee In Bok
 * Date      : 2025.03.04(Tue)
 * Runtime   : 180 ms
 * Memory    : 21672 KB
 * Algorithm : Queue, Stack
 */
package com.year2025.Week37;

import java.util.*;

public class G5_12852 {

    public static Queue<Integer> q = new LinkedList<>();
    public static int[] seq;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();

        seq = new int[N + 1];

        search(N);

        int temp = 1;
        stack.push(temp);

        // 역추적: 목표로 하는 1 번 배열방에서 이전 각 요소로 저장된 이전 노드 번호를 타고 간다.
        while(temp != N) {
            stack.push(seq[temp]);
            temp = seq[temp];
        }

        // 경로 사이즈 출력
        System.out.println(stack.size() - 1);

        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        // 경로 출력
        System.out.println(sb);
    }

    public static void search(int N) {
        q.add(N);

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == 1) {
                break;
            }

            if(cur % 3 == 0) {
                addAndCheck(cur, cur / 3);
            }

            if(cur % 2 == 0) {
                addAndCheck(cur, cur / 2);
            }

            addAndCheck(cur, cur - 1);
        }
    }

    // 경로 추적을 위해서 seq 배열에 이전 노드의 번호를 저장 후 다음 노드를 큐에 담는다.
    public static void addAndCheck(int cur, int next) {
        if(seq[next] == 0) {
            seq[next] = cur;  // 다음 숫자에 이전 숫자 기록
            q.add(next);
        }
    }
}

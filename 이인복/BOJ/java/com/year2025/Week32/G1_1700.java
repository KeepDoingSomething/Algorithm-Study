/**
 * Author    : Lee In Bok
 * Date      : 2025.01.25(Sat)
 * Runtime   : 108 ms
 * Memory    : 14344 KB
 * Algorithm : Greedy
 */

package com.year2025.Week32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G1_1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Map<Integer, Queue<Task>> schedule = new HashMap<>();
        Set<Integer> multiTab = new HashSet<>();
        int ans = 0;
        int[] seq = Arrays.stream(br.readLine().split(" "))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        for(int i = 0; i < K; i++) {
            schedule.putIfAbsent(seq[i], new LinkedList<>());
            schedule.get(seq[i]).add(new Task(seq[i], i));
        }

        int idx = 0;

        // 멀티탭을 콘센트 교체 없이 끝나는 경우가 있어서 while 문을 아래와 같이 구성함
        /*
            2 칸 모두 교체 없이 진행 가능한 케이스
            2 10
            1 1 2 1 1 2 1 1 2 1
         */
        while(idx != seq.length && multiTab.size() != N) {
            int tool = seq[idx];

            multiTab.add(tool);
            schedule.get(seq[idx]).poll();
            idx++;
        }

        for(int i = N; i < K; i++) {
            int newTool = seq[i];
            boolean noNextSchedule = false;
            // 멀티탭에서 사용중인 모든 용품이 다음 스케줄이 없는경우 아무거나 제거
            Task temp = new Task(0, Integer.MIN_VALUE);

            // 멀티탭이 이미 꽃혀 있는 케이스
            if(multiTab.contains(newTool)) {
                // 해당 스케줄 제거
                schedule.get(newTool).poll();
                continue;
            }

            Set<Integer> tempSet = new HashSet<>();

            for(int using : multiTab) {
                Queue<Task> tasks = schedule.get(using);

                // 다음 스케줄이 없는 경우 멀티탭에서 뽑아도됨
                if(tasks.isEmpty()) {
                    multiTab.remove(using);
                    multiTab.add(newTool);
                    ans++;
                    noNextSchedule = true;
                    schedule.get(newTool).poll();
                    break;
                }

                // 뽑아야 하는케이스에는 가장 늦게 사용하는 도구를 temp 에 보관함
                if(!tempSet.contains(tasks.peek().tool) && temp.seq < tasks.peek().seq) {
                    tempSet.add(tasks.peek().tool);
                    temp = tasks.peek();
                }
            }

            // 스케줄 없는 도구가 없는 경우 가장 늦은 스케줄을 갖는 도구의 콘센트를 뽑는 로직
            if(!noNextSchedule && temp.seq != Integer.MIN_VALUE) {
                multiTab.add(newTool);
                multiTab.remove(temp.tool);
                schedule.get(newTool).poll();
                ans++;
            }
        }

        System.out.println(ans);
    }

    static class Task {
        int tool;
        int seq;

        public Task(int tool, int seq) {
            this.tool = tool;
            this.seq = seq;
        }
    }
}
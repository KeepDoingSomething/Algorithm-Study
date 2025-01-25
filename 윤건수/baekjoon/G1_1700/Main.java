package baekjoon.G1_1700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] done =  Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int holeCnt = info[0];
        int doCnt = info[1];

        Item[] items = new Item[doCnt + 1];
        for (int i = 0; i < doCnt; i++) {
            int itemName = done[i];
            if(items[itemName] == null){
                items[itemName] = new Item(itemName);
            }
            items[itemName].addIdx(i);
        }

        int answer = 0;
        List<Item> useNow = new ArrayList<>();

        for (int i = 0; i < doCnt; i++) {
            Item now = items[done[i]];
            if(useNow.contains(now)) continue;

            now.removeBeforeIdx(i);
            for(Item usedItem : useNow){
                usedItem.removeBeforeIdx(i);
            }

            Collections.sort(useNow, (item1, item2) -> {
                int itemUseIdx1 = item1.useIdxs.isEmpty() ? Integer.MAX_VALUE : item1.useIdxs.peek();
                int itemUseIdx2 = item2.useIdxs.isEmpty() ? Integer.MAX_VALUE : item2.useIdxs.peek();

                return itemUseIdx2 - itemUseIdx1;
            });

            if(useNow.size() >= holeCnt){
                useNow.remove(0);
                answer++;
            }

            useNow.add(now);
        }

        System.out.println(answer);
    }

    private static class Item{
        int name;
        Queue<Integer> useIdxs =  new ArrayDeque<>();

        Item(int name){
            this.name = name;
        }

        void addIdx(int idx){
            useIdxs.add(idx);
        }

        void removeBeforeIdx(int idx){
            while(!useIdxs.isEmpty() && useIdxs.peek() <= idx){
                useIdxs.poll();
            }
        }

        @Override
        public boolean equals(Object o) {
            if(o == null || !(o instanceof Item)) return false;
            Item other = (Item)o;
            return other.name == this.name;
        }
    }

}

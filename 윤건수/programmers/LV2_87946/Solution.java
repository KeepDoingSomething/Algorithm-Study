package programmers.LV2_87946;

import java.util.*;

public class Solution {

    int maxCount;
    boolean[] visited;
    List<Dungeon> dungeonList;

    public int solution(int totalEnergy, int[][] dungeons) {

        maxCount = 0;
        dungeonList = new ArrayList<>();
        visited = new boolean[dungeons.length];

        for(int[] dungeon: dungeons){
            dungeonList.add(new Dungeon(dungeon[0], dungeon[1]));
        }

        for(int i=0; i < dungeonList.size(); i++){
            searchDungeon(i, 0, totalEnergy);
            visited[i] = false;
        }

        return maxCount;
    }

    private void searchDungeon(int dungeon, int count, int totalEnergy){

        if(!visited[dungeon] && dungeonList.get(dungeon).minEnergy <= totalEnergy){
            visited[dungeon] = true;
            count++;
            totalEnergy -= dungeonList.get(dungeon).useEnergy;
            maxCount = Math.max(count, maxCount);

            for(int i=0; i<dungeonList.size(); i++){
                if(!visited[i]){
                    searchDungeon(i, count, totalEnergy);
                    visited[i] = false;
                }
            }
        }

    }

    private class Dungeon{
        int minEnergy;
        int useEnergy;

        Dungeon(int minEnergy, int useEnergy){
            this.minEnergy = minEnergy;
            this.useEnergy = useEnergy;
        }
    }

}
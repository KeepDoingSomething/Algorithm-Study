package programmers.LV2_72411;

import java.util.*;
import java.util.stream.*;

public class Solution {
    List<Integer> courseSize;
    HashMap<String, Integer> orderMap;
    ArrayList<String> result;
    int[] maxPriority = new int[11];

    public String[] solution(String[] orders, int[] course) {
        result = new ArrayList<>();
        orderMap = new HashMap<>();
        courseSize = Arrays.stream(course).boxed().collect(Collectors.toList());

        for(String order: orders){
            String[] menu = order.split("");
            Arrays.sort(menu);
            for(int i=0; i<menu.length; i++){
                makeCourse(menu, i, "");
            }
        }

        for(String menu : orderMap.keySet()){
            int menuSize = menu.length();
            int priority = orderMap.get(menu);
            if(priority == maxPriority[menuSize] && priority >= 2){
                result.add(menu);
            }
        }

        result.sort((a,b) -> a.compareTo(b));
        return result.toArray(String[]::new);
    }

    private void makeCourse(String[] menu, int index, String makedMenu){
        makedMenu += menu[index];
        int menuSize = makedMenu.length();
        if(courseSize.contains(menuSize)){
            int priority = orderMap.getOrDefault(makedMenu, 0);
            orderMap.put(makedMenu, priority + 1);
            maxPriority[menuSize] = Math.max(maxPriority[menuSize], priority + 1);
        }

        for(int i=index+1; i<menu.length; i++){
            makeCourse(menu, i, makedMenu);
        }

    }
}
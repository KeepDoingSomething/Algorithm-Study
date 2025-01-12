import java.util.TreeSet;

class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < operations.length; i++) {
            if(operations[i].equals("D 1")) {
                set.pollLast();
            }else if(operations[i].equals("D -1")) {
                set.pollFirst();
            }else {
                String subStr = operations[i].substring(2);
                set.add(Integer.parseInt(subStr));
            }
        }
        
        return set.isEmpty() ? new int[]{0,0} : new int[]{set.pollLast(),set.pollFirst()};
    }
}

import java.util.ArrayList;

public class Lv2_12946 {
    ArrayList<int[]> list = new ArrayList<>();

    public void hanoi(int n, int start, int middle, int end){
        int[] move = {start, end};

        if(n == 1){
            list.add(move);
        }else {
            hanoi(n - 1, start, end, middle);

            list.add(move);

            hanoi(n - 1, middle, start, end);
        }
    }

    public ArrayList<int[]> solution(int n) {
        hanoi(n, 1, 2, 3);

        return list;
    }

    public static void main(String[] args) {

    }
}

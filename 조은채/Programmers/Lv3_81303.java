import java.util.ArrayList;
import java.util.Stack;

public class Lv3_81303 {
    public String solution(int n, int k, String[] cmd) {
        StringBuilder answer = new StringBuilder();

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        Stack<Integer> stack = new Stack<>();

        for (String s : cmd) {
            String tmpCmd = s.substring(0, 1);

            switch (tmpCmd) {
                case "U":
                    k -= Integer.parseInt(s.substring(2));
                    break;

                case "D":
                    k += Integer.parseInt(s.substring(2));
                    break;

                case "C":
                    System.out.println("get 값 >>> " + list.get(k));

                    stack.add(list.get(k));
                    list.set(k, -1);

                    if (k == list.size() - 1){
                        k--;
                    }

                    System.out.println("stack");
                    System.out.println(stack);
                    break;

                case "Z":
                    int tmp = stack.pop();

                    list.set(tmp, tmp);
            }

            System.out.println(k);
            System.out.println(list);
        }

        for (int a : list) {
            if (a == -1){
                answer.append("X");
            }else {
                answer.append("O");
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        Lv3_81303 lv3_81303 = new Lv3_81303();
        //String[] arr = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        String[] arr = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};

        System.out.println(lv3_81303.solution(8, 2, arr));
    }
}

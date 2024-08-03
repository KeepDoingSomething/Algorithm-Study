import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class G5_6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        long answer = 0;

        for (int i = 0; i < N; i++) {
            int building = Integer.parseInt(br.readLine());

            while (!stack.isEmpty() && (stack.peek() <= building)){
                stack.pop();
            }

            answer += stack.size();

            stack.push(building);
        }

        System.out.println(answer);
    }
}

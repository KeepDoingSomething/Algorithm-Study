package baekjoon.G4_9935;

// 문자열 폭발 (Gold 4)
// https://www.acmicpc.net/problem/9935

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] characters = br.readLine().split("");
        String eraseStr = br.readLine();
        int eraseLength = eraseStr.length();
        StringBuilder saved = new StringBuilder();

        for (String curStr : characters) {
            saved.append(curStr);

            while (saved.length() - eraseLength >= 0) {
                int savedLength = saved.length();
                String endStr = saved.substring(savedLength - eraseLength, savedLength);
                if (endStr.equals(eraseStr)) {
                    saved.delete(saved.length() - eraseLength, saved.length());
                } else {
                    break;
                }
            }
        }

        String result = saved.length() == 0 ? "FRULA" : saved.toString();
        System.out.println(result);
    }

}

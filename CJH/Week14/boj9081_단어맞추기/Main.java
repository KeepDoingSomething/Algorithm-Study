package baekjoon.silver.silver1.boj9081_단어맞추기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/baekjoon/silver/silver1/boj9081_단어맞추기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            char[] word = br.readLine().toCharArray();
            nextPermutation(word);
            System.out.println(new String(word));
        }
    }

    private static void nextPermutation(char[] word) {
        int i = word.length - 1;

        // 1. 뒤에서부터 처음으로 감소하는 위치 찾기
        while (i > 0 && word[i - 1] >= word[i]) {
            i--;
        }

        // 가장 큰 순열로 다음 순열이 없어 종료
        if (i <= 0) return;

        int j = word.length - 1;

        // 2. 감소하는 위치의 문자보다 큰 문자 찾기
        while (word[j] <= word[i -1]) {
            j--;
        }

        // 3. 감소하는 문자와 큰 문자와 위치 바꾸기
        char temp = word[i - 1];
        word[i - 1] = word[j];
        word[j] = temp;

        j = word.length - 1;
        // 4. 감소하는 위치 뒤쪽 뒤집기
        while (i < j) {
            temp = word[i];
            word[i] = word[j];
            word[j] = temp;
            i++;
            j--;
        }

    }
}

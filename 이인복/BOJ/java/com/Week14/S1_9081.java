package com.Week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class S1_9081 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            String[] word = br.readLine().split("");

            NESTED_LOOP:
            for(int j = word.length - 1; j > 0; j--) {
                for(int k = j - 1; k >= 0; k--) {
                    if(word[j].compareTo(word[k]) > 0) {
                        String temp = word[j];
                        word[j] = word[k];
                        word[k] = temp;

                        Arrays.sort(word, k + 1, word.length);
                        break NESTED_LOOP;
                    }
                }
            }

            sb.append(Arrays.stream(word).collect(Collectors.joining("")))
              .append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}

/*
ABCDE FGHIJ KLMNO PQRST UVWXY Z

1
SHUTT => 19 8 21 20 20
STHTU => 19 20 8 20 21
 */
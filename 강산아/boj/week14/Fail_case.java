/**
 * Author    : Kang San Ah
 * Date      : 2024.08.23(Fri)
 * Runtime   : 1 sec
 * Memory    : 128 MB
 * Algorithm : Array
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Fail_case {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < T ; i++){
            char[] wchar = br.readLine().toCharArray();

            boolean changed = false;
            int index = 0 ;

            for (int k = wchar.length-1 ; k > 0 ; k--){

                for (int j = k-1 ; j >= 0 ; j-- ){
                    if (wchar[k] > wchar[j]){
                        char tmp = wchar[k];
                        wchar[k] = wchar[j];
                        wchar[j] = tmp;

                        changed = true;
                        index = j+1;
                    }
                    else continue;
                    if (changed)break;
                }
                if (changed) break;
            }
            if (changed){
                Arrays.sort(wchar,index,wchar.length);
                for (char word : wchar){
                    answer.append(wchar[word]);
                }
                answer.append("\n");
            } else {
                for (char word : wchar){
                    answer.append(wchar[word]);
                }
                answer.append("\n");
            }
        }
        System.out.println(answer);
    }
}


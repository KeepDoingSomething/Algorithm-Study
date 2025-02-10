import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int cup[] = new int[N];

        int result[] = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i =0; i < N; i++){
            cup[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cup);

        long mindiff = Long.MAX_VALUE;


        for(int i =0; i < N-2; i++){
            int left = i+1;
            int right = N-1;

            while(left < right){

                long sum = (long)cup[i] + cup[left] + cup[right];

                if(Math.abs(sum) < mindiff){
                    mindiff = Math.abs(sum);
                    result[0] = cup[i];
                    result[1] = cup[left];
                    result[2] = cup[right];
                }

                if(sum < 0){
                    left++;
                }
                else{
                    right--;
                }
            }
        }


        Arrays.sort(result);


        System.out.println(result[0] + " " + result[1] + " " + result[2]);

    }
}
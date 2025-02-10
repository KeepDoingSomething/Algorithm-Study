import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String inp[] = br.readLine().split(" ");

        int[] intArray = new int[inp.length];
        for(int i = 0; i<n; i++){
            intArray[i]=Integer.parseInt(inp[i]);
        }

        Arrays.sort(intArray);

        long[] resultEle = new long[3];
        long result=3000000000L;

        for(int i = 0; i<n-2; i++){
            long one, two, thr;
            int twoCnt = i+1;
            int cnt = n-1;
            while(twoCnt<cnt){
                one = intArray[i];
                thr = intArray[cnt];
                two = intArray[twoCnt];
                //System.out.println(one+" "+two+" "+thr);
                long sum = one+two+thr;
                if (result>(abs(sum))) {
                    result = abs(sum);
                    resultEle[0] = one;
                    resultEle[1] = two;
                    resultEle[2] = thr;
                }
                if(sum==0){
                    break;
                } else if (sum>0) {
                    cnt-=1;
                } else{
                    twoCnt+=1;
                }
            }
        }

        Arrays.sort(resultEle);

        System.out.println(resultEle[0]+" "+resultEle[1]+" "+resultEle[2]);
    }
}
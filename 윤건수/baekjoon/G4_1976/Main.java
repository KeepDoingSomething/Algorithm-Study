package baekjoon.G4_1976;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] city;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 도시 수
        int m = Integer.parseInt(br.readLine()); // 여행할 도시의 수
        city = new int[n+1];

        for(int i=1; i<city.length; i++){
            city[i]=i;
        }

        for(int i=0; i<n; i++){
            String[] info = br.readLine().split(" ");
            for(int j=0; j<info.length; j++){
                if(info[j].equals("1")){
                    int a = city[i+1];
                    int b = city[j+1];
                    union(a, b);
                }
            }
        }

        boolean result = true;
        String[] plan = br.readLine().split(" ");
        for(int i=0; i<plan.length-1; i++){
            if(!isConnected(Integer.parseInt(plan[i]), Integer.parseInt(plan[i+1]))){
                result = false;
                break;
            }
        }

        if(result) System.out.println("YES");
        else System.out.println("NO");

    }

    public static void union(int a, int b){

        if(!isConnected(a, b)){
            city[a]=b;
        }
    }

    public static int find(int a){
        if(city[a]==a)
            return a;
        else
            return find(city[a]);
    }

    public static boolean isConnected(int a, int b){
        a = find(a);
        b = find(b);
        if(a==b) return true;
        else return false;
    }

}
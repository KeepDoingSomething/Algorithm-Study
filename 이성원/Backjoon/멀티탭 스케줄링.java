import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st=  new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 멀티탭 구멍 갯수

        int K = Integer.parseInt(st.nextToken()); // 전기 용품 사용 순서


        int order[] = new int[K];

        boolean use[] = new boolean[101];

        st=  new StringTokenizer(br.readLine());

        for(int i =0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }



        int reps = 0;
        int aw = 0;

        for(int i =0; i < K; i++) {
            int temp = order[i];

            if(!use[temp]) {

                if(reps < N) {   // 콘센트에 아무것도 없을떄
                    use[temp] = true;
                    reps++; // 콘센트 하나 추가
                }
                else { //콘센트 꽊참   -> 뽑는경우
                    ArrayList<Integer> list = new ArrayList<>();

                    for(int j = i; j < K; j++) {
                        if(use[order[j]] && !list.contains(order[j])) {   // 콘센트에 꼽혀 있는 애 중  다음 차례에 오는 애들을  LIst에 넣어줌
                            list.add(order[j]);
                        }
                    }

                    if(list.size() < N) {  //콘센트에 꼽힌 애들이 모두 다음 차레에 안오는경우 -> 즉 2개중 하나만 다음 차례에 있는경우
                        for(int j = 0; j < use.length; j++) {
                            if(use[j] && !list.contains(j)) { //꼽혀있는것중 다음 차례 안오는거를 빼줌
                                use[j] = false; //봅는작업
                                break;
                            }
                        }
                    }
                    else {
                        int number = list.get(list.size()-1);

                        use[number] = false;
                    }

                    use[temp] = true;
                    aw++;

                }
            }
        }

        System.out.println(aw);


    }

}
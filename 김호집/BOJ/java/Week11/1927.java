/**
 * Author    : HojipKim
 * Date      : 2024.08.03(Sat)
 * Runtime   : 264 ms
 * Memory    : 26988 KB
 * Algorithm : 자료 구조, 우선순위 큐
 */
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {

            int num = Integer.parseInt(br.readLine());

            if(num == 0) {
                if(pq.isEmpty()) {
                    bw.write("0\n");
                }
                else {
                    bw.write(pq.poll()+"\n");
                }
            }
            else {
                pq.add(num);
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

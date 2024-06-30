/**
 * Author    : HojipKim
 * Date      : 2024.06.20(Thu)
 * Runtime   : 1296 ms
 * Memory    : 137048 KB
 * Algorithm : 최소힙, 우선순위큐
 */
class Node implements Comparable<Node> {
    private int first;
    private int last;

    public Node(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public int compareTo(Node o) {
        if(this.getFirst() == o.getFirst()){
            return o.getLast()-this.getLast();
        }
        return this.getFirst() - o.getFirst();
    }

    public int getFirst() {
        return first;
    }

    public int getLast() {
        return last;
    }
}

class Main {

    private static List<Node> nodes;
    private static List<Integer> backpacks;
    private static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        nodes = new ArrayList<>();
        backpacks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            Node node = new Node(M, V);
            nodes.add(node);
        }



        for (int i = 0; i < K; i++) {
            int backpackLimit = Integer.parseInt(br.readLine());
            backpacks.add(backpackLimit);
        }

        Collections.sort(nodes);
        Collections.sort(backpacks);

        // (최소힙) 가격이 내림차순으로 정렬되도록 설정
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0, j = 0; i < K; i++) {

            while(j< N && nodes.get(j).getFirst() <= backpacks.get(i)){
                pq.offer(nodes.get(j++).getLast());
            }

            if(!pq.isEmpty()){
                answer += pq.poll();
            }
        }
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
/**
 * Author    : HojipKim
 * Date      : 2024.08.14(Wed)
 * Runtime   : 708 ms
 * Memory    : 118780 KB KB
 * Algorithm : 데이크스트라, 그래프 이론, 최단 경로
 */
class Node implements Comparable<Node>{
    int target, weight;

    public Node(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}

class Main{

    public static List<Node>[] list;
    public static boolean[] isVisited;
    public static int[] distance;
    private static int INF = 100000000;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        isVisited = new boolean[V+1];
        list = new ArrayList[V+1];
        distance = new int[V+1];

        Arrays.fill(distance, INF);

        for (int i = 1; i < V+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
        }
        StringBuilder sb = new StringBuilder();

        dijkstra(K);

        for (int i = 1; i < V+1; i++) {
            if(distance[i] == INF) {
                sb.append("INF\n");
            }else{
                sb.append(distance[i] + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int next = cur.target;

            if(!isVisited[next]){
                isVisited[next] = true;

                for(Node node : list[next]){
                    if(distance[node.target] > distance[next] + node.weight){
                        distance[node.target] = distance[next] + node.weight;
                        pq.add(new Node(node.target, distance[node.target]));
                    }
                }
            }
        }

    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int V, P;
    static ArrayList<Node>[] graph;

    static class Node implements Comparable<Node> {
        int node;
        int dist;

        public Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 입력받기
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        dist = new int[V + 1];
        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<Node>();
        }

        // 그래프 간선 연결
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        // 다익스트라
        int throughP = dijkstra(1, P) + dijkstra(P, V);
        int goV = dijkstra(1,V);
        System.out.println(throughP == goV?"SAVE HIM":"GOOD BYE");
    } // end of main

    private static int dijkstra(int start, int end) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.node == end) {
                return cur.dist;
            }
            for (Node nxt : graph[cur.node]) {
                int d = cur.dist + nxt.dist;
                if(dist[nxt.node] > d){
                    dist[nxt.node] = d;
                    pq.offer(new Node(nxt.node, d));
                }
            }
        }
        return 0;
    }
} // end of class
